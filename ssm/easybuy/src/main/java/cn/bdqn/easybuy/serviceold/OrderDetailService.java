package cn.bdqn.easybuy.service;

import cn.bdqn.easybuy.dao.Impl.OrderDetailDaoImpl;
import cn.bdqn.easybuy.dao.OrderDetailDao;
import cn.bdqn.easybuy.entity.OrderDetail;

public class OrderDetailService {
    OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    public int addOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.addOrderDetail(orderDetail);
    }
}
