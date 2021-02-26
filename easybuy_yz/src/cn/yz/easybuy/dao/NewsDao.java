package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.News;

public interface NewsDao{
	//分页查找新闻
	List<News> findByNews(int pageNo, int pageSize);
	//找到所有新闻总数
	int findTotalNews();
	//通过id找新闻的方法
	News findById(int id);
	//新增新闻
	int addNews(News news);
	//修改新闻的方法
	int updateNews(News news);
	//删除新闻的方法
	int delNews(int id);
}
