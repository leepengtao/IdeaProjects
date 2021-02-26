package cn.bdqn.easybuy.controller;

import cn.bdqn.easybuy.Service.CategoryService;
import cn.bdqn.easybuy.Service.NewsService;
import cn.bdqn.easybuy.Service.ProductService;
import cn.bdqn.easybuy.entity.CategoryVo;
import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Index")
public class IndexController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/index")
    public String findTopFive(@RequestParam int pageNo, Model model) {
        System.out.println("控制器中的查询所有");
        // 1.前5条新闻
        List<News> newsList = newsService.findTopFive();
        // 2.所有的三层目录
        List<CategoryVo> cvList = categoryService.findAllCategory();

        // 3.商品分页
        int pageSize = 6;
        PageBean<Product> proPage = productService.findByPage(pageNo, pageSize);

        model.addAttribute("newsList",newsList);
        model.addAttribute("cvList",cvList);
        model.addAttribute("proPage",proPage);
        return "index";
    }


    // ajax实现商品翻页
    @RequestMapping(value = "/findProduct")
    public @ResponseBody PageBean findProduct(@Param("pageNo") Integer pageNo) {
//        Integer pageNo = Integer.parseInt(pageNoStr);
        System.out.println("findProduct方法执行了");
        int pageSize = 6;
        PageBean<Product> proPage = productService.findByPage(pageNo, pageSize);
        return proPage;
    }
}
