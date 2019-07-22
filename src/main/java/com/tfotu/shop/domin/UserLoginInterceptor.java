package com.tfotu.shop.domin;

import com.tfotu.shop.Utils;
import com.tfotu.shop.dao.UserMapper;
import com.tfotu.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    //  模拟登陆
    @Autowired
    private UserMapper userMapper;
    public static UserLoginInterceptor userLoginInterceptor;
    @PostConstruct
    public void init() {
        userLoginInterceptor = this;
        userLoginInterceptor.userMapper = this.userMapper;
    }
    //  模拟登陆

    //  true则继续执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = Utils.getSession();
        User user = (User)session.getAttribute("user");

        //  模拟登陆
        if( user == null ) {
            user = userLoginInterceptor.userMapper.getByMobile("18680884457");
            session.setAttribute("user", user);
        }

        if( user == null ){
            Utils.getResponse().setContentType("text/html;charset=UTF-8");
            String tips = "<script>alert('请登陆！');window.location.href='/userLogin';</script>";
            Utils.getResponse().getWriter().print(tips);
            //Utils.getResponse().sendRedirect("/userLogin");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
