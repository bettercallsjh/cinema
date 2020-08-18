package com.pmsj.cinema.business.controller;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.pmsj.cinema.business.service.OrderService;
import com.pmsj.cinema.common.entity.Order;
import com.pmsj.cinema.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mhd
 * @className AlipayControllar
 * @description TODO
 * @create 2020/7/10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/alipay")
@EnableConfigurationProperties(AlipayConfig.class)
public class AlipayControllar {
    @Autowired
    OrderService orderService;
    @Autowired
    AlipayConfig alipayConfig;


    @RequestMapping("/pay")
    public void pay(HttpServletResponse httpResponse,String orderNo) throws IOException {


        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key,
                "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);

//        //商品价格总额
//        String orderNo = (String) session.getAttribute("orderNo");
        if (orderNo==null){
            return;
        }
        Order order = orderService.getOrderByNo(orderNo);
        if (order==null){
            return;
        }

        //商品名称

        String subject = new String("购票");

        //商品描述，可以为空
        String body = "蜗牛电影购票";

        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setOutTradeNo(orderNo);
        model.setTotalAmount(String.valueOf(order.getOrderTotalDiscountsCash()));
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);
        alipayRequest.setReturnUrl(alipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);


        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping("/notify")
    public void doNotify(String out_trade_no){
        Order order = orderService.getOrderByNo(out_trade_no);
        order.setOrderStatus(2);
        orderService.updateOrderStatues(order);
    }

    @RequestMapping("/return")
    public String doReturn(String out_trade_no){
        Order order = orderService.getOrderByNo(out_trade_no);
        order.setOrderStatus(2);
        orderService.updateOrderStatues(order);
        return "business/HTML/zhifu.html";
    }

}
