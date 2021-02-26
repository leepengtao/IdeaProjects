package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yz.easybuy.entity.CategoryVo;
import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.entity.ShoppingCar;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.service.CategoryService;
import cn.yz.easybuy.service.ProductService;

/**
 * ���ﳵ��ɾ�Ĳ�
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService=new ProductService();
    private CategoryService categoryService=new CategoryService(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opr=request.getParameter("opr");
		// ���Ŀ¼��Ϣ
		List<CategoryVo> categoryVo = categoryService.findAllCategory();
		request.setAttribute("categoryVo", categoryVo);
		if("showPro".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Product product = productService.findById(id);
			request.setAttribute("product", product);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
		}
		//��ӹ��ﳵ
		HttpSession session = request.getSession();
		if("addCar".equals(opr)) {
			String proNum = request.getParameter("proNum");
			int num=Integer.parseInt(proNum);
			String proId = request.getParameter("proId");
			int id=Integer.parseInt(proId);
			//���жϵ�¼
			User user = (User) session.getAttribute("loginUser");
			if(user==null) {
				response.sendRedirect("Login.jsp");
			}else {
				//���ж�֮ǰ��û�й��ﳵ
				ShoppingCar car=(ShoppingCar) session.getAttribute("car");
				if(null==car) {
					car=new ShoppingCar();
				}
				Product product = productService.findById(id);
				car.addItem(product, num);
				session.setAttribute("car", car);
				request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
			}
		}
		//�޸Ĺ��ﳵ������
		if("modifyNum".equals(opr)) {
			String proNum = request.getParameter("proNum");
			int num=Integer.parseInt(proNum);
			String proId = request.getParameter("proId");
			int id=Integer.parseInt(proId);
			ShoppingCar car = (ShoppingCar)session.getAttribute("car");
			car.modifyNum(id, num);
			//����session
			session.setAttribute("car", car);
			request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
		}
		//ɾ�����ﳵ������
		if("removeItem".equals(opr)) {				
			String proId = request.getParameter("proId");
			int id=Integer.parseInt(proId);
			ShoppingCar car = (ShoppingCar)session.getAttribute("car");
			car.delItem(id);;
			//����session
			session.setAttribute("car", car);
			request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
