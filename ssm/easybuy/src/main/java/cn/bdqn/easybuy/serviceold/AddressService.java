package cn.bdqn.easybuy.service;

import cn.bdqn.easybuy.dao.AddressDao;
import cn.bdqn.easybuy.dao.Impl.AddressDaoImpl;
import cn.bdqn.easybuy.entity.Address;

import java.util.List;

public class AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    // 根据用户ID查询的地址
    public List<Address> findByUesrId(int userId){
        return addressDao.findByUserId(userId);
    };

    // 添加一个新地址
    public int addAddress(Address addressNew){
        // 如果地址是默认值, 则清空当前用户所有地址的默认属性
        if (addressNew.getIsDefault() == 1) {
            addressDao.updateDefault(addressNew.getUserId());
        }
        // 再把带有默认属性的新地址传入
        return addressDao.addAddress(addressNew);
    }

// 删除一个地址
    public int delAddress(int id) {
        return addressDao.delAddress(id);
    }
}
