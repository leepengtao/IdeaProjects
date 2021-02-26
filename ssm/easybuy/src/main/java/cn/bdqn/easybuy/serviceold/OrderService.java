package cn.bdqn.easybuy.service;

import cn.bdqn.easybuy.dao.Impl.OrderDaoImpl;
import cn.bdqn.easybuy.dao.OrderDao;
import cn.bdqn.easybuy.entity.Order;

public class OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }
}
