package com.practice.web.filter;


import com.practice.web.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter.init");
    }

    // HttpServletRequest extends ServletRequest
    // ServletRequest servletRequest = new HttpServletRequest();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoginFilter.doFilter");
        //要转换为子类HttpServletRequest才能用getSession方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        String method = request.getParameter("method");
        // 这些操作本身就是要去完成登录流程的，不需要执行后面是否登录的验证流程
        // 如果操作为登陆操作则不拦截
        if (servletPath.endsWith(".js")
                || servletPath.endsWith(".png")
                || servletPath.endsWith(".jpg")
                || servletPath.endsWith(".css")     //以上静态资源也放行
                || servletPath.endsWith("/verifyCode")  //验证码图片放行
                || servletPath.equals("/login.jsp")     //登录界面放行
                ||servletPath.equals("/user") && method.equals("login"))    //登录界面提交放行
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Cannot forward after response has been committed
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter.destroy");
    }
}
