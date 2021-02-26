package cn.bdqn.smbms.dao;

import cn.bdqn.smbms.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    //根据id查询Role和对应的用户
    Role findById(int id);

    // 查询所有角色
    @Select("select `id`,`roleName` " +
            "from `smbms`.`smbms_role`")
    List<Role> findAll();

}
