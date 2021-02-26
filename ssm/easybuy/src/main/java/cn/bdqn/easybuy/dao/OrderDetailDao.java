package cn.bdqn.easybuy.dao;

import cn.bdqn.easybuy.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao {

    // 增加一个订单详情
    @Select("insert into easybuy1.easybuy_order_detail (serialNumber,productId,quantity,cost) VALUES (#{orderDetail.serialNumber},#{orderDetail.productId},#{orderDetail.quantity},#{orderDetail.cost})")
    public Integer addOrderDetail(@Param("orderDetail") OrderDetail orderDetail);

    // insert into easybuy1.easybuy_order_detail (serialNumber,productId,quantity,cost) VALUES (#{orderDetail.serialNumber},#{orderDetail.productId},#{orderDetail.quantity},#{orderDetail.cost})
}
