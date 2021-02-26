package cn.bdqn.easybuy.dao.Impl;

import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.OrderDao;
import cn.bdqn.easybuy.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int addOrder(Order order) {
        String sql = "\n" +
                "INSERT INTO `easybuy1`.`easybuy_order` (\n" +
                "  `userId`,\n" +
                "  `loginName`,\n" +
                "  `userAddress`,\n" +
                "  `createTime`," +
                "  `cost`,\n" +
                "  `serialNumber`\n" +
                ")\n" +
                "VALUES\n" +
                "  (?,?,?,NOW(),?,?)";
        // 将所有参数封装到Object[] args， 来传递个数可变的实参
        Object[] args = {order.getUserId(),order.getLoginName(),order.getUserAddress(),order.getCost(),order.getSerialNumber()};
        return executeUpdate(sql,args);
    }
}
