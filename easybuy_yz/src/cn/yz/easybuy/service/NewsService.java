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
	//��ҳ��ѯ����
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
	//��������
	public int addNews(News news) {
		return newsDao.addNews(news);		
	}
	//ͨ��id�����ŵķ���
	public News findById(int id) {
		return newsDao.findById(id);		
	}
	//�޸ĵķ���
	public int updateNews(News news) {
		return newsDao.updateNews(news);		
	}
	//ɾ���ķ���
	public int delNews(int id) {
		return newsDao.delNews(id);		
	}
}
