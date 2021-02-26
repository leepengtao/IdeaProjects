package cn.bdqn.easybuy.Service.Impl;

import cn.bdqn.easybuy.Service.UserService;
import cn.bdqn.easybuy.dao.UserDao;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.util.PageBean;
import cn.bdqn.easybuy.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl
        implements UserService{

    @Autowired
    private UserDao userDao;

    // 登录
    @Override
    public User login(String loginName, String password) {
        System.out.println(password);
        password = SecurityUtils.md5Hex(password);
        System.out.println(password);
        User user = userDao.findByLoginName(loginName);
        if (user!=null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // 注册
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    // 管理员按页查找用户
    @Override
    public PageBean<User> findByPage(Integer pageNo, int pageSize) {
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setPageSize(pageSize);
        int totalCount = userDao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        if (pageNo == null) {
            pageNo = 1;
        }
        pageBean.setPageNo(pageNo);
        List<User> pageList = userDao.findByList((pageBean.getPageNo()-1)*pageBean.getPageSize(), pageBean.getPageSize()); // 获取页码数和一页多少个记录, 然乎返回记录列表
        pageBean.setPageList(pageList); // 把列表存入pageBean
        return pageBean;
    }

    @Override
    public int delUserById(Integer userId) {
        return userDao.delUserById(userId);
    }

    @Override
    public int modUser(User user) {
        return userDao.modUser(user);
    }


    // 模糊查找
    @Override
    public PageBean<User> searchByPage(Integer pageNo, int pageSize, String search) {
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setPageSize(pageSize);
        int totalCount = userDao.searchTotalCount(search);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageNo(pageNo);
        int start = (pageNo -1)* pageSize;
        int end = pageSize;
        List<User> pageList = userDao.searchByList(search, start,end); // 获取页码数和一页多少个记录, 然乎返回记录列表
        pageBean.setPageList(pageList); // 把列表存入pageBean
        return pageBean;
    }

    @Override
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);// 使用了登录的方法
    }
}
