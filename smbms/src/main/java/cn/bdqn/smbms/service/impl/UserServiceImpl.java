package cn.bdqn.smbms.service.impl;

import cn.bdqn.smbms.dao.UserDao;
import cn.bdqn.smbms.entity.User;
import cn.bdqn.smbms.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录业务层
     * 在indexController里面,1. 接收参数 2. 调用service方法 3.放回参数 4.返回给视图控制器的页面结果
     * 其他的例如判断用户是否存在, 全在业务层中进行, 业务层该干的事应该和视图控制层分工明确
     * @param name
     * @param password
     * @return
     */
    public User login(String name,String password) {
        User user = userDao.findByName1(name);  // 调用Dao层
        if (user != null && user.getUserPassword().equals(password)) {  // 判断是否有该用户与输入密码是否正确
            return user;    // 正确则返回该用户
        }
        return null;
    }

    /**
     * 业务层查找用户
     * @param pageNo
     * @param pageSize
     * @param userName
     * @param roleId
     * @return
     */
    public PageBean<User> findByPage(int pageNo, int pageSize, String userName, int roleId) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize); // 传入页面大小
        int totalCount = userDao.getTotalCount(userName, roleId);// 查找该角色下的所有用户数量. userName与roleId皆为可选参数, 利用动态Sql语句实现
        pageBean.setTotalCount(totalCount);     // 传入总页数
        pageBean.setPageNo(pageNo);     // 传入当前页码
        int from = (pageBean.getPageNo()-1)*pageSize;   // 传入从第几个开始查, 就是页数的控制, 传入limit的第一个数
        List<User> pageList = userDao.findByPage(from,pageSize,userName,roleId);
        for (User user : pageList) {
            System.out.println(user);
        }
        pageBean.setPageList(pageList);
        return pageBean;

    }
}
