package cn.bdqn.easybuy.Service;

import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.util.PageBean;

import java.util.List;

public interface NewsService {



    // 查找前5个新闻
    public List<News> findTopFive();


    // 删除单个
    public int delById(int newsId);
    // 查找单个
    public News findById(int newsId);
    // 修改
    public int ModById(News news);

    // 增
    public int addNews(News news);

    // 新闻管理: 按页查询, 每页10个
    PageBean<News> findTopTen(Integer pageNo, int pageSize);

}
