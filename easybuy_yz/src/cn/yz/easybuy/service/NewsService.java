package cn.yz.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.NewsDao;
import cn.yz.easybuy.dao.Impl.NewsDaoImpl;
import cn.yz.easybuy.entity.News;
import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.util.PageBean;

public class NewsService {
	
	NewsDao newsDao=new NewsDaoImpl();
	//分页查询新闻
	public PageBean<News> findByNews(int pageNo, int pageSize) {
		PageBean<News> pbNews=new PageBean<News>();
		List<News> list=new ArrayList<News>();						
		pbNews.setPageSize(pageSize);
		int totalNews=newsDao.findTotalNews();
		pbNews.setTotalCount(totalNews);
		pbNews.setPageNo(pageNo);
		list=newsDao.findByNews(pbNews.getPageNo(), pageSize);
		pbNews.setPageList(list);		
		return pbNews;
	}
	//新增新闻
	public int addNews(News news) {
		return newsDao.addNews(news);		
	}
	//通过id找新闻的方法
	public News findById(int id) {
		return newsDao.findById(id);		
	}
	//修改的方法
	public int updateNews(News news) {
		return newsDao.updateNews(news);		
	}
	//删除的方法
	public int delNews(int id) {
		return newsDao.delNews(id);		
	}
}
