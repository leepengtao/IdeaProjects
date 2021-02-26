package cn.bdqn.smbms.dao;

import cn.bdqn.smbms.entity.Bill;
import cn.bdqn.smbms.entity.Provider;

import java.util.List;
import java.util.Map;

/**
 * 供应商(注解的方式)
 */

public interface ProviderDao {

    // 查找所有供应商
    List<Provider> findAll();

    // 统计供应商总数
    int getProviderCount();

    // 根据供应商id查订单
    Provider findBillsByProId(int id);



    /**
     * 根据商品名称, 供应商id, 或是否付款查询供应商与订单
     * @return 带有供应商信息的订单列表
     */
    List<Bill> findBillsByCondition(Map<String,Object> map);
}
