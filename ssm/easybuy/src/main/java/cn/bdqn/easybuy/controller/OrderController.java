package cn.bdqn.easybuy.controller;

import cn.bdqn.easybuy.Service.OrderDetailService;
import cn.bdqn.easybuy.Service.OrderService;
import cn.bdqn.easybuy.entity.*;
import cn.bdqn.easybuy.util.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/Order")
@SessionAttributes({"userLogin","cart"})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService detailService;

    @RequestMapping("/addOrder")
    public String addOrder(ModelMap modelMap, @Param("address")String address) {
        User userLogin = (User) modelMap.get("userLogin");
        if (null == userLogin) {// 如果检测到没有登陆， 那么发送回登陆页面
            // 逻辑无else：将不能执行下去的情况放在代码里。 如此次未登录： null == userLogin
            return "Login";
        }
        Integer userId = userLogin.getId();
        String loginName = userLogin.getLoginName(); // 获得用户的id和用户名
        ShoppingCart cart = (ShoppingCart) modelMap.get("cart");
        double cost = cart.getTotalCost();// 从会话获取购物车并计算总费用
        String serialNumber= UUIDUtil.getUUId(); // 获取一个订单号

        Order order = new Order(0, userId, loginName, address, null, cost, serialNumber);
        int ret = orderService.addOrder(order);
        System.out.println("新增了"+ret+"个订单");

        // 遍历cart的list: 要在页面上显示cart中的商品
        List<ShoppingCartItem> itemList = cart.getItems();// 获得商品列表
        for (ShoppingCartItem item : itemList) {    // 使用迭代器开始遍历
            int productId = item.getProduct().getId();
            int quantity = item.getQuantity();
            double cost1 = item.getCost();
            OrderDetail orderDetail = new OrderDetail(0, serialNumber, productId, quantity, cost1);
            Integer ret2 = detailService.addOrderDetail(orderDetail);

            System.out.println("新增订单详情数为:"+ret2);   // 为什么返回是null?
        }
        System.out.println("添加订单数为:"+ret+"个");
        modelMap.addAttribute("serialNumber", serialNumber);
        return "BuyCar_Three";
    }
}
