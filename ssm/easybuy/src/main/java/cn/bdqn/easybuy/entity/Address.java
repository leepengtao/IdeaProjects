package cn.bdqn.easybuy.entity;


import java.sql.Timestamp;

public class Address {

	private int id;
	private int userId;
	private String address;
	private Timestamp createTime;
	private int isDefault;
	private String remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Address(int id, int userId, String address, Timestamp createTime, int isDefault, String remark) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.createTime = createTime;
        this.isDefault = isDefault;
        this.remark = remark;
    }

    public Address(int userId, String address, Timestamp createTime, int isDefault, String remark) {
		this.userId = userId;
		this.address = address;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}

	public Address(int userId, String address, int isDefault, String remark) {
		this.userId = userId;
		this.address = address;
		this.isDefault = isDefault;
		this.remark = remark;
	}
}
