package cn.yz.easybuy.dao.Impl;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.OrderDetailDao;
import cn.yz.easybuy.entity.OrderDetail;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao {

	@Override
	public int addOrderDetail(OrderDetail orderDetail) {
		String sql="INSERT INTO easybuy_order_detail (id,serialNumber,productId,quantity,cost) VALUES (0,?,?,?,?) ";
		Object [] args= {orderDetail.getSerialNum(),orderDetail.getProductId(),orderDetail.getNum(),orderDetail.getCost()};
		return executeUpdate(sql, args);
	}

}
