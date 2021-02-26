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

import cn.yz.easybuy.entity.Order;
import cn.yz.easybuy.entity.OrderDetail;
import cn.yz.easybuy.entity.ShoppingCar;
import cn.yz.easybuy.entity.ShoppingCarItem;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.service.OrderDetailService;
import cn.yz.easybuy.service.OrderService;
import cn.yz.easybuy.util.UUIDUtil;

/**
 * 订单增删改查
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService order=new OrderService();
	private OrderDetailService orderDetail=new OrderDetailService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opr = request.getParameter("opr");
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		ShoppingCar car = (ShoppingCar) session.getAttribute("car");
		//订单增加
		if("addOrder".equals(opr)) {
			String address = request.getParameter("address");
			Integer userId = loginUser.getId();
			String loginName = loginUser.getLoginName();
			Double totalCost = car.getTotalCost();
			String serialNumber=UUIDUtil.getUUId();
			int ret = order.addOrder(new Order(0, userId, loginName, address, null, totalCost, serialNumber));
			List<ShoppingCarItem> items = car.items;
			for (ShoppingCarItem item : items) {
				int productId = item.getProduct().getId();
				int num = item.getNum();
				Double cost = item.getCost();
				orderDetail.addOrderDetail(new OrderDetail(0, serialNumber, productId, num, cost));
			}
			if(ret>0) {
				session.removeAttribute("car");
				request.setAttribute("serialNumber", serialNumber);
				request.getRequestDispatcher("BuyCar_Three.jsp").forward(request, response);
			}			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
