package cn.bdqn.easybuy.servlet;

import cn.bdqn.easybuy.entity.*;
import cn.bdqn.easybuy.service.OrderDetailService;
import cn.bdqn.easybuy.service.OrderService;
import cn.bdqn.easybuy.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();
    private OrderDetailService detailService = new OrderDetailService();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        // 显示商品详情
        if ("addOrder".equals(opr)) {
            HttpSession session = request.getSession();
            User userLogin = (User)session.getAttribute("userLogin"); // 获得会话中的用户对象
            if (null == userLogin) {// 如果检测到没有登陆， 那么发送回登陆页面
                // 逻辑无else：将不能执行下去的情况放在代码里。 如此次未登录： null == userLogin
                response.sendRedirect("Login.jsp");
            } else {
                int userId = userLogin.getId();
                String loginName = userLogin.getLoginName(); // 获得用户的id和用户名
                String userAddress = request.getParameter("address");// 从前端传回来的用户选择的地址

                ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
                double cost = cart.getTotalCost();// 从会话获取购物车并计算总费用
                String serialNumber= UUIDUtil.getUUId(); // 获取一个订单号

                Order order = new Order(0, userId, loginName, userAddress, null, cost, serialNumber);
                int ret = orderService.addOrder(order);

                // 遍历cart的list: 要在页面上显示cart中的商品
                List<ShoppingCartItem> itemList = cart.getItems();// 获得商品列表
                for (ShoppingCartItem item : itemList) {    // 使用迭代器开始遍历
                    int productId = item.getProduct().getId();
                    int quantity = item.getQuantity();
                    double cost1 = item.getCost();
                    OrderDetail orderDetail = new OrderDetail(0, serialNumber, productId, quantity, cost1);
                    detailService.addOrderDetail(orderDetail);
                }
                if (ret > 0) { // 执行成功后转发到第三购物车页面
                    request.setAttribute("serialNumber", serialNumber);
                    request.getRequestDispatcher("BuyCar_Three.jsp").forward(request, response);
                }
            }

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
