package cn.bdqn.smbms.dao;

import cn.bdqn.smbms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface UserDao {

    // 查找所有用户
    List<User> findAll();

    // 分页的数量
    @Select("<script>  SELECT  COUNT(U.id)\n" +
            "        FROM\n" +
            "        smbms_user  U INNER JOIN smbms_role R\n" +
            "        ON U.`userRole` = R.id\n" +
            "        <where>\n" +
            "            <if test=\"userName!=null\">\n" +
            "                AND U.`userName` LIKE CONCAT('%',#{userName},'%')\n" +
            "            </if>\n" +
            "            <if test=\"roleId!=null and roleId !=0 \">\n" +
            "                AND U.`userRole` = #{roleId}\n" +
            "            </if>\n" +
            "        </where>" +
            "</script>")
    int getTotalCount(@Param("userName")String userName,@Param("roleId")int roleId);

    // 登录查询
    @Select("SELECT \n" +
            "  `id`,\n" +
            "  `userCode`,\n" +
            "  `userName`,\n" +
            "  `userPassword`,\n" +
            "  `gender`,\n" +
            "  `birthday`,\n" +
            "  `phone`,\n" +
            "  `address`,\n" +
            "  `userRole`,\n" +
            "  `createdBy`,\n" +
            "  `creationDate`,\n" +
            "  `modifyBy`,\n" +
            "  `modifyDate` \n" +
            "FROM\n" +
            "  `smbms`.`smbms_user` \n" +
            "WHERE userCode = #{name};\n")
    User findByName1(@Param("name") String name);

    // 增
    int addUser(User user);

    // 改
    int updateUser(User user);

    // 删除
    int delUser(int id);

    // 根据用户名模糊查询
    List<User> findByName(String name);

    // 根据id查询
    User findById(int id);

    // 多条件查询1
    List<User> findByCondition1(User user);

    // 多条件查询2
    List<User> findByCondition2(Map<String, Object> map);

    // 多条件查询3
    List<User> findByCondition3(
            @Param("name")
                    String name,
            @Param("role")
                    int rid
    );


    // 1月12日新增方法
    // 根据数组查询
    List<User> findByRoles(int[] roleIds);

    // 根据集合查询
    List<User> findByRoleList(List<Integer> roleList);

    // 根据Map查询
    List<User> findByMap(Map<String,Object> map);

    // 按页面查找
    @Select("<script>" +
            "SELECT U.id,U.`userCode`,U.`userName`,U.`gender`,U.phone,U.userRole,U.birthday,\n" +
            "    FLOOR((DATEDIFF(NOW() ,U.`birthday`)/365)) AS age,\n" +
            "    R.`roleName` AS userRoleName\n" +
            "    FROM\n" +
            "    smbms_user  U INNER JOIN smbms_role R\n" +
            "    ON U.`userRole` = R.id\n" +
            "    <where>\n" +
            "        <if test=\"userName!=null\">\n" +
            "            AND U.`userName` LIKE CONCAT('%',#{userName},'%')\n" +
            "        </if>\n" +
            "        <if test=\"roleId!=null and roleId !=0 \">\n" +
            "            AND U.`userRole` = #{roleId}\n" +
            "        </if>\n" +
            "    </where>\n" +
            "        LIMIT #{from},#{pageSize}" +
            "</script>")
    List<User> findByPage(@Param("from")int from,
                          @Param("pageSize")int pageSize,
                          @Param("userName") String userName,
                          @Param("roleId")int roleId);
}
