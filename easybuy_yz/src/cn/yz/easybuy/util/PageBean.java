package cn.yz.easybuy.util;

import java.util.List;

public class PageBean<T> {
	private Integer pageNo=1;  //��ǰҳ��
	private Integer pageSize;  //ÿҳ��ʾ����
	private Integer totalCount;//������
	private Integer totalPage; //��ҳ��  --ֻ������������
	private List<T> pageList;  //ÿҳ��ʾ�Ķ�Ӧ�����ݵļ���
	//��ǰҳ���ж�
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
	//ֻ������Ϊ��ֻ���ļ���������ҳ����Ҫͨ��totalCount�����������Զ������ҳ��
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
