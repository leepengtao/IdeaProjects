package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.Address;

public interface AddressDao {
	//根据用户Id查询的地址
	List<Address> findAddressByUserId(int userId);
	
	int addAddress(Address address);
	
	int updateDefault(int userId);
	
	int delAddress(int id);

}
