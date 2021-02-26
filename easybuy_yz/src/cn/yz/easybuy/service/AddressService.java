package cn.yz.easybuy.service;

import java.util.List;

import cn.yz.easybuy.dao.AddressDao;
import cn.yz.easybuy.dao.Impl.AddressDaoImpl;
import cn.yz.easybuy.entity.Address;

public class AddressService {
	AddressDao addressDao=new AddressDaoImpl();
	
	public List<Address> findAddressByUserId(int userId){
		return addressDao.findAddressByUserId(userId);
	}
		
	public int addAddress(Address address) {
		if(address.getIsDefault()==1) {
			addressDao.updateDefault(address.getUserId());
		}
		return addressDao.addAddress(address);
	}

	public int delAddress(int id) {
		return addressDao.delAddress(id);
	}

}
