package cn.bdqn.easybuy.dao.Impl;

import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.OrderDetailDao;
import cn.bdqn.easybuy.entity.OrderDetail;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao {
    @Override
    public int addOrderDetail(OrderDetail orderDetail) {

        String sql = "INSERT INTO `easybuy1`.`easybuy_order_detail` (\n" +
                "  `serialNumber`,\n" +
                "  `productId`,\n" +
                "  `quantity`,\n" +
                "  `cost`\n" +
                ")\n" +
                "VALUES\n" +
                "  ( ?,?,?,?);\n";
        Object[] args = {orderDetail.getSerialNumber(), orderDetail.getPriductId(), orderDetail.getQuantity(), orderDetail.getCost()};
        return executeUpdate(sql, args);

    }
}
