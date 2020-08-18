package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.UserService;
import com.pmsj.cinema.business.util.EmailUtil;
import com.pmsj.cinema.common.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
/*
 * @author jiangshuai
 * @date 2020/7/1 0001 19:59
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String DELAY_EXCHANGE = "js_cinema_delay_exchange";
    public static final String DELAY_ROUTING_KEY = "js_cinema_delay_routing_key";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserService userService;

    /*
     * @Author jiangshuai
     * @Description // TODO 查询用户，
     * @Date 18:44 2020/7/8 0008
     * @Param [account, session]
     * @since 1.0.0
     * @return com.pmsj.cinema.common.entity.User
     **/
    @RequestMapping("/selectUser")
    @ResponseBody
    public User selectUser(String account, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user = userService.selectUser(user.getUserAccount());
            session.setAttribute("user", user);
            return user;
        }
        user = userService.selectUser(account);
        return user;
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 验证手机号是否被使用，
     * @Date 18:44 2020/7/8 0008
     * @Param [account, session]
     * @since 1.0.0
     * @return com.pmsj.cinema.common.entity.User
     **/
    @RequestMapping("/selectPhone")
    @ResponseBody
    public User selectPhone(String phone, HttpSession session) {
        User user = userService.selectPhone(phone);
        session.setAttribute("user", user);
        return user;
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 验证邮箱是否被使用，
     * @Date 18:44 2020/7/8 0008
     * @Param [account, session]
     * @since 1.0.0
     * @return com.pmsj.cinema.common.entity.User
     **/
    @RequestMapping("/selectEmail")
    @ResponseBody
    public User selectEmail(String email, HttpSession session) {
        User user = userService.selectEmail(email);
        session.setAttribute("user", user);
        return user;
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 获取验证码
     * @Date 18:45 2020/7/8 0008
     * @Param
     * @since 1.0.0
     * @return
     **/
    @RequestMapping("/getCode")
    @ResponseBody
    public void getCode(String email,HttpSession session) {
//        rabbitTemplate.convertAndSend(DELAY_EXCHANGE, DELAY_ROUTING_KEY,session);
        EmailUtil emailUtil = new EmailUtil();
        Integer code = emailUtil.sendMail(email);
        session.setAttribute("code",code);
        session.setAttribute("email",email);

    }

    /*
     * @Author jiangshuai
     * @Description // TODO 注册
     * @Date 18:45 2020/7/8 0008
     * @Param [verifycode, userAccount, password1, email, phone]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/register")
    public String register(Map map, String verifycode,HttpSession session, String userAccount, String password1, String email, String phone) {
        String photo = "../IMG/Bangdan_2.jpg";
        int vcode = Integer.parseInt(verifycode);
        Integer code = (Integer) session.getAttribute("code");
        String ema = (String) session.getAttribute("email");
        if (email.equals(ema)) {
            if (code != 0) {
                if (vcode == code) {
                    userService.register(userAccount, password1, email, phone, photo);
                    return "redirect:/business/HTML/login.html";
                }
                map.put("error", "验证码错误");
                return "/business/HTML/register.html";
            } else {
                map.put("error", "验证码失效");
                return "/business/HTML/register.html";
            }
        }else{
            map.put("error", "邮箱号与注册邮箱号不一样");
            return "/business/HTML/register.html";
        }
    }
    /*
     * @Author jiangshuai
     * @Description // TODO 使用账户登录
     * @Date 18:45 2020/7/8 0008
     * @Param [account, password, session]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/accountLogin")
    public String accountLogin(String account, String password, HttpSession session) {
        User user = userService.accountLogin(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            String url = (String) session.getAttribute("url");
            if (url != null && url.length() > 0) {
                session.removeAttribute("url");
                return "redirect:" + url;
            }
            return "redirect:/business/HTML/index.html";
        }
        return "redirect:/business/HTML/login.html";
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 使用邮箱登录
     * @Date 18:46 2020/7/8 0008
     * @Param [email, verifycode, session]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/emailLogin")
    public String emailLogin(String email, String verifycode, HttpSession session) {
        int vcode = Integer.parseInt(verifycode);
        Integer code = (Integer) session.getAttribute("code");
        if (code == vcode) {
            User user = userService.emailLogin(email);
            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:/business/HTML/index.html";
            }
        }
        return "redirect:/business/HTML/denglu.html";
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 退出登录
     * @Date 18:46 2020/7/8 0008
     * @Param [session]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/business/HTML/index.html";
    }

    /*
     * @Author jiangshuai
     * @Description // TODO 修改密码
     * @Date 18:46 2020/7/8 0008
     * @Param [email, verifycode, password]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/updatePass")
    @ResponseBody
    public String updatePass(String email, String verifycode, String password,HttpSession session) {
        int vcode = Integer.parseInt(verifycode);
        Integer code = (Integer) session.getAttribute("code");
        if (code == vcode) {
            Integer num = userService.updatePass(email, password);
            if (num > 0) {
                return "true";
            }
        }
        return "false";
    }

    /*
     * @Author jiangshuai
     * @Description //TODO 修改用户信息
     * @Date 18:47 2020/7/8 0008
     * @Param [user]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(User user) {
        Integer num = userService.updateUser(user);
        if (num > 0) {
            return "true";
        }
        return "false";
    }

    /*
     * @Author jiangshuai
     * @Description //TODO 修改用户头像
     * @Date 18:47 2020/7/8 0008
     * @Param [user]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/updatePhoto")
    @ResponseBody
    public String updatePhoto(HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //判断用户是否选择图片
        if (image.getOriginalFilename() == null || image.getOriginalFilename().length() <= 0) {
        } else {
            String fileName = image.getOriginalFilename();
            String substring = "";
            if (fileName.lastIndexOf(".") != -1) {
                substring = fileName.substring(fileName.lastIndexOf("."));
            }
            String name = UUID.randomUUID().toString().replaceAll("-", "") + substring;
            request.getServletPath();
            String property = System.getProperty("user.dir");
            String realPath = property + "\\src\\main\\resources\\static\\img";
            user.setUserPhoto("/img/" + name);
            try {
                File fileMkdir = new File(realPath);
                if (!fileMkdir.exists()) {
                    fileMkdir.mkdir();
                }
                FileOutputStream os = new FileOutputStream(fileMkdir.getPath() + "\\" + name);
                InputStream in = image.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) { //读取文件
                    os.write(b);
                }
                os.flush(); //关闭流
                in.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int num = userService.updatePhoto(user);
        if (num > 0) {
            return "true";
        }
        return "false";
    }

}
