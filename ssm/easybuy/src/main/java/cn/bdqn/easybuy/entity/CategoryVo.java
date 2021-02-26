package cn.bdqn.easybuy.entity;

import java.util.List;

public class CategoryVo {

	private Category category; // 目录对象
	private List<CategoryVo> categoryVoList; // 递归调用自己的集合

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CategoryVo> getCategoryVoList() {
		return categoryVoList;
	}

	public void setCategoryVoList(List<CategoryVo> categoryVoList) {
		this.categoryVoList = categoryVoList;
	}

}
