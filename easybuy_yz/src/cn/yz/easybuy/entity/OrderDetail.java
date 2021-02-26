package cn.yz.easybuy.entity;
/**
 * 订单详情
 * @author
 *
 */
public class OrderDetail {
	private Integer id;
	private String serialNum;//订单号
	private Integer productId; //商品主键
	private Integer num; //数量
	private Double cost; //消费

	public OrderDetail(Integer id, String serialNum, Integer productId, Integer num, Double cost) {
		super();
		this.id = id;
		this.serialNum = serialNum;
		this.productId = productId;
		this.num = num;
		this.cost = cost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
