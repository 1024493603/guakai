package com.practice.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("EncodingFilter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("EncodingFilter.doFilter");
        //必须为子类才能用getMethod()
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        //解决post乱码
        if (method.equalsIgnoreCase("post")) {
            servletRequest.setCharacterEncoding("UTF-8");
//            request.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("EncodingFilter.destroy");
    }
}
