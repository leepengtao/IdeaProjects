package cn.bdqn.easybuy.Service;

import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.util.PageBean;

public interface UserService {

    // 登录
    User login(String loginName, String password);

    // 注册
    int addUser(User user);

    // 管理员按页查找用户
    PageBean<User> findByPage(Integer pageNo, int pageSize);

    // 管理员删除单个用户
    int delUserById(Integer userId);

    // 修改用户
    int modUser(User user);

    // 模糊查找
    PageBean<User> searchByPage(Integer pageNo, int pageSize, String search);

    // 按登录名查找
    User findByLoginName(String loginName);
}
