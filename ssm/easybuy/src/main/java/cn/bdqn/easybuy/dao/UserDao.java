package cn.bdqn.easybuy.dao;

import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.util.PageBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    // 登录
    @Select("select * from `easybuy1`.`easybuy_user` where loginName=#{name}")
    User findByLoginName(@Param("name")String loginName);


    // 注册
    @Insert("insert into`easybuy1`.`easybuy_user`(" +
            "`loginName`,\n" +
            "  `userName`,\n" +
            "  `password`,\n" +
            "  `sex`,\n" +
            "  `identityCode`,\n" +
            "  `email`,\n" +
            "  `mobile`,\n" +
            "  `type`) " +
            " values(#{user.loginName}," +
            "#{user.userName}," +
            "#{user.password}," +
            "#{user.sex}," +
            "#{user.identityCode}," +
            "#{user.email}," +
            "#{user.mobile}," +
            "#{user.type})")
    int addUser(@Param("user")User user);

    // 查找所有用户
    @Select("select * from `easybuy1`.`easybuy_user`")
    List<User> testFindAll();


    // 管理员按页查找
    @Select("select * from `easybuy1`.`easybuy_user` limit #{start},#{end}")
    List<User> findByList(@Param("start") int start, @Param("end") int end);

    // 统计用户总数
    @Select("select COUNT(1) from `easybuy1`.`easybuy_user`")
    int findTotalCount();

    // 删除单个用户
    @Delete("delete from `easybuy1`.`easybuy_user` where id = #{id}")
    int delUserById(@Param("id") int id);

    // 修改用户信息
    @Update("UPDATE `easybuy1`.`easybuy_user` SET " +
            "`loginName` = #{user.loginName},\n" +
            "  `userName` = #{user.userName},\n" +
            "  `password` = #{user.password},\n" +
            "  `sex` = #{user.sex},\n" +
            "  `identityCode` = #{user.identityCode},\n" +
            "  `email` = #{user.email},\n" +
            "  `mobile` = #{user.mobile},\n" +
            "  `type` = #{user.type} \n" +
            "WHERE `id` = #{user.id} ;")
    int modUser(@Param("user") User user);

    // 模糊查找结果总数(int)
    @Select("select count(1) from `easybuy1`.`easybuy_user` WHERE userName LIKE CONCAT('%',#{search},'%')")
    int searchTotalCount(@Param("search") String search);

    // 模糊查找
    @Select("select * from `easybuy1`.`easybuy_user` WHERE userName LIKE CONCAT('%',#{search},'%') LIMIT #{start},#{end}")
    List<User> searchByList(@Param("search")String search, @Param("start")int start, @Param("end")int end);
}
