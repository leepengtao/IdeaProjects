package cn.yz.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.CategoryDao;
import cn.yz.easybuy.dao.Impl.CategoryDaoImpl;
import cn.yz.easybuy.entity.Category;
import cn.yz.easybuy.entity.CategoryVo;

public class AdminProductService {
	private CategoryDao categoryDao=new CategoryDaoImpl();
	public Category findById(int id) {
		return categoryDao.findById(id);
		
	}
	//新增目录
	public int addCategory(Category category) {		
		return categoryDao.addCategory(category);		
	}
	//修改目录
	public int updateCategory(String name,int parentId,int type,int id) {
		return categoryDao.updateCategory(name,parentId,type,id);		
	}
	//删除目录
	public int delCategory(int id) {		
		return categoryDao.delCategory(id);		
	}
	//通过parentId删除
	public int delParentIdCategory1(int parentId) {
		return categoryDao.delParentIdCategory1(parentId);
		
	}
	
	//查找选中的通用方法（3级目录，逐级查找）
	public Category fingChoice(String...types) {
		CategoryService categoryService=new CategoryService();
		List<CategoryVo> allCategory = categoryService.findAllCategory();
		String[] type= {null,null,null};
		for (int i = 0; i < types.length; i++) {
			type[i] = types[i];
		}
		Category category =null;
		for (int i = 0; i < allCategory.size(); i++) {
			String oneName=allCategory.get(i).getCategory().getName();
			if(oneName.equals(type[0])) {
				  category = allCategory.get(i).getCategory();					
			}else {
				for (int j = 0; j < allCategory.get(i).getCategoryListVo().size(); j++) {
					String twoName=allCategory.get(i).getCategoryListVo().get(j).getCategory().getName();
					if(twoName.equals(type[1])) {
						category = allCategory.get(i).getCategoryListVo().get(j).getCategory();						
					}else {
						for (int k = 0; k < allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().size(); k++) {
							String threeName=allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().get(k).getCategory().getName();
							if(threeName.equals(type[2])) {
								category = allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().get(k).getCategory();						
							}
						}
					}
				}				
			}
		
		}
		return category;
	}
	
	//直接查找到物品所在的目录树
	public List<Category> categoryList(String type) { 
		CategoryService categoryService=new CategoryService();
		List<CategoryVo> allCategory =categoryService.findAllCategory();
		List<Category> categoryList=new ArrayList<Category>();
		Category category1 =null;
		Category category2 =null;
		Category category3 =null;
		for (int i = 0; i< allCategory.size(); i++) {
			for (int j = 0; j < allCategory.get(i).getCategoryListVo().size(); j++) { 
				for (int k = 0; k <allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().size(); k++) {
					String Name=allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().get(k).getCategory().getName();
					if(Name.equals(type)) {
						category1=allCategory.get(i).getCategory();
						category2=allCategory.get(i).getCategoryListVo().get(j).getCategory();
						category3 =allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().get(k).getCategory(); 
						} 
					} 
				} 
			} 
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		return categoryList; 
		}

}
