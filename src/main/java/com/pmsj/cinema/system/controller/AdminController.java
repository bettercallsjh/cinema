package com.pmsj.cinema.system.controller;

import com.pmsj.cinema.common.entity.Admin;
import com.pmsj.cinema.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jiangshuai
 * @date 2020/7/1 0001 10:19
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String adminName = request.getParameter("adminName");
        session.setAttribute("adminName",adminName);
        String password = request.getParameter("passWord");
        System.out.println(password);
        System.out.println("登录");
        if(adminService.selectAdmin(adminName,password)!=null){
            return "redirect:/system/index.html";
        }
        return "redirect:/system/page/login-2.html";
    }
}
