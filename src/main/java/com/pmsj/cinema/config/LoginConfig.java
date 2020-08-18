package com.pmsj.cinema.config;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/14 13:23
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Package: com.*.*.config
 * @ClassName: LoginConfig
 * @Description:拦截器配置
 * @author: zk
 * @date: 2019年9月19日 下午2:18:35
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new UserConfig());

        registration.addPathPatterns("/**/jifen/**",
                "/**/yingyuanShop/**",
                "/**/xuanzuo.html",
                "/**/transaction.html",
                "/**/account.html",
                "/**/updateUser",
                "/**/updatePhoto",
                "/**/updatePass",
                "/**/shopCoupon",
                "/**/getAllCoupon",
                "/**/getAllOrderByUser",
                "/**/addComment",
                "/**/account.html",
                "/**/yingyuanShop/**"

        );                      //所有路径都被拦截


//        registration.excludePathPatterns("/**");
    }
}