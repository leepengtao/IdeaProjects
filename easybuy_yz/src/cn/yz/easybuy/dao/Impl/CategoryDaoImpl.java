package cn.yz.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.CategoryDao;
import cn.yz.easybuy.entity.Category;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {
	//查询通过parentId查询目录
	@Override
	public List<Category> findByParentId(int parentId) {
		List<Category> list=new ArrayList<Category>();
		String sql="SELECT id,`name`,parentId,`type`,iconClass FROM easybuy_product_category WHERE parentId=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, parentId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int type=rs.getInt("type");
				String iconClass=rs.getString("iconClass");
				list.add(new Category(id, name, parentId, type, iconClass));
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
	//通过id找目录
	@Override
	public Category findById(int id) {
		Category category=null;
		String sql="SELECT id,`name`,parentId,`type`,iconClass FROM easybuy_product_category WHERE id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {			
				String name=rs.getString("name");
				int parentId=rs.getInt("parentId");
				int type=rs.getInt("type");
				String iconClass=rs.getString("iconClass");
				category=new Category(id, name, parentId, type, iconClass);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return category;
	}
	//新增目录
	@Override
	public int addCategory(Category category) {
		String sql="INSERT INTO easybuy_product_category (id,name,parentId,type,iconClass)VALUES(0,?,?,?,NULL) ";
		Object[] args= {category.getName(),category.getParentId(),category.getType()};
		return executeUpdate(sql, args);
	}
	//修改目录
	@Override
	public int updateCategory(String name,int parentId,int type,int id) {
		String sql="UPDATE easybuy_product_category SET name = ? ,parentId = ? ,type = ?  WHERE id = ?";
		Object[] args= {name,parentId,type,id};
		return executeUpdate(sql, args);
	}
	//通过id删除目录
	@Override
	public int delCategory(int id) {
		String sql="DELETE FROM easybuy_product_category WHERE id =?";
		return executeUpdate(sql, id);
	}
	//通过parentId删除目录
	@Override
	public int delParentIdCategory1(int parentId) {
		String sql="DELETE FROM easybuy_product_category WHERE parentId =?";
		return executeUpdate(sql, parentId);
	}
	
	

}
