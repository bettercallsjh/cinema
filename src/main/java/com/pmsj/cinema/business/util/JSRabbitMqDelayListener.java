package com.pmsj.cinema.business.util;


import com.alipay.api.internal.parser.json.ObjectJsonParser;
import com.pmsj.cinema.business.controller.UserController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class JSRabbitMqDelayListener {

    @Autowired
    UserController userController;


    private static final String DEAD_LETTER_QUEUE="js_cinema_dead_letter_queue";
    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void recv(Object myMsg){
        if(myMsg != null){
            HttpSession myMsg1 = (HttpSession) myMsg;
            myMsg1.setAttribute("code",0);
        }
    }
}
