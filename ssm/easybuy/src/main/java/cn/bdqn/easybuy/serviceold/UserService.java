package cn.bdqn.easybuy.service;

import cn.bdqn.easybuy.dao.UserDao;
import cn.bdqn.easybuy.dao.Impl.UserDaoImpl;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.util.PageBean;
import cn.bdqn.easybuy.util.SecurityUtils;

import java.util.List;

public class UserService {


    private UserDao userDao = new UserDaoImpl();

    //登录
    public User login(String loginName,String password) {
        User user = userDao.findByLoginName(loginName);
        // 使用MD5加密
        password = SecurityUtils.md5Hex(password);
        if(user!=null && password.equals(user.getPassword()))
            return user;
        else
            return null;
    }


    //注册
    public int addUser(User user) {
        return userDao.addUser(user);
    }


    // 查找单个用户
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    // 查找所有用户
    public PageBean<User> findByPage(int pageNo, int pageSize) {
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setPageSize(pageSize);
        int totalCount = userDao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        pageBean.setPageNo(pageNo);
        List<User> pageList = userDao.findByList(pageBean.getPageNo(), pageBean.getPageSize()); // 获取页码数和一页多少个记录, 然乎返回记录列表
        pageBean.setPageList(pageList); // 把列表存入pageBean
        return pageBean;
    }

    // 删除单个用户
    public int delUserById(int id) {
        return userDao.delUserById(id);
    }

    // 修改用户
    public int modUser(User user) {
        return userDao.modUser(user);
    }


    // 模糊查找
    public PageBean<User> searchByPage(int pageNo, int pageSize, String search) {
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setPageSize(pageSize);
        int totalCount = userDao.searchTotalCount(search);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageNo(pageNo);
        List<User> pageList = userDao.searchByList(search, pageBean.getPageNo(), pageBean.getPageSize()); // 获取页码数和一页多少个记录, 然乎返回记录列表
        pageBean.setPageList(pageList); // 把列表存入pageBean
        return pageBean;
    }
}
