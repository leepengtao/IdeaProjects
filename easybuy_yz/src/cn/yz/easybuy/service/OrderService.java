package cn.yz.easybuy.service;

import cn.yz.easybuy.dao.OrderDao;
import cn.yz.easybuy.dao.Impl.OrderDaoImpl;
import cn.yz.easybuy.entity.Order;

public class OrderService {
	OrderDao orderDao=new OrderDaoImpl();
	public int addOrder(Order order) {
		return orderDao.addOrder(order);		
	}
}
