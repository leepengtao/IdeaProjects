package cn.bdqn.easybuy.Service.Impl;

import cn.bdqn.easybuy.Service.NewsService;
import cn.bdqn.easybuy.dao.NewsDao;
import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> findTopFive() {
        return newsDao.findTopFive();
    }

    @Override
    public int delById(int newsId) {
        return newsDao.delById(newsId);
    }

    @Override
    public News findById(int newsId) {
        return newsDao.findById(newsId);
    }

    @Override
    public int ModById(News news) {
        return newsDao.ModById(news);
    }

    @Override
    public int addNews(News news) {
        return newsDao.addNews(news);
    }

    @Override
    public PageBean<News> findTopTen(Integer pageNo, int pageSize) {
        PageBean<News> pageBean = new PageBean<News>();
        pageBean.setPageSize(pageSize); // 传入规定好的页面大小
        int totalCount = newsDao.totalCount();// 查询总记录数
        pageBean.setTotalCount(totalCount);// 传入总记录数
        pageBean.setPageNo(pageNo); // 传入当前页码

        List<News> pageList = newsDao.findTopTen((pageBean.getPageNo()-1)* pageBean.getPageSize(),pageBean.getPageSize());
        pageBean.setPageList(pageList);
        return pageBean;
    }
}
