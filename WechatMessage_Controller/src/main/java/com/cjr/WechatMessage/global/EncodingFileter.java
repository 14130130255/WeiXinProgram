package com.cjr.WechatMessage.global;
;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/29
 * @Time:13:14
 */
public class EncodingFileter implements Filter {

    private String encoding = "utf-8";


    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("encoding")!=null){
            encoding = filterConfig.getInitParameter("encoding");
        }
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request,response);
    }


    public void destroy() {

    }
}
