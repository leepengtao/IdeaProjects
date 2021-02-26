package cn.bdqn.easybuy.controller;

import cn.bdqn.easybuy.Service.AddressService;
import cn.bdqn.easybuy.entity.Address;
import cn.bdqn.easybuy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/Address")
@SessionAttributes("userLogin")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // 显示商品详情
    @RequestMapping("/checkOrder")
    public String checkOrder(ModelMap modelMap) {
        User userLogin = (User) modelMap.get("userLogin");
        if (null == userLogin) {
            return "Login";
        }
        Integer userId = userLogin.getId();
        List<Address> addList = addressService.findByUesrId(userId);
        modelMap.addAttribute("addList", addList);
        return "BuyCar_Two";
    }

    // 添加地址
    @RequestMapping(value = "/newAddress", method = RequestMethod.POST)
    public String newAddress(ModelMap modelMap, @Param("address")String address,
                             @Param("isDefault")int isDefault,
                             @Param("remark")String remark) {
        User userLogin = (User) modelMap.get("userLogin");
        if (null == userLogin) {
            return "Login";
        }
        int userId = userLogin.getId();
        Address addressNew = new Address(userId, address, isDefault, remark);
        int ret = addressService.addAddress(addressNew);
        return "redirect:/Address/checkOrder";
    }

    // 删除地址
    @RequestMapping("/delAddress")
    public String delAddress(@Param("id")int id) {
        int ret = addressService.delAddress(id);
        System.out.println("地址删除数量为:"+ret);
        return "redirect:/Address/checkOrder";
    }
}
