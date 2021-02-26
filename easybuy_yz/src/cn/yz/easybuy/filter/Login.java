package cn.yz.easybuy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.yz.easybuy.entity.User;

/**
 * Servlet Filter implementation class Login
 */
//@WebFilter("/*")
public class Login implements Filter {

   
    public Login() {
    }
    
	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
		//request.getServletContext(String);
		HttpServletRequest req=(HttpServletRequest) request;
		String uri=req.getRequestURI();
		if(uri.contains("/Login.jsp")||uri.contains("/Index.jsp")||uri.contains("/cn.yz.easybuy")||uri.contains("/WEB-INF")||uri.contains("/META-INF")||uri.contains("/servlet")||uri.contains("/css")||uri.contains("/images")||uri.contains("/js")||uri.contains("/Number.jsp")) {
			chain.doFilter(request, response);
		}else {
			User user = (User) req.getSession().getAttribute("loginUser");
			if(user!=null) {
				chain.doFilter(request, response);
			}else {
				req.getRequestDispatcher("Login.jsp").forward(req, response);
			}
		}		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
