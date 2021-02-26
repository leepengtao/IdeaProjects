package cn.bdqn.easybuy.dao;

import cn.bdqn.easybuy.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressDao {
    // 根据用户ID查询地址
    @Select("select * from `easybuy1`.`easybuy_user_address` where userId = #{userId}")
    List<Address> findByUserId(@Param("userId")int userId);

    // 增加一个地址
    @Insert("insert into `easybuy1`.`easybuy_user_address`(userId,address,createTime,isDefault,remark)" +
            "values(#{addressNew.userId},#{addressNew.address},NOW(),#{addressNew.isDefault},#{addressNew.remark})")
    int addAddress(@Param("addressNew") Address addressNew);
    // 增加默认地址时, 清空原有地址默认值
    @Update("update `easybuy1`.`easybuy_user_address` set isDefault = 0 where userId = #{userId}")
    int updateDefault(@Param("userId") int userId);

    // 删除一个地址
    @Update("delete from `easybuy1`.`easybuy_user_address` where id = #{id}")
    int delAddress(@Param("id") int id);
}
