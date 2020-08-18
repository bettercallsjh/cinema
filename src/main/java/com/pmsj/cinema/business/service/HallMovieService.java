package com.pmsj.cinema.business.service;

import com.pmsj.cinema.business.exception.CouponIllegalException;
import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.common.entity.*;
import com.pmsj.cinema.common.mapper.*;
import com.pmsj.cinema.common.vo.TicketVo;
import com.pmsj.cinema.common.vo.TicketsVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author mhd
 * @className HallMovieService
 * @description TODO
 * @create 2020/7/7
 * @since 1.0.0
 */
@Service
public class HallMovieService {
    @Autowired(required = false)
    HallMovieMapper hallMoviemapper;
    @Autowired(required = false)
    UserCouponMapper userCouponMapper;
    @Autowired(required = false)
    OrderSeatMapper orderSeatMapper;
    @Autowired(required = false)
    OrderMapper orderMapper;
    @Autowired(required = false)
    SeatMapper seatMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    SeatService seatService;
    @Autowired
    OrderService orderService;
    @Autowired
    HallService hallService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String DELAY_EXCHANGE="cinema_delay_exchange";
    private static final String DELAY_ROUTING_KEY="cinema_delay_routing_key";


    public HallMovie selectById(Integer id) {
        if (id == null) {
            throw new NullParametersException("HallMovieId is null");
        }
        return hallMoviemapper.selectByPrimaryKey(id);
    }

    public synchronized int add(HallMovie hallMovie) {
        int hallMoviesByTime = hallMoviemapper.getHallMoviesByTime(hallMovie.getHallId(), hallMovie.getStartTime(), hallMovie.getEndTime());
        if (hallMoviesByTime == 0) {
            hallMoviemapper.insert(hallMovie);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 添加订单
     *
     * @param tickets
     * @param session
     */
    private int addOrder(TicketsVo tickets, HttpSession session) {
        HallMovie hallMovie = this.selectById(tickets.getHallMovieId());
        Order order = new Order();

        String orderNo = UUID.randomUUID().toString().replace("-", "");
        order.setOrderNo(orderNo);


        //场次信息
        order.setHallMovieId(tickets.getHallMovieId());

        //消费金额
        order.setOrderUnitprice(new BigDecimal(hallMovie.getFareMoney()));
        double money = hallMovie.getFareMoney() * tickets.getTickets().size();
        order.setOrderTotalInitialCash(new BigDecimal(money));
        //优惠卷
        order.setUserCouponId(tickets.getUserCouponId());
        UserCoupon userCoupon = userCouponMapper.selectByPrimaryKey(tickets.getUserCouponId());
        if(userCoupon!=null){
        Integer couponId=userCoupon.getCouponId();
            order.setCouponId(couponId);
            order.setOrderTotalDiscountsCash(new BigDecimal(money-couponMapper.selectByPrimaryKey(couponId).getCouponMoeny()));
        }else {
            order.setOrderTotalDiscountsCash(new BigDecimal(money));
        }


        //session中用户信息
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new NullParametersException("User is null");
        } else {
            order.setUserId(user.getUserId());
        }

        //订单状态 未支付:1 已支付：2 已取消 0
        order.setOrderStatus(1);
        //票数
        order.setOrderCount(tickets.getTickets().size());
        //订单创建时间
        order.setOrderTime(new Date());
        //添加订单
        orderService.buyTickets(order);
//        //将订单编号存储到session中
//        session.setAttribute("orderNo", orderNo);

        //将订单编号存入消息队列
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE,DELAY_ROUTING_KEY,orderNo);
        return order.getOrderId();
    }

    /**
     * 添加座位
     *
     * @param tickets
     */
    private  int[] addSeat(TicketsVo tickets) {
        Integer hallMovieId = tickets.getHallMovieId();
        List<TicketVo> list = tickets.getTickets();
        int[] seatIds = new int[tickets.getTickets().size()];
        int i = 0;
        for (TicketVo ticket : list) {
            Seat seat = new Seat();
            seat.setHallMovieId(hallMovieId);
            //设置行
            seat.setSeatX(ticket.getCol());
            //设置列
            seat.setSeatY(ticket.getRow());
            //设置状态
            seat.setSeatTpye(2);
            seat.setSeatName(ticket.getSeatInfo());
            seatService.isAvailable(ticket,hallMovieId);
            seatService.addSeat(seat);
            seatIds[i++]=seat.getSeatId();
        }
        return seatIds;
    }

    @Transactional
    public  String buyTicket(TicketsVo tickets, HttpSession session) {
            if (tickets.getTickets().size()==0){
                throw new NullParametersException("未选座");
            }
            int[] seatIds = addSeat(tickets);
            int orderid = addOrder(tickets, session);
            for (int seatId : seatIds) {
                OrderSeat orderSeat = new OrderSeat();
                orderSeat.setOrderId(orderid);
                orderSeat.setSeatId(seatId);
                orderSeat.setSeatName(seatMapper.selectByPrimaryKey(seatId).getSeatName());
                orderSeatMapper.insert(orderSeat);
            }
            //修改优惠卷状态 0 不可用 1 可用
            UserCoupon userCoupon = userCouponMapper.selectByPrimaryKey(tickets.getUserCouponId());
            if(userCoupon!=null) {
                if (userCoupon.getCouponStatus() == 1) {
                    userCoupon.setCouponStatus(0);
                    userCouponMapper.updateByPrimaryKey(userCoupon);
                } else {
                    throw new CouponIllegalException("Coupon is not available");
                }
            }
            return orderMapper.selectByPrimaryKey(orderid).getOrderNo();

    }


}
