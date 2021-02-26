package cn.bdqn.easybuy.Service.Impl;

import cn.bdqn.easybuy.Service.OrderService;
import cn.bdqn.easybuy.dao.OrderDao;
import cn.bdqn.easybuy.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }
}
