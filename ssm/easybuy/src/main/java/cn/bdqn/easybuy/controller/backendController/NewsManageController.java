package cn.bdqn.easybuy.controller.backendController;

import cn.bdqn.easybuy.Service.NewsService;
import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

@Controller
@RequestMapping("/NewsManage")
public class NewsManageController {

    @Autowired
    private NewsService newsService;

    // 分页显示所有新闻
    @RequestMapping("/showAll")
    public String showAll(@Param("pageNo")Integer pageNo, ModelMap modelMap) {
        int pageSize = 5;  // 每页显示10个
        if (pageNo == null) {
            pageNo = 1;
        }
        PageBean<News> newsPage = newsService.findTopTen(pageNo, pageSize);
        modelMap.addAttribute("newsPage",newsPage);
        return "/backend/NewsManage";
    }

    // Ajax分页显示新闻
    @RequestMapping("/showPages")
    public @ResponseBody PageBean showPages(@Param("pageNo")Integer pageNo) {
        int pageSize = 5;
        PageBean<News> newsPage = newsService.findTopTen(pageNo, pageSize);
        return newsPage;
    }



    // 删除一条新闻
    /*@RequestMapping("/delNews")
    public String delNews(@Param("newsId")Integer newsId) {
        int ret = newsService.delById(newsId);
        System.out.println("删除了"+ret+"条新闻");
        return "redirect:/NewsManage/showAll";
    }*/

    // ajax删除一条新闻
    @RequestMapping("/delNews")
    public @ResponseBody PageBean delNews(@Param("pageNo")Integer pageNo,@Param("newsId")Integer newsId) {
        int ret = newsService.delById(newsId);
        System.out.println("删除了" + ret + "条新闻");
        PageBean<News> news = newsService.findTopTen(pageNo, 10);
        return news;
    }


    // 批量删除
    @RequestMapping("/delSelected")
    public String delSelected(@Param("uid")Integer[] uid, ModelMap modelMap) {
        int delFailCount = 0;
        for (Integer id : uid) {
            int ret = newsService.delById(id);
            if (ret == 0) {
                delFailCount++;
            }
        }
        modelMap.addAttribute("delFailCount",delFailCount);
        return "redirect:/NewsManage/showAll";
    }

    // 改
    // 跳转到修改新闻页面
    @RequestMapping("/jumpToModNews")
    public String jumpToModNews(@Param("newsId")Integer newsId, ModelMap modelMap) {
        News news = newsService.findById(newsId);
        modelMap.addAttribute("news", news);
        return "backend/modNews";
    }

    // 修改新闻
    @RequestMapping(path = "/modNews",method = RequestMethod.POST)
    public String modNews(@Param("id")Integer id,@Param("title")String title,@Param("content")String content) {
        News news = new News(id,title,content,new Date(new java.util.Date().getTime()));// util.date是sql.date的父类, 所以能传进去
        int ret = newsService.ModById(news);
        System.out.println("修改了"+ret+"条新闻");
        return "redirect:/NewsManage/showAll";
    }

    // 增
    @RequestMapping(value = "/addNews",method = RequestMethod.POST)
    public String addNews(@Param("title")String title,@Param("content")String content) {
        News news = new News(title, content,new Date(new java.util.Date().getTime()));
        int ret = newsService.addNews(news);
        System.out.println("增加了"+ret+"条新闻");
        return "redirect:/NewsManage/showAll";
    }
}
