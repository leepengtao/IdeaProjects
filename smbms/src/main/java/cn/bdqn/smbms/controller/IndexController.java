package cn.bdqn.smbms.controller;

import cn.bdqn.smbms.entity.User;
import cn.bdqn.smbms.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("userSession")
public class IndexController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 表示层用户登录
     * 表示层只做表示层该做的事, 接收到所有数据, 调用Service方法, 根据Service返回值来决定给前端返回什么. 其他的后端逻辑一律交给业务层来处理
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("userCode")String username,       // 账号
                        @RequestParam("userPassword")String password,   // 密码
                        ModelMap modelMap) {    // 传递信息的参数
        System.out.println("登录方法执行了");
        User user = userService.login(username,password);
        if (user != null) {
            modelMap.addAttribute("userSession",user);  // 放入Session域中
            return "frame";
        }else{
            modelMap.addAttribute("msg","用户名或密码错误!");   // 放入response域中
            return "login";
        }
    }

    /**
     * 登出: 只用到SessionStatus方法销毁session即可
     * @param sessionStatus
     * @return
     */
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();    // 登出专用方法: SessionStatus
        return "login";
    }
}
