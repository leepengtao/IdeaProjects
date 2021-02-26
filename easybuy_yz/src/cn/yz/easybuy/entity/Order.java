package cn.yz.easybuy.entity;

import java.util.Date;
/**
 * 订单类
 * @author 
 *
 */
public class Order {
	private Integer id;
	private Integer userId;
	private String loginName;
	private String userAddress;//用户地址
	private Date createTime; //创建时间
	private Double cost; //总消费
	private String serialNumber;//订单号
	
	public Order(Integer id, Integer userId, String loginName, String userAddress, Date createTime, Double cost,
			String serialNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginName = loginName;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.cost = cost;
		this.serialNumber = serialNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
