package com.atguigu.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(urlPatterns = {"*.do","*.html"},
//        initParams = {
//            @WebInitParam(name="bai",value="/book_war_exploded2/page.do?operate=page&page=user/login,/book_war_exploded2/user.do?null")
//        })
public class SessionFilter implements Filter {

    List<String> baiList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bai = filterConfig.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = uri + "?" + queryString;
        if(baiList.contains(str)){
            filterChain.doFilter(request,response);
            return;
        }else{
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if(currUserObj==null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
