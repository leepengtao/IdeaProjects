package cn.bdqn.easybuy.servlet.backendservlet;

import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.service.UserService;
import cn.bdqn.easybuy.util.PageBean;
import cn.bdqn.easybuy.util.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserManageServlet")
public class UserManageServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        String opr = request.getParameter("opr");// 获取操作
        // 查找所有用户
        if ("showall".equals(opr)) {
            // 3.用户分页
            int pageNo = 1;
            String pageNoStr = request.getParameter("pageNo");
            if (null!=pageNoStr) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            int pageSize = 10;
            PageBean<User> userPage = userService.findByPage(pageNo, pageSize);
            request.setAttribute("userPage", userPage);
            request.getRequestDispatcher("Administrator.jsp").forward(request,response);
        }

        // 模糊查询
        if ("searchLike".equals(opr)) {
            String search = request.getParameter("search");
            int pageNo = 1;
            String pageNoStr = request.getParameter("pageNo");
            if (null!=pageNoStr) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            int pageSize = 10;
            PageBean<User> userPage = userService.searchByPage(pageNo, pageSize,search);
            request.setAttribute("userPage", userPage);
            request.getRequestDispatcher("Administrator.jsp").forward(request,response);
        }


        // 增：
        // 跳转至添加用户
        if ("jumptoadd".equals(opr)) {
            request.getRequestDispatcher("backend/add.jsp").forward(request,response);
        }
        // 修改注册方法，这里可以添加是否是管理员，而且添加成功后跳转到用户查询页，故不能直接用register
        if ("addUser".equals(opr)) {
            // 获取表单参数
            String loginName = request.getParameter("loginName");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String identityCode = request.getParameter("identityCode");
            String sexStr = request.getParameter("sex");
            String typeStr = request.getParameter("type");
            int sex = Integer.parseInt(sexStr);
            int type = Integer.parseInt(typeStr);
            // 使用MD5加密
            password = SecurityUtils.md5Hex(password);
            User user = new User(null,loginName, userName, password, sex, identityCode, email, mobile, type);

            System.out.println(user.getLoginName());
            int ret = userService.addUser(user);
            if (ret>0) {
                request.getRequestDispatcher("UserManageServlet?opr=showall").forward(request,response);
            }
        }


        // 删
        if ("delUser".equals(opr)) {
            String userIdStr = request.getParameter("userId");
            int id = Integer.parseInt(userIdStr);
            int ret = userService.delUserById(id);
            if (ret > 0) {
                request.getRequestDispatcher("UserManageServlet?opr=showall").forward(request,response);
            }
        }

        // 批量删
        if ("delSelected".equals(opr)) {
            String[] userIdStrs = request.getParameterValues("uid");
            /*for (int i = 0; i<userIdStrs.length;i++) {
                int ret = userService.delUserById(Integer.parseInt(userIdStrs[i]));
                int[] rets = new int[];
                rets
            }*/ // 这段代码本想循环调用删除方法,

            int delFailCount = 0;
            for (String id : userIdStrs) {
                int ret = userService.delUserById(Integer.parseInt(id));
                if (ret == 0) {
                    delFailCount++;
                }
            }
            request.setAttribute("delFailCount",delFailCount);
            request.getRequestDispatcher(request.getContextPath()+"/UserManageServlet?opr=showall").forward(request,response);
        }

        // 改
        // 跳转至改用户页面
        if ("jumpToModUser".equals(opr)) {
            String loginName = request.getParameter("loginName");
            User user = userService.findByLoginName(loginName);
            request.setAttribute("user",user);
            request.getRequestDispatcher("backend/modUser.jsp").forward(request,response);
        }
        // 修改用户
        if ("modUser".equals(opr)) {
            String idStr = request.getParameter("loginName");

            // 获取修改用户的登录名
            int id = Integer.parseInt(idStr);
            // 获取表单数据
            String userName = request.getParameter("userName");
            String loginName = request.getParameter("loginName");
            String password = request.getParameter("password");
            String sexStr = request.getParameter("sex");
            int sex = Integer.parseInt(sexStr);
            String identityCode = request.getParameter("identityCode");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String typeStr = request.getParameter("type");
            int type = Integer.parseInt(typeStr);

            // 使用MD5加密
            password = SecurityUtils.md5Hex(password);
            User user = new User(id,loginName,userName,password,sex,identityCode,email,mobile,type);

            // 调用serviec修改数据
            int ret = userService.modUser(user);
            request.getRequestDispatcher("backend/modUser.jsp").forward(request,response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
