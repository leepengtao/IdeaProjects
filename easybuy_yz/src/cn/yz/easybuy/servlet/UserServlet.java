package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.service.UserService;
import cn.yz.easybuy.util.PageBean;
import cn.yz.easybuy.util.SecurityUtils;

/**
 * �û���¼��ע��
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService userService=new UserService(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opr = request.getParameter("opr");
		HttpSession session = request.getSession();
		//��¼
		if("login".equals(opr)) {
			//���ж�code��֤�룬�����֤�벻��ȷ
			String code = request.getParameter("code");
			//���session Number.jsp���ɵ���֤��
			String numrand = (String)session.getAttribute("numrand");
			session.removeAttribute("numrand");
			if(code.equals(numrand)) {
				String loginName = request.getParameter("loginName");
				String password = request.getParameter("password");
				User loginUser = userService.login(loginName, password);
				if(loginUser!=null) {
					session.setAttribute("loginUser", loginUser);
					response.sendRedirect("Index.jsp");
				}else {
					request.setAttribute("msg", "�û����������������������");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("msg", "��֤�����벻��ȷ");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
		//ע��
		if("regist".equals(opr)) {
			String code=request.getParameter("code");
			String numrand=(String)session.getAttribute("numrand");
			if(code.equals(numrand)) {
				String loginName = request.getParameter("loginName");
				String userName = request.getParameter("userName");
				String password = SecurityUtils.md5Hex(request.getParameter("password"));
				Integer gender = Integer.parseInt(request.getParameter("gender"));
				String identityCode = request.getParameter("identityCode");
				String email = request.getParameter("email");
				String mobile = request.getParameter("mobile");
				User user=new User(loginName,userName,password,gender,identityCode,email,mobile,0);
				int ret=userService.addUser(user);
				if(ret>0) {
					response.sendRedirect("Login.jsp");
				}else {
					request.setAttribute("msg", "�û����Ѵ��ڣ�ע��ʧ�ܣ�");
					request.getRequestDispatcher("Regist.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("msg", "��֤�����벻��ȷ");
				request.getRequestDispatcher("Regist.jsp").forward(request, response);
			}
		}
		//ע��
		if("logout".equals(opr)) {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}
		//��ѯ��ת
		if("selectUser".equals(opr)) {
			int pageNo = 1;
			int pageSize = 7;
		    String pageNoStr=request.getParameter("pageNo"); 
			if(null!=pageNoStr) { 
				pageNo=Integer.parseInt(pageNoStr); 
			}
			PageBean<User> allUser = userService.findAllUser(pageNo, pageSize);
			request.setAttribute("allUser", allUser);
			request.getRequestDispatcher("backend/selectUser.jsp").forward(request, response);
		}
		//�޸���ת
		if("updateUser".equals(opr)) {					
		    String idStr=request.getParameter("id"); 
		    int id = Integer.parseInt(idStr);
		    User uesr = userService.findUserById(id);		   
		    request.setAttribute("uesr", uesr);
			request.getRequestDispatcher("backend/updateUser.jsp").forward(request, response);
		}
		//�޸���ת
		if("update".equals(opr)) {					
		    String idStr=request.getParameter("id"); 
		    int id = Integer.parseInt(idStr);		    
		    User uesr = userService.findUserById(id);	
		    String loginName=uesr.getLoginName();
		    String userName=request.getParameter("userName"); 
		    String password=request.getParameter("password"); 
		    String sexStr=request.getParameter("sex");
		    int sex = Integer.parseInt(sexStr);
		    String identityCode=request.getParameter("identityCode"); 
		    String email=request.getParameter("email"); 
		    String mobile=request.getParameter("mobile");
		    String typeStr=request.getParameter("type");
		    int type = Integer.parseInt(typeStr);
		    if(userName.equals(null)||userName.equals("")) {
		    	userName=uesr.getUserName();
		    }
		    if(password.equals(null)||password.equals("")) {
		    	password=uesr.getPassword();
		    }else {
		    	password=SecurityUtils.md5Hex(password);
			}
		    if(identityCode.equals(null)||identityCode.equals("")) {
		    	identityCode=uesr.getIdentityCode();
		    }
		    if(email.equals(null)||email.equals("")) {
		    	email=uesr.getEmail();
		    }
		    if(mobile.equals(null)||mobile.equals("")) {
		    	mobile=uesr.getMobile();
		    }
		    userService.updateUser(new User(id, loginName, userName, password, sex, identityCode, email, mobile, type));
			request.getRequestDispatcher("UserServlet?opr=selectUser").forward(request, response);
		}
		//ɾ���û�
		if("delUser".equals(opr)) {
			 String idStr=request.getParameter("id"); 
			 int id = Integer.parseInt(idStr);
			 userService.delUser(id);
			 request.getRequestDispatcher("UserServlet?opr=selectUser").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
