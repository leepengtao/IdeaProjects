package cn.yz.easybuy.entity;

import java.util.List;

public class ProductVo {
	Product product;
	List<Category> categoryList;
	String parentName1;
	String parentName2;
	String parentName3;
	
	public String getParentName1() {
		return parentName1;
	}
	public String getParentName2() {
		return parentName2;
	}
	public String getParentName3() {
		return parentName3;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;		
	}
	public ProductVo(Product product, List<Category> categoryList) {
		super();
		this.product = product;
		this.categoryList = categoryList;
		this.parentName1=categoryList.get(0).getName();
		this.parentName2=categoryList.get(1).getName();
		this.parentName3=categoryList.get(2).getName();
	}
	public ProductVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
