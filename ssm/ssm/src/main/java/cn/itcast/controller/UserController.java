package cn.itcast.controller;

import cn.itcast.domain.User;
import cn.itcast.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/findAll")
    public String findAll() {
        System.out.println("表示层:查询所有用户方法执行了...");
        userService.findAll();
        return "list";
    }
}
