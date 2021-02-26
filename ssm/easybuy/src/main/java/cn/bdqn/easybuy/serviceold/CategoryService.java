package cn.bdqn.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.bdqn.easybuy.dao.CategoryDao;
import cn.bdqn.easybuy.dao.Impl.CategoryDaoImpl;
import cn.bdqn.easybuy.entity.Category;
import cn.bdqn.easybuy.entity.CategoryVo;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDaoImpl();

	public List<CategoryVo> findAllCategory() {
		List<CategoryVo> cateList = new ArrayList<CategoryVo>();
		// 首先查询所有的一级目录
		List<Category> list = categoryDao.findByParentId(0);
		for (Category category : list) {
			CategoryVo cv = new CategoryVo();
			cv.setCategory(category);

			// 封装二级目录集合
			List<CategoryVo> categoryVoList2 = new ArrayList<CategoryVo>();
			// 查询出所有的此一级目录下的二级集合
			List<Category> categoryLev2List = categoryDao.findByParentId(category.getId());
			// 查询出所有的此二级目录下的三级集合
			for (Category cl2 : categoryLev2List) {
				CategoryVo vo2 = new CategoryVo();
				vo2.setCategory(cl2);
				List<CategoryVo> categoryVoList3 = new ArrayList<CategoryVo>();
				List<Category> categoryLev3List = categoryDao.findByParentId(cl2.getId());
				for (Category cl3 : categoryLev3List) {
					CategoryVo vo3 = new CategoryVo();
					vo3.setCategory(cl3);
					categoryVoList3.add(vo3);
				}
				vo2.setCategoryVoList(categoryVoList3);
				categoryVoList2.add(vo2);
			}
			cv.setCategoryVoList(categoryVoList2);
			cateList.add(cv);
		}
		return cateList;
	}

}