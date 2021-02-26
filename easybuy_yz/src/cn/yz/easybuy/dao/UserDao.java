package cn.yz.easybuy.dao;

import java.util.List;

import cn.yz.easybuy.entity.User;

public interface UserDao{
	//��¼
	User dindByLoginName(String loginName); 
	//���������û�
	List<User> findAllUser(int pageNo, int pageSize);
	//ע��
	int addUser(User user);
	//�޸��û�
	int updateUser(User User);
	//ɾ���û�
	int delUser(int id);
	//��ҳ���������û�
	int findTotalUser();
	//��id���������û�
	User findUserById(int id);

}
