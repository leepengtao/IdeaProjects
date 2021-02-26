package cn.yz.easybuy.dao.Impl;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.OrderDao;
import cn.yz.easybuy.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public int addOrder(Order order) {
		String sql="INSERT INTO easybuy_order(id,userId,loginName,userAddress,createTime,cost,serialNumber) VALUES (0,?,?,?,NOW(),?,?) ";
		Object[] args= {order.getUserId(),order.getLoginName(),order.getUserAddress(),order.getCost(),order.getSerialNumber()};		
		return executeUpdate(sql, args);
	}

}
