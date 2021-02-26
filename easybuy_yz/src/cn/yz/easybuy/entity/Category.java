package cn.yz.easybuy.entity;

import java.io.Serializable;

/**
 * ÖÖÀà
 * @author 
 *
 */	
public class Category implements Serializable{
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer type;
	private String iconClass;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public Category(Integer id, String name, Integer parentId, Integer type, String iconClass) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
