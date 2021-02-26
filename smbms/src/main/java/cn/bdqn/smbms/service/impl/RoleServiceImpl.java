package cn.bdqn.smbms.service.impl;

import cn.bdqn.smbms.dao.RoleDao;
import cn.bdqn.smbms.entity.Role;
import cn.bdqn.smbms.service.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("UserRole")
public class RoleServiceImpl implements UserRole {

    @Autowired
    private RoleDao roleDao;

    // 查找所有角色
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
