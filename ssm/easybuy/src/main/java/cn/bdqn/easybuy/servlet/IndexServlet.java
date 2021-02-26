package cn.bdqn.easybuy.servlet;

import cn.bdqn.easybuy.entity.CategoryVo;
import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.service.CategoryService;
import cn.bdqn.easybuy.service.NewsService;
import cn.bdqn.easybuy.service.ProductService;
import cn.bdqn.easybuy.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    private NewsService newsService = new NewsService();
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.前5条新闻
        List<News> newsList = newsService.findTopFive();
        // 2.所有的三层目录
        List<CategoryVo> cvList = categoryService.findAllCategory();
		// 3.商品分页
        int pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (null!=pageNoStr) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		int pageSize = 6;
        PageBean<Product> proPage = productService.findByPage(pageNo, pageSize);

        request.setAttribute("newsList",newsList);
        request.setAttribute("cvList", cvList);
        request.setAttribute("proPage", proPage);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
