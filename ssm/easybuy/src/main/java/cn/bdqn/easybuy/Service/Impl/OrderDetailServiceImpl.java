package cn.bdqn.easybuy.Service.Impl;

import cn.bdqn.easybuy.Service.OrderDetailService;
import cn.bdqn.easybuy.dao.OrderDetailDao;
import cn.bdqn.easybuy.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public Integer addOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.addOrderDetail(orderDetail);
    }
}
