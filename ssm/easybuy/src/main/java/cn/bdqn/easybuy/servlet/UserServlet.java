package cn.bdqn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.service.UserService;
import cn.bdqn.easybuy.util.SecurityUtils;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        HttpSession session = request.getSession();
        if("login".equals(opr)) {
            //先判断code 验证码,如果验证码不正确
            String code = request.getParameter("code");
            //获取 session Number.jsp生成的
            String numrand = (String) session.getAttribute("numrand");
            if(code.equals(numrand)) {
                String loginName = request.getParameter("loginName");
                String password = request.getParameter("password");
                User userLogin = userService.login(loginName, password);
                if(null!=userLogin) {
                    session.setAttribute("userLogin", userLogin);
                    response.sendRedirect("index.jsp");
                }else {
                    request.setAttribute("msg", "用户名或密码错误,请重新输入");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("msg", "验证码不正确,请重新输入");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
        if("register".equals(opr)) {
            // 获取表单参数
            String loginName = request.getParameter("loginName");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String identityCode = request.getParameter("identityCode");
            String sexStr = request.getParameter("sex");
            int sex = Integer.parseInt(sexStr);
            int type = 0;
            // 使用MD5加密
            password = SecurityUtils.md5Hex(password);
            User user = new User(loginName, userName, password, sex, identityCode, email, mobile, type);

            System.out.println(user.getLoginName());
            int ret = userService.addUser(user);
            if (ret>0) {
                request.getRequestDispatcher("Login.jsp").forward(request,response);
            }
        }

        // 登出，消除会话， 直接转发回首页
        if ("logout".equals(opr)) {
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
