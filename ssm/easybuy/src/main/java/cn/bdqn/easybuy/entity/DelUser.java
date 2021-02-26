package cn.bdqn.easybuy.entity;

public class DelUser {


    private Integer pageNo;
    private Integer userId;

    public DelUser() {
    }

    public DelUser(Integer pageNo, Integer userId) {
        this.pageNo = pageNo;
        this.userId = userId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
