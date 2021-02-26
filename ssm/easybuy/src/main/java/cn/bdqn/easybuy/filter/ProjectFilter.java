package cn.bdqn.easybuy.filter;

import cn.bdqn.easybuy.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ProjectFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // ServletRequest是一个接口, 只有HttpServletRequest才成, 后者是前者的子接口
        // 0. 强制转换
        HttpServletRequest request = (HttpServletRequest) req;


        // 可以获取url路径和uri路径, url是长的, uri是短的
        // 1.获取资源请求路径
        String uri = request.getRequestURI();

        // 2.判断是否包含登录相关资源路径, 注意排除掉css等
        if (uri.contains("/Login.jsp") || uri.contains("/index.jsp")
                || uri.contains("/UserServlet")|| uri.contains("/ProductServlet")
                || uri.contains("/Number.jsp") || uri.contains("/Register.jsp")
                || uri.contains("/css/") || uri.contains("/js/")
                || uri.contains("/fonts/") || uri.contains("/images/")
                ){
            // 包含, 用户想登录, 放行
            chain.doFilter(req, resp);

        } else {
            // 不包含, 需要验证用户是否登录
            Object user = request.getSession().getAttribute("userLogin");
            if (user != null) {
                // 登录了, 放行
                resp.setCharacterEncoding("UTF-8");
                chain.doFilter(req, resp);
            } else {
                // 没有登录, 跳转登录页面
                // request.setAttribute("login_msg", "您尚未登录, 请登录");
                request.getRequestDispatcher("/Login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
