package cn.bdqn.easybuy.controller;

import cn.bdqn.easybuy.Service.ProductService;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.entity.ShoppingCart;
import cn.bdqn.easybuy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Product")
@SessionAttributes({"userLogin", "cart"})
public class ProductController {

    @Autowired
    private ProductService productService;

    // 显示商品详情
    @RequestMapping("/showPro")
    public String showPro(@Param("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "Product";
    }


    // 添加购物车
    @RequestMapping("/addCart")
    public String addCart(@Param("proNum") int proNum, @Param("proId") int proId, ModelMap modelMap) {
        // 判断是否登录
        User userLogin = (User) modelMap.getAttribute("userLogin");
        if (userLogin == null) {
            return "Login";
        }
        // 在判断之前有没有购物车
        ShoppingCart cart = (ShoppingCart) modelMap.getAttribute("cart");
        if (null == cart) {
            cart = new ShoppingCart();
        }
        Product product = productService.findById(proId);
        cart.addItem(product, proNum);// 将返回的商品添加到购物车中
        // 放入Session中,最重要, 别忘记, 和return null一样
        modelMap.addAttribute("cart", cart);
        return "BuyCar";
    }

    // 购物车中更改商品数量
    @RequestMapping("/modifyQuantity")
    public String modifyQuantity(@Param("proNum") int proNum, @Param("proId") int proId, ModelMap modelMap) {
        ShoppingCart cart = (ShoppingCart) modelMap.getAttribute("cart");
        cart.modifyQuantity(proId, proNum);
        // 放入Session
        modelMap.addAttribute("cart", cart);
        return "BuyCar";
    }

    // 删除一件商品
    @RequestMapping("/removeItem")
    public String removeItem(@Param("proId") int proId, ModelMap modelMap) {
        ShoppingCart cart = (ShoppingCart) modelMap.get("cart");
        cart.removeItem(proId);
        modelMap.addAttribute("cart", cart);
        return "BuyCar";
    }
}
