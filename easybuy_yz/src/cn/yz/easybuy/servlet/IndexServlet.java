package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yz.easybuy.entity.CategoryVo;
import cn.yz.easybuy.entity.News;
import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.service.CategoryService;
import cn.yz.easybuy.service.NewsService;
import cn.yz.easybuy.service.ProductService;
import cn.yz.easybuy.util.PageBean;

/**
 * ��ҳ���ţ�Ŀ¼����ҳ�Ĵ���
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService = new NewsService();
	private CategoryService categoryService = new CategoryService();
	private ProductService productService = new ProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ������Ϣ
		 List<News> newsList = newsService.findByNews(1, 5).getPageList();
		// ���Ŀ¼��Ϣ
		List<CategoryVo> categoryVo = categoryService.findAllCategory();
		// ������ҳ����
		int pageNo = 1;
		int pageSize = 6;
	    String pageNoStr=request.getParameter("pageNo"); 
		if(null!=pageNoStr) { 
			pageNo=Integer.parseInt(pageNoStr); 
		}		
		PageBean<Product> findBypage = productService.findBypage(pageNo, pageSize);
		request.setAttribute("newsList", newsList);
		request.setAttribute("categoryVo", categoryVo);
		request.setAttribute("findBypage", findBypage);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		/*
		 * PrintWriter out = response.getWriter(); out.print(findBypage);
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
