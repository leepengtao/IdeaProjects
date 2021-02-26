package cn.bdqn.smbms.controller;

import cn.bdqn.smbms.entity.Role;
import cn.bdqn.smbms.entity.User;
import cn.bdqn.smbms.service.impl.RoleServiceImpl;
import cn.bdqn.smbms.service.impl.UserServiceImpl;
import cn.bdqn.smbms.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;


    @RequestMapping("userlist.html")
    public String userList(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1")int pageNo,    // 页码
            @RequestParam(value = "pageSize", required = false, defaultValue = "3")int pageSize,// 页面显示条数
            @RequestParam(value = "userName", required = false)String userName,     // 用户名
            @RequestParam(value = "roleId", required = false, defaultValue = "0")int roleId,    // 用户角色
            Model model) {
        // 查找所有角色
        List<Role> role = roleService.findAll();
        // 查找用户
        PageBean<User> pageBean = userService.findByPage(pageNo, pageSize, userName, roleId);
        model.addAttribute("roleList",role);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("userName",userName);
        model.addAttribute("roleId",roleId);
        return "userlist";
    }

    //显示新增
    @RequestMapping("/useradd.html")
    public String showAdd() {
        return "useradd";
    }
}
