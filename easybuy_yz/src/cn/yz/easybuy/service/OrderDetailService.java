package cn.yz.easybuy.service;

import cn.yz.easybuy.dao.OrderDetailDao;
import cn.yz.easybuy.dao.Impl.OrderDetailDaoImpl;
import cn.yz.easybuy.entity.OrderDetail;

public class OrderDetailService {
	OrderDetailDao orderDetailDao=new OrderDetailDaoImpl();
	public int addOrderDetail(OrderDetail orderDetail) {
		return orderDetailDao.addOrderDetail(orderDetail);
		
	}
}
