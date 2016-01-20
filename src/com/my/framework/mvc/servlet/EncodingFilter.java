package com.my.framework.mvc.servlet;

import java.io.IOException;

import javax.servlet.*;

/**
 * YNJ: ����ת�����뷽ʽ��filter
 * 
 * @author computer
 */

//@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {
    
   // final static Logger LOG = LoggerFactory.getLogger(EncodingFilter.class);

    public void destroy() {
        System.out.println("����......");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("��ʼ��......");
    }

}