package cn.bdqn.easybuy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class News implements Serializable {

	
	private static final long serialVersionUID = -7922019301698914506L;
	private int id;
	private String title;
	private String content;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // 创建一个格式化对象 日期由Servlet创建
		// sdf.format(news.getCreateTime());   // 将字符串格式化成字符串类型
		return (sdf.format(createTime));
	}
	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public News(int id, String title, String content, Date createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}

	public News(String title, String content, Date createTime) {
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
}
