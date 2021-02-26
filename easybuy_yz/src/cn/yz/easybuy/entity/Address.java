package cn.yz.easybuy.entity;

import java.util.Date;

public class Address {
	private Integer id;
	private Integer userId;
	private String userAddress;//�û���ַ
	private Date createTime; //����ʱ��
	private Integer isDefault;//�Ƿ���Ĭ�ϵ�ַ��1:�� 0��
	private String remark;//��ע
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
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Address(Integer id, Integer userId, String userAddress, Date createTime, Integer isDefault, String remark) {
		super();
		this.id = id;
		this.userId = userId;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
