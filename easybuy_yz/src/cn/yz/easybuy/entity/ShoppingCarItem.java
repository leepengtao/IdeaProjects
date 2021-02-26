package cn.yz.easybuy.entity;
/**
 * 购物车内每一个品项商品信息
 * @author
 *
 */
public class ShoppingCarItem {
	private Product product;
	private Integer num;  //产品数量
	private Double cost;  //该产品总价钱
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
		this.cost=num*product.getPrice();
	}
	public Double getCost() {
		return cost;
	}
	public ShoppingCarItem(Product product, Integer num) {
		super();
		this.product = product;
		this.num = num;
		this.cost=num*product.getPrice();
	}
	
}
