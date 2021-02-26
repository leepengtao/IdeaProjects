package cn.bdqn.smbms.util;

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
    private List<T> pageList; // 每页对应数据的集合

    public int getPageNo() {
        return pageNo;
    }

    // 判断当前页码
    public void setPageNo(int pageNo) {
        if(pageNo<1)
            this.pageNo = 1;
        else if(pageNo>totalPages && totalPages>0)
            this.pageNo = totalPages;
        else
            this.pageNo = pageNo;
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
        totalPages = totalCount%pageSize==0?
                totalCount/pageSize:totalCount/pageSize+1;
    }

    // 只读
    public int getTotalPages() {
        return totalPages;
    }


    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

}

