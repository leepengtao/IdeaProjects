package cn.yz.easybuy.entity;

import java.util.List;
/**
 * Ŀ¼�ࣨ���������ࣩ
 * @author 
 *
 */
public class CategoryVo {
	private Category category;//Ŀ¼�е�����
	private List<CategoryVo> categoryListVo;//Ŀ¼�еĶ���Ŀ¼
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
