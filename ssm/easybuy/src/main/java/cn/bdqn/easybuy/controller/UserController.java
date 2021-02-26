package cn.bdqn.easybuy.controller;

import cn.bdqn.easybuy.Service.UserService;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.util.SecurityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/User")
@SessionAttributes({"userLogin","numrand"})
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String code, ModelMap modelMap,@RequestParam String loginName,@RequestParam String password) {
        //先判断code 验证码,如果验证码不正确
        //获取 session Number.jsp生成的
        String numrand = (String) modelMap.getAttribute("numrand");

        if(code.equals(numrand)) {
            User userLogin = userService.login(loginName, password);
            if(null!=userLogin) {
                modelMap.addAttribute("userLogin", userLogin);
                return "index";
            }else {
                modelMap.addAttribute("msg", "用户名或密码错误,请重新输入");
                return "Login";
            }
        }else {
            modelMap.addAttribute("msg", "验证码不正确,请重新输入");
            return "Login";
        }
    }

    // 注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model,@Param("loginName") String loginName, @Param("userName")String userName
            , @Param("password")String password, @Param("email")String email,
                           @Param("mobile")String mobile, @Param("identityCode")String identityCode, @Param("sex")int sex) {
        System.out.println(loginName);
        System.out.println(sex);
        int type = 0;
        // 使用MD5加密
        password = SecurityUtils.md5Hex(password);
        User user = new User(loginName, userName, password, sex, identityCode, email, mobile, type);
        System.out.println(user.getLoginName());
        int ret = userService.addUser(user);
        if (ret > 0) {
            return "Login";
        } else {
            return "Register";
        }

    }

    // 注销登录
    @RequestMapping("/logout")
    public String logOut(SessionStatus status) {
        System.out.println("用户登出...");
        status.setComplete();
        return "index";
    }
}
