package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.Product;

public interface ProductDao {
	//��ѯ���м�¼(��ҳ����)
	int findTotalCount();
	
	//��ҳ���ݼ��ϲ�ѯ,pageNoҳ�룬pageSizeÿҳ��ʾ������
	List<Product> findByList(int pageNo, int pageSize);
	
	//ͨ��Id�ҵ���Ӧ����Ʒ
	Product findById(int id);
	
	//������Ʒ
	int addProduct(Product product);
	//�޸���Ʒ
	int updateProduct(Product product);
	//ɾ����Ʒ
	int delProduct(int id);
	//ɾ��Ŀ¼��ͬʱɾ��Ŀ¼�µ���Ʒ
	public int delCategoryByProduct(int parentId);

}
