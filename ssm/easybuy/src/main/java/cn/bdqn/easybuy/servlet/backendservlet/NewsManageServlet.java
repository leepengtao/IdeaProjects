package cn.bdqn.easybuy.servlet.backendservlet;

import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.service.NewsService;
import cn.bdqn.easybuy.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/NewsManageServlet")
public class NewsManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");

        // 复用NewsService里的方法进行显示
        NewsService newsService = new NewsService();
        // 分页显示所有新闻
        if ("showall".equals(opr)) {
            int pageSize = 10;  // 每页显示10个
            int pageNo = 1;
            String pageNoStr = request.getParameter("pageNo");
            if (null != pageNoStr) {
                pageNo = Integer.parseInt(pageNoStr);   // 如有当前页, 则传入当前页
            }
            PageBean<News> newsPage = newsService.findTopTen(pageNo, pageSize);
            request.setAttribute("newsPage", newsPage);
            request.getRequestDispatcher("/backend/NewsManage.jsp").forward(request, response);
        }


        // 删除一条新闻
        if ("delNews".equals(opr)) {
            String newsIdStr = request.getParameter("newsId");
            int newsId = Integer.parseInt(newsIdStr);
            int ret = newsService.delById(newsId);
            if (ret > 0) {
                request.getRequestDispatcher("NewsManageServlet?opr=showall").forward(request,response);
            }
        }
        // 批量删除
        if ("delSelected".equals(opr)) {
            String[] userIdStrs = request.getParameterValues("uid");
            /*for (int i = 0; i<userIdStrs.length;i++) {
                int ret = userService.delUserById(Integer.parseInt(userIdStrs[i]));
                int[] rets = new int[];
                rets
            }*/ // 这段代码本想循环调用删除方法,

            int delFailCount = 0;
            for (String id : userIdStrs) {
                int ret = newsService.delById(Integer.parseInt(id));
                if (ret == 0) {
                    delFailCount++;
                }
            }
            request.setAttribute("delFailCount",delFailCount);
            request.getRequestDispatcher(request.getContextPath()+"/NewsManageServlet?opr=showall").forward(request,response);
        }

        // 改
        // 跳转到修改新闻页面
        if ("editNews".equals(opr)) {
            String newsIdStr = request.getParameter("newsId");
            int newsId = Integer.parseInt(newsIdStr);
            News news = newsService.findById(newsId);
            if (null != news) {
                request.setAttribute("news",news);
                request.getRequestDispatcher("/backend/modNews.jsp").forward(request,response);
            }
        }
        // 修改新闻
        if ("ModNews".equals(opr)) {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            String title = request.getParameter("title");
            String content = request.getParameter("content"); // 应该用文件输入流， 以后再改
            News news = new News(id,title,content,new Date());  // 算上修改时间
            int ret = newsService.ModById(news);
            if (ret > 0) {
                request.getRequestDispatcher("NewsManageServlet?opr=showall").forward(request,response);
            }
        }

        // 增
        if ("addNews".equals(opr)) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            News news = new News(title, content,new Date());
            int ret = newsService.addNews(news);
            if (ret > 0) {
                request.getRequestDispatcher("NewsManageServlet?opr=showall").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}