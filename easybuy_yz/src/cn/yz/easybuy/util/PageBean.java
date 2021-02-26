package cn.yz.easybuy.util;

import java.util.List;

public class PageBean<T> {
	private Integer pageNo=1;  //当前页码
	private Integer pageSize;  //每页显示条数
	private Integer totalCount;//总条数
	private Integer totalPage; //总页数  --只读，不可设置
	private List<T> pageList;  //每页显示的对应的数据的集合
	//当前页码判断
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		if(pageNo<1) {
			this.pageNo = 1;
		}else if(pageNo>totalPage &&totalPage>0) {
			this.pageNo = totalPage;
		}else {
			this.pageNo = pageNo;
		}	
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	//只读，因为是只读文件，所以总页数需要通过totalCount（条数）来自动计算出页数
	public Integer getTotalPage() {
		return totalPage;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
}
