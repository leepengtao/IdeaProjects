package cn.yz.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yz.easybuy.dao.AddressDao;
import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.entity.Address;

public class AddressDaoImpl extends BaseDao implements AddressDao{
	
	@Override
	public List<Address> findAddressByUserId(int userId) {
		List<Address> list=new ArrayList<Address>();
		String sql="SELECT id,userId,address,createTime,isDefault,remark FROM easybuy_user_address WHERE userId=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String userAddress=rs.getString("address");
				Date createTime=rs.getDate("createTime");
				int isDefault=rs.getInt("isDefault");
				String remark=rs.getString("remark");
				list.add(new Address(id, userId, userAddress, createTime, isDefault, remark));
			}
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	//新增地址
	@Override
	public int addAddress(Address address) {
		String sql="INSERT INTO easybuy_user_address (id,userId,address,createTime,isDefault,remark) VALUES (0,?,?,NOW(),?,?)";
		Object[] args= {address.getUserId(),address.getUserAddress(),address.getIsDefault(),address.getRemark()};		
		return executeUpdate(sql, args);
	}
	//修改默认地址
	@Override
	public int updateDefault(int userId) {
		String sql="UPDATE easybuy_user_address SET isDefault=0 WHERE userId=?";
		return executeUpdate(sql, userId);
	}
	//删除地址
	@Override
	public int delAddress(int id) {
		String sql="DELETE FROM easybuy_user_address WHERE id = ?";
		return executeUpdate(sql, id);
	}

}
