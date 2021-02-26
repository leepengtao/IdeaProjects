package cn.bdqn.easybuy.Service;

import cn.bdqn.easybuy.entity.Address;

import java.util.List;

public interface AddressService {

    // 根据用户id查询地址
    public List<Address> findByUesrId(int userId);
    // 添加一个地址
    public int addAddress(Address addressNew);

    // 删除一个地址
    public int delAddress(int id);
}
