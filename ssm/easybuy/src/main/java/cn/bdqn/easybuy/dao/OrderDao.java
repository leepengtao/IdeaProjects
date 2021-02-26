package cn.bdqn.easybuy.dao;

import cn.bdqn.easybuy.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    // 创建一个订单
    @Insert("insert into `easybuy1`.`easybuy_order` (" +
            "  `userId`,\n" +
            "  `loginName`,\n" +
            "  `userAddress`,\n" +
            "  `createTime`,\n" +
            "  `cost`,\n" +
            "  `serialNumber`)VALUES\n" +
            "  (\n" +
            "    #{order.userId},\n" +
            "    #{order.loginName},\n" +
            "    #{order.userAddress},\n" +
            "    #{order.createTime},\n" +
            "    #{order.cost},\n" +
            "    #{order.serialNumber})\n")
    public int addOrder(@Param("order") Order order);
}
