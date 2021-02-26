package cn.yz.easybuy.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	private Integer id;
	private String loginName; //登录名
	private String userName;  //昵称
	private String password; //登录密码  
	private Integer sex;     //性别 0 女 1 男
	private String identityCode;   //身份证
	private String email;          //邮箱
	private String mobile;         //电话
	private Integer type;          //权限 0 普通用户 1 管理员
	
	public String getGender() {
		String gender=null;
		if(this.sex==0) {
			gender="女";
		}else {
			gender="男";
		}
		return gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public User(Integer id, String loginName, String userName, String password, Integer sex, String identityCode,
			String email, String mobile, Integer type) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
	}
	public User(String loginName, String userName, String password, Integer sex, String identityCode, String email,
			String mobile, Integer type) {
		super();
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
	}
	public User() {
		super();
	}
	
}
