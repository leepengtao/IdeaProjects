package cn.yz.easybuy.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private String content;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {				
		this.createTime = createTime;
	}
	//转化成yyyy-MM-dd的字符串形式的日期
	public String getTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(createTime);
	}
	public News(Integer id, String title, String content, Date createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	public News() {
		super();
	}
	
}
