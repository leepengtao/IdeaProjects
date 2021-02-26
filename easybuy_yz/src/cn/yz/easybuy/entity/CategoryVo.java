package cn.yz.easybuy.entity;

import java.util.List;
/**
 * 目录类（存放种类的类）
 * @author 
 *
 */
public class CategoryVo {
	private Category category;//目录中的种类
	private List<CategoryVo> categoryListVo;//目录中的二级目录
	private int size;
	
	public int getChiledSize() {
		int sum=0;
		for (int i = 0; i < categoryListVo.size(); i++) {
			int a=categoryListVo.get(i).categoryListVo.size();
			if(a==0) {
				sum+=1;
			}else {
				sum+=a;
			}
			
		}
		return sum;		
	}
	
	public int getSize() {
		size = categoryListVo.size();
		return size;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<CategoryVo> getCategoryListVo() {
		return categoryListVo;
	}
	public void setCategoryListVo(List<CategoryVo> categoryListVo) {
		this.categoryListVo = categoryListVo;
	}
	
	
}
