package cn.yz.easybuy.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 
 * �������������ļ�����Ҫ��������������UTF-8
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(urlPatterns="/*",initParams=@WebInitParam(name="encode",value="UTF-8"))
public class EncodingFilter implements Filter {
	private String encode;
   
    public EncodingFilter() {
    }

	//����
	public void destroy() {
		encode=null;
	}

	//����
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charser="+encode);
		//�Ź�ȥ��������һ�����ˣ����û�оͷ�����Դ
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		encode=fConfig.getInitParameter("encode");
	}

}
