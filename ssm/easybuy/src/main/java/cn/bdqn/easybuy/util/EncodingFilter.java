//package cn.bdqn.easybuy.util;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//
///**
// * Servlet Filter implementation class EncodingFilter
// *
// */
//@WebFilter(urlPatterns = "/*",
//        initParams = @WebInitParam(name = "encode",value = "UTF-8"))
//public class EncodingFilter implements Filter {
//
//    private String encode;
//
//    public EncodingFilter() {
//    }
//    public void init(FilterConfig fConfig) throws ServletException {
//        encode=fConfig.getInitParameter("encode");
//    }
//
//
//    //过滤
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        request.setCharacterEncoding(encode);
//        response.setContentType("text/html; charset="+encode);
//        //放过去,调用下一个过滤,如果没有就访问资源
//        chain.doFilter(request, response);
//    }
//
//    public void destroy() {
//        encode = null;
//    }
//
//}
