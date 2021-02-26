package cn.bdqn.easybuy.servlet;

import cn.bdqn.easybuy.entity.Address;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
    private AddressService addressService = new AddressService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        // 显示商品详情
        if ("checkOrder".equals(opr)) {
            HttpSession session = request.getSession();
            // 先判断登录
            User userLogin = (User) session.getAttribute("userLogin");
            if (null == userLogin) { // 如果没登陆， 则转发回登陆页面
                response.sendRedirect("Login.jsp");
            } else {  // 如果登陆了，
                int userId = userLogin.getId();
                List<Address> addList = addressService.findByUesrId(userId);// 根据用户id查找用户地址
                request.setAttribute("addList", addList); // 把地址封装到请求中，转发到BuyCar_Two中
                request.getRequestDispatcher("BuyCar_Two.jsp").forward(request, response);
            }
        }

        if ("newAddress".equals(opr)) {
            HttpSession session = request.getSession();
            // 先判断登录
            User userLogin = (User) session.getAttribute("userLogin");
            if (null == userLogin) { // 如果没登陆， 则转发回登陆页面
                response.sendRedirect("Login.jsp");
            } else {  // 如果登陆了，
                int userId = userLogin.getId();
                String address = request.getParameter("address");
                String isDefaultstr = request.getParameter("isDefault");
                int isDefault = Integer.parseInt(isDefaultstr);
                String remark = request.getParameter("remark");
                Address addressNew = new Address(userId, address, isDefault, remark);
                int ret = addressService.addAddress(addressNew);
                if (ret > 0) { // 假如插入失败， 提示省略
                    request.getRequestDispatcher("AddressServlet?opr=checkOrder").forward(request, response);
                }
            }
        }

        if ("delAddress".equals(opr)) {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            int ret = addressService.delAddress(id);
            if (ret > 0) {
                request.getRequestDispatcher("AddressServlet?opr=checkOrder").forward(request, response);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}