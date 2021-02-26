package cn.bdqn.easybuy.entity;

import java.io.Serializable;

public class Category implements Serializable{
	
	private static final long serialVersionUID= 4327289510918401921L;
	private int id;
	private String name;
	private int parentId;
	private int type;
	private String iconClass;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public Category() {
		super();
	}

	public Category(int id, String name, int parentId, int type, String iconClass) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}
	
	

}
