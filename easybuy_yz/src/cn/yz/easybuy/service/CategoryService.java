package cn.yz.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.CategoryDao;
import cn.yz.easybuy.dao.Impl.CategoryDaoImpl;
import cn.yz.easybuy.entity.Category;
import cn.yz.easybuy.entity.CategoryVo;

public class CategoryService {
	private CategoryDao categoryDao=new CategoryDaoImpl();
	public List<CategoryVo> findAllCategory(){
		//����һ��Ŀ¼�б�Aװһ��Ŀ¼
		List<CategoryVo> categoryVoList=new ArrayList<CategoryVo>();
		List<Category> categoryList1=categoryDao.findByParentId(0);//ͨ��0�ҵ����е�һ��Ŀ¼�µ�Ԫ��
		for (Category category : categoryList1) {
			//һ��Ŀ¼�µ�ÿһ��Ԫ�ض�����һ������
			CategoryVo cv=new CategoryVo();
			cv.setCategory(category);
			
			//��������һ��Ŀ¼�б�Bװ����Ŀ¼
			List<CategoryVo> categoryVoList2=new ArrayList<CategoryVo>();
			List<Category> categoryList2=categoryDao.findByParentId(category.getId());
			for (Category category2 : categoryList2) {
				CategoryVo cv2=new CategoryVo();
				cv2.setCategory(category2);
				
				List<CategoryVo> categoryVoList3=new ArrayList<CategoryVo>();
				List<Category> categoryList3=categoryDao.findByParentId(category2.getId());
				for (Category category3 : categoryList3) {
					CategoryVo cv3=new CategoryVo();
					cv3.setCategory(category3);
					categoryVoList3.add(cv3);
				}
				cv2.setCategoryListVo(categoryVoList3);
				categoryVoList2.add(cv2);
			}
			cv.setCategoryListVo(categoryVoList2);
			categoryVoList.add(cv);
		}
		return categoryVoList;			
	}
	public int addCategory(Category category) {		
		return categoryDao.addCategory(category);		
	}
	
}
