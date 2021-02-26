package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.News;

public interface NewsDao{
	//��ҳ��������
	List<News> findByNews(int pageNo, int pageSize);
	//�ҵ�������������
	int findTotalNews();
	//ͨ��id�����ŵķ���
	News findById(int id);
	//��������
	int addNews(News news);
	//�޸����ŵķ���
	int updateNews(News news);
	//ɾ�����ŵķ���
	int delNews(int id);
}
