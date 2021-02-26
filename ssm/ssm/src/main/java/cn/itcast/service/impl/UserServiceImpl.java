package cn.itcast.service.impl;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() {
        System.out.println("业务层: 查询所有账户....");
        return null;
    }
}
