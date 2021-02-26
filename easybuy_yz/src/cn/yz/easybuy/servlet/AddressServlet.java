package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yz.easybuy.entity.Address;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.service.AddressService;

/**
 * Servlet implementation class AddressService
 */
@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddressService addressService= new AddressService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String opr=request.getParameter("opr");
		User loginUser = (User) session.getAttribute("loginUser");
		//跳转到购物车2
		if("BuyCar_2".equals(opr)) {			
			if(loginUser==null) {
				response.sendRedirect("Login.jsp");
			}else {
				int userId = loginUser.getId();
				List<Address> addressList = addressService.findAddressByUserId(userId);
				request.setAttribute("addressList", addressList);
				request.getRequestDispatcher("BuyCar_Two.jsp").forward(request, response);
			}
		}
		//新增地址
		if("newAddress".equals(opr)) {
			String address = request.getParameter("address");
			String isDefaultStr = request.getParameter("isDefault");
			int isDefault=Integer.parseInt(isDefaultStr);
			String remark = request.getParameter("remark");			
			int ret=addressService.addAddress(new Address(0, loginUser.getId(), address, new Date(), isDefault, remark));
			if(ret>0) {
				request.getRequestDispatcher("AddressServlet?opr=BuyCar_2").forward(request, response);
			}		
		}
		//删除地址
		if("delAddress".equals(opr)) {						
			int userId = loginUser.getId();
			String addressIdStr=(String) request.getParameter("addressId");
			int addressId=Integer.parseInt(addressIdStr);			
			if(addressId!=0) {
				addressService.delAddress(addressId);
			}
			List<Address> addressList = addressService.findAddressByUserId(userId);			
			request.setAttribute("addressList", addressList);			
			request.getRequestDispatcher("DelAddress.jsp").forward(request, response);			
		}
			
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
