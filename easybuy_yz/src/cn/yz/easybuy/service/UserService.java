package cn.yz.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.UserDao;
import cn.yz.easybuy.dao.Impl.UserDaoImpl;
import cn.yz.easybuy.entity.User;
import cn.yz.easybuy.util.PageBean;
import cn.yz.easybuy.util.SecurityUtils;

public class UserService {
	private UserDao userDao=new UserDaoImpl();
	
	//��¼
	public User login(String loginName,String password) {
		User user=userDao.dindByLoginName(loginName);
		//ʹ��MD5����
		password=SecurityUtils.md5Hex(password);
		if(user!=null&&password.equals(user.getPassword())) {
			return user;
		}else{
			return null;
		}
	}
	//ע��
	public int addUser(User user) {
		return userDao.addUser(user);
	}
	//��ҳ���������û��ķ���
	public PageBean<User> findAllUser(int pageNo, int pageSize) {
		PageBean<User> user=new PageBean<User>();		
		List<User> list = new ArrayList<User>();				
		user.setPageSize(pageSize);
		int totalUser = userDao.findTotalUser();
		user.setTotalCount(totalUser);
		user.setPageNo(pageNo);
		list=userDao.findAllUser(user.getPageNo(),pageSize);
		user.setPageList(list);		
		return user;
	}
	//��id���������û�
	public User findUserById(int id) {
		return userDao.findUserById(id);		
	}
	public int updateUser(User user) {
		return userDao.updateUser(user);		
	}
	public int delUser(int id) {
		return userDao.delUser(id);
	}
}
