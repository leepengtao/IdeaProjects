package cn.bdqn.easybuy.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bdqn.easybuy.entity.*;
import cn.bdqn.easybuy.service.ProductService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	public static final long serialVersionUID = 1L;
       
	private ProductService productService = new ProductService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String opr = request.getParameter("opr");
			// 显示商品详情
			if ("showPro".equals(opr)) {
				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				Product product = productService.findById(id);
				request.setAttribute("product", product);
				request.getRequestDispatcher("Product.jsp").forward(request, response);
				
			}

			// 添加购物车
		HttpSession session = request.getSession();
		if ("addCart".equals(opr)) {
			String proNumstr = request.getParameter("proNum");
			int proNum = Integer.parseInt(proNumstr);
			String proIdStr = request.getParameter("proId");
			int proId = Integer.parseInt(proIdStr);
			// 先判断登录
			User userLogin = (User) session.getAttribute("userLogin");
			if (null == userLogin) {
				response.sendRedirect("Login.jsp");
			}else{
				// 再判断之前有没有购物车
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				if(null == cart){
					cart = new ShoppingCart();
				}
				Product product = productService.findById(proId);// 通过商品Id找到商品, 并返回
				cart.addItem(product, proNum); // 将返回的商品添加到购物车中
				// 放入session中, 最重要, 别忘记, 和return null一样
				session.setAttribute("cart", cart);
				// 做完所有工作以后转跳到购物车页面
				request.getRequestDispatcher("BuyCar.jsp").forward(request,response);
			}
		}

		// 购物车中更改商品数量
		if ("modifyQuantity".equals(opr)) {
			String proNumStr = request.getParameter("proNum");
			String proIdStr = request.getParameter("proId");
			int proNum = Integer.parseInt(proNumStr);// 获得商品数量
			int proId = Integer.parseInt(proIdStr);// 获得商品id
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");// 获得购物车
			cart.modifyQuantity(proId, proNum);
			// 放入session
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
		}

		// 删除一件商品
		if ("removeItem".equals(opr)) {
			String proIdStr = request.getParameter("proId");
			int proId = Integer.parseInt(proIdStr);
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");// 获得购物车
			cart.removeItem(proId);
			// 放入session
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("BuyCar.jsp").forward(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
