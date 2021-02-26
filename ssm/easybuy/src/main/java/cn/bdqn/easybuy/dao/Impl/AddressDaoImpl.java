package cn.bdqn.easybuy.dao.Impl;

import cn.bdqn.easybuy.dao.AddressDao;
import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.entity.Address;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends BaseDao implements AddressDao {
    @Override
    public List<Address> findByUserId(int userId) {

        List<Address> list = new ArrayList<Address>();
        String sql = "SELECT\n" +
                "  `id`,\n" +
                "  `userId`,\n" +
                "  `address`,\n" +
                "  `createTime`,\n" +
                "  `isDefault`,\n" +
                "  `remark`\n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_user_address`\n" +
                "WHERE userId = ?";
        try {
            rs = executeQuery(sql, userId);
            while (rs.next()) {
                 int id = rs.getInt("id");
                 String address = rs.getString("address");
                 Date createTime = rs.getDate("createTime");
                 int isDefault = rs.getInt("isDefault");
                String remark = rs.getString("remark");
                list.add(new Address(id,userId,address,createTime,isDefault,remark));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);

        }
        return list;
    }

    @Override
    public int addAddress(Address addressNew) {
        // 定义sql语句
        String sql =
                "INSERT INTO `easybuy1`.`easybuy_user_address` (\n" +
                "  `userId`,\n" +
                "  `address`,\n" +
                " `createTime`," +
                "  `isDefault`,\n" +
                "  `remark`\n" +
                ")\n" +
                "VALUES\n" +
                " (?,?,NOW(),?,?);";
        // 将所有参数封装到Object[] args， 来传递个数可变的实参
        Object[] args = {addressNew.getUserId(), addressNew.getAddress(), addressNew.getIsDefault(), addressNew.getRemark()};
        return executeUpdate(sql,args);
    }

    // 清空之前的默认地址属性
    @Override
    public int updateDefault(int userId) {
        String sql = "update `easybuy1`.`easybuy_user_address` set isDefault = 0 where userId = ?";
        return executeUpdate(sql,userId);
    }


    // 删除一个地址
    @Override
    public int delAddress(int id) {
        // 定义sql语句
        String sql = "DELETE\n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_user_address`\n" +
                "WHERE `id` = ?;\n" +
                "\n";
        return executeUpdate(sql, id);
    }
}