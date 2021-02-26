package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.Category;

public interface CategoryDao {
	List<Category> findByParentId(int parentId);
	
	int addCategory(Category category);
	
	int updateCategory(String name, int parentId, int type, int id);
	
	int delCategory(int id);
	
	int delParentIdCategory1(int parentId);
	
	public Category findById(int id);
}
