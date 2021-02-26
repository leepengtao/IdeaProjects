package cn.bdqn.easybuy.controller.backendController;

import cn.bdqn.easybuy.entity.DelUser;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.Service.UserService;
import cn.bdqn.easybuy.util.PageBean;
import cn.bdqn.easybuy.util.SecurityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/UserManage")
@SessionAttributes("userLogin")
public class UserManageController {

    @Autowired
    private UserService userService;

    // 按页查找用户
    @RequestMapping("/showAll")
    public String showAll(@Param("pageNo")Integer pageNo, ModelMap modelMap) {
        int pageSize = 3;
        PageBean<User> userPage = userService.findByPage(pageNo, pageSize);
        modelMap.addAttribute("userPage", userPage);
        return "Administrator";
    }

    // ajax翻页
    @RequestMapping(value = "/showPage",method = RequestMethod.GET)
    public @ResponseBody PageBean showPage(@Param("pageNo") Integer pageNo) {
//        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = 3;
        PageBean<User> userPage = userService.findByPage(pageNo, pageSize);
        return userPage;
    }

    // 模糊查找
    @RequestMapping(value = "/searchLike",method = RequestMethod.POST)
    public String searchLike(@Param("search")String search,@Param("pageNo")Integer pageNo,ModelMap modelMap) {
        if (pageNo == null) {
            pageNo = 1;
        }
        int pageSize = 10;
        PageBean<User> userPage = userService.searchByPage(pageNo, pageSize,search);
        List<User> users = userPage.getPageList();
        for (User user : users) {
            System.out.println(user);
        }
        modelMap.addAttribute("userPage", userPage);
        return "Administrator";
    }

    // 添加用户
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String addUser(@Param("loginName")String loginName,@Param("userName")String userName,
                          @Param("password")String password,@Param("email")String email,
                          @Param("mobile")String mobile,@Param("identityCode")String identityCode,
                          @Param("sex")Integer sex,@Param("type")Integer type) {

        // 使用MD5加密
        password = SecurityUtils.md5Hex(password);
        User user = new User(null,loginName, userName, password, sex, identityCode, email, mobile, type);
        System.out.println(user.getLoginName());
        int ret = userService.addUser(user);    // 和注册用的一个方法
        System.out.println("新增用户数为:"+ret);
        return "redirect:/UserManage/showAll";
    }

    // 删除单个用户
    /*@RequestMapping("/delUser")
    public String delUser(@Param("userId")Integer userId) {
        int ret = userService.delUserById(userId);
        System.out.println("删除了"+ret+"个用户");
        return "redirect:/UserManage/showAll";
    }*/

    // ajax删除单个用户
    @RequestMapping(value = "/delUser",method = RequestMethod.POST)
    public @ResponseBody PageBean delUser(@RequestBody DelUser delUser) {
        Integer userId = delUser.getUserId();
        Integer pageNo = delUser.getPageNo();
        int ret = userService.delUserById(userId);
        System.out.println("删除了"+ret+"个用户");
//        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = 3;
        PageBean<User> userPage = userService.findByPage(pageNo, pageSize);
        return userPage;
    }


    // 批量删除用户
    @RequestMapping(value = "delSelected",method = RequestMethod.POST)
    public String delSelected(@Param("uid")Integer[] uid,ModelMap modelMap) {
        int delFailCount = 0;
        for (Integer id:uid) {
            int ret = userService.delUserById(id);
            if (ret == 0) {
                delFailCount++;
            }
        }
        modelMap.addAttribute("delFailCount", delFailCount);
        return "redirect:/UserManage/showAll";
    }

    // 转跳至修改用户页面
    @RequestMapping("/jumpToAddUser")
    public String jumpToModUser(@Param("loginName")String loginName, ModelMap modelMap) {
        User user = userService.findByLoginName(loginName);
        modelMap.addAttribute("user", user);
        return "/backend/modUser";
    }


    // 修改用户
    @RequestMapping(value = "/modUser",method = RequestMethod.POST)
    public String modUser(@Param("id") Integer id,@Param("loginName")String loginName,
                          @Param("userName")String userName,@Param("password")String password,
                          @Param("sex")Integer sex,@Param("identityCode")String identityCode,
                          @Param("email")String email,@Param("mobile")String mobile,
                          @Param("type")Integer type) {
        // 使用MD5加密
        password = SecurityUtils.md5Hex(password);
        User user = new User(id,loginName,userName,password,sex,identityCode,email,mobile,type);
        // 调用serviec修改数据
        int ret = userService.modUser(user);
        return "redirect:/UserManage/showAll";
    }

}
