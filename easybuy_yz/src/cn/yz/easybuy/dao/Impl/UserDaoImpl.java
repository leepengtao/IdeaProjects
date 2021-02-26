package cn.yz.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.UserDao;
import cn.yz.easybuy.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	//登录用户
	@Override
	public User dindByLoginName(String loginName) {//登录
		User user=null;
		String sql="SELECT id,loginName,userName,password,sex,identityCode,email,mobile,type FROM easybuy_user WHERE loginName=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Integer id=rs.getInt("id");
				String userName=rs.getString("userName");
				String password=rs.getString("password");
				Integer sex=rs.getInt("sex");
				String identityCode=rs.getString("identityCode");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				Integer type=rs.getInt("type");
				user=new User(id,loginName,userName,password,sex,identityCode,email,mobile,type);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,rs);
		}
		return user;
	}	
	//新增用户
	@Override
	public int addUser(User user) {
		String sql="INSERT INTO easybuy_user (loginName,userName,`password`,sex,identityCode,email,mobile,`type`) VALUES (?,?,?,?,?,?,?,?)";
		Object[] args= {user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType()};
		return executeUpdate(sql,args);
	}
	//修改用户
	@Override
	public int updateUser(User user) {
		String sql="UPDATE easybuy_user SET userName =?,PASSWORD =?,sex =?,identityCode =?,email =?,mobile =?,`type` =? WHERE	id =?";
		Object[] args= {user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType(),user.getId()};
		return executeUpdate(sql,args);
	}
	//删除用户
	@Override
	public int delUser(int id) {
		String sql="DELETE FROM easybuy_user WHERE id = ?";
		return executeUpdate(sql, id);
	}
	//找到有所有用户的总数
	@Override
	public int findTotalUser() {
		String sql="SELECT COUNT(1) FROM easybuy_user";
		int totalCount=0;
		try {
			conn =getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCount=rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,rs);
		}
		return totalCount;
	}
	//分页查找所有用户
	@Override
	public List<User> findAllUser(int pageNo,int pageSize) {
		List<User> list=new ArrayList<User>();
		String sql="SELECT id,loginName,userName,`password`,sex,identityCode,email,mobile,`type` FROM easybuy_user LIMIT ?,?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String loginName=rs.getString("loginName");
				String userName=rs.getString("userName");
				String password=rs.getString("password");
				int sex=rs.getInt("sex");
				String identityCode=rs.getString("identityCode");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				int type=rs.getInt("type"); 
				list.add(new User(id, loginName, userName, password, sex, identityCode, email, mobile, type));
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
	//用id查找所有用户
	@Override
	public User findUserById(int id) {		
		String sql="SELECT id,loginName,userName,`password`,sex,identityCode,email,mobile,`type` FROM easybuy_user WHERE id=?";
		User user=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {				
				String loginName=rs.getString("loginName");
				String userName=rs.getString("userName");
				String password=rs.getString("password");
				int sex=rs.getInt("sex");
				String identityCode=rs.getString("identityCode");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				int type=rs.getInt("type"); 
				user=new User(id, loginName, userName, password, sex, identityCode, email, mobile, type);				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return user;
	}
	

}
