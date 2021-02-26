package cn.yz.easybuy.entity;
/**
 * ���ﳵ��ÿһ��Ʒ����Ʒ��Ϣ
 * @author
 *
 */
public class ShoppingCarItem {
	private Product product;
	private Integer num;  //��Ʒ����
	private Double cost;  //�ò�Ʒ�ܼ�Ǯ
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
