package cn.bdqn.smbms.service;

import cn.bdqn.smbms.entity.Role;

import java.util.List;

public interface UserRole {

    // 查找所有角色
    List<Role> findAll();
}
