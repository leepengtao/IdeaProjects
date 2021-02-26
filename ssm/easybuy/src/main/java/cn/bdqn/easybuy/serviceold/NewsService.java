package cn.bdqn.easybuy.service;

import cn.bdqn.easybuy.dao.Impl.NewsDaoImpl;
import cn.bdqn.easybuy.dao.NewsDao;
import cn.bdqn.easybuy.entity.News;
import cn.bdqn.easybuy.util.PageBean;

import java.util.List;

public class NewsService {
    private NewsDao newsDao = new NewsDaoImpl();
    public List<News> findTopFive(){
        return newsDao.findTopFive();
    }


    /**
     * 根据当前页和页面大小，调用totalCount查询总记录数，调用findTopTen查询记录
     * 并利用pageBean封装，校正页码数
     * @param pageNo 当前页码数（未校正）
     * @param pageSize 页码大小
     * @return pageBean 封装好的页面对象
     */
    public PageBean<News> findTopTen(int pageNo, int pageSize){
        PageBean<News> pageBean = new PageBean<News>();
        pageBean.setPageSize(pageSize); // 传入规定好的页面大小
        int totalCount = newsDao.totalCount();// 查询总记录数
        pageBean.setTotalCount(totalCount);// 传入总记录数
        pageBean.setPageNo(pageNo); // 传入当前页码

        /*传入的当前页数pageNo，和页码大小， 其中当前页数一定要从pageBean中获取， 因为页数
        有可能小于0，所以PageBean中要做页码判断，不能小于1，不能大于总页数
        pageSize随意
        */
        // 将所查询的新闻以列表的形式存储在pageBean中，内部进行分页加工再返回
        List<News> pageList = newsDao.findTopTen(pageBean.getPageNo(), pageBean.getPageSize());
        pageBean.setPageList(pageList);
        return pageBean;
    }

    public int delById(int newsId) {
        return newsDao.delById(newsId);
    }

    public News findById(int newsId) {
        return newsDao.findById(newsId);
    }

    public int ModById(News news) {
        return newsDao.ModById(news);
    }

    // 增
    public int addNews(News news) {
        return newsDao.addNews(news);
    }
}