package cn.bdqn.easybuy.entity;

import java.io.Serializable;
/**
 * 购物车的列表项
 * 表格上每行
 * @author WindLin
 *
 */
public class ShoppingCartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1815807073469626619L;
	private Product product;//商品
	private int quantity;//数量
	private double cost;//表格上每行价格

	public ShoppingCartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost = product.getPrice() * quantity; //每行价格= 商品单价*商品数量
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	//设置数量时自动计算cost
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.cost = product.getPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getCost() {
		return cost;
	}


}
