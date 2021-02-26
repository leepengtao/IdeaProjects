package cn.bdqn.easybuy.util;

/**
 *  分页的工具类
 *  泛型类
 * @author Lin
 *
 */

import java.util.List;

public class PageBean<T> {

	private int pageNo = 1; // 当前页码
	private int pageSize = 8; // 每页的显示数量
	private int totalCount; // 总记录数
	private int totalPages; // 总页数  -- 只读
	private List<T> pageList; // 每页对应数据的集合, 使用列表

	public int getPageNo() {
		return pageNo;
	}

	// 判断当前页码
	public void setPageNo(int pageNo) { // 前端传入当前页码
		if(pageNo<1)	// 如果当前页码为0或负数, 
			this.pageNo = 1;	// 则显示第一页;
		else if(pageNo>totalPages && totalPages>0) // 如果当前页数大于总页数且总页数大于0
			this.pageNo = totalPages; // 则显示最后一页. 
		else
			this.pageNo = pageNo;	//  如果经数据库查询总页数为0, 则使用成员变量的默认值 (1)
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//自动计算总页数
		/*
		if( totalCount%pageSize==0)
			this.totalPages = totalCount/pageSize;
		else
			this.totalPages = totalCount/pageSize+1;
		*/
		totalPages = totalCount%pageSize==0? 	// 总页数对页条数的余数是否为0?(判断是否除尽)
				totalCount/pageSize:totalCount/pageSize+1;	// 如果为0, 总页数=总记录数/每页显示条数, 不为0, 则增加最后一页放余数
	}

	// 只读, 总页数不能传入
	public int getTotalPages() {
		return totalPages;
	}


	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {	// 这只是一个列表, 记录了某类的地址, 指针的集合
		this.pageList = pageList;
	}

}
