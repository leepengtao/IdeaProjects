package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yz.easybuy.entity.News;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.service.NewsService;
import cn.yz.easybuy.util.PageBean;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsService newsService=new NewsService();
		String opr = request.getParameter("opr");
		//新闻查询跳转页面
		if("selectNews".equals(opr)) {
			int pageNo = 1;
			int pageSize = 8;
			String pageNoStr = request.getParameter("pageNo");
			if(null!=pageNoStr) { 
				pageNo = Integer.parseInt(pageNoStr);
			}
			PageBean<News> findByNews = newsService.findByNews(pageNo, pageSize);					
			request.setAttribute("findByNews", findByNews);
			request.getRequestDispatcher("backend/selectNews.jsp").forward(request, response);
		}
		//新增的方法
		if("add".equals(opr)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int ret = newsService.addNews(new News(0, title, content, new Date()));// ???
			if(ret>0) {
				request.getRequestDispatcher("NewsServlet?opr=selectNews").forward(request, response);
			}
		}
		//查找单个的方法的方法
		if("select".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			News news = newsService.findById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("backend/selectOneNews.jsp").forward(request, response);
		}
		//删除的方法
		if("delNews".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			int ret = newsService.delNews(id);
			if (ret>0) {
				request.getRequestDispatcher("NewsServlet?opr=selectNews").forward(request, response);
			}
		}
		//修改页面跳转
		if("updateNews".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			News news = newsService.findById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("backend/updateNews.jsp").forward(request, response);
		}
		//修改的方法
		if("update".equals(opr)) {			
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			News news = newsService.findById(id);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String time = request.getParameter("time");
			Date date=null;
			if(title.equals("")||title.equals(null)) {
				title=news.getTitle();
			}
			if(content.equals("")||content.equals(null)) {
				content=news.getContent();
			}
			if(time.equals("")||time.equals(null)) {
				date=news.getCreateTime();
			}else {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				try {
					date=sdf.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			int ret = newsService.updateNews(new News(id, title, content, date));
			if (ret>0) {
				request.getRequestDispatcher("NewsServlet?opr=selectNews").forward(request, response);
			}			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
