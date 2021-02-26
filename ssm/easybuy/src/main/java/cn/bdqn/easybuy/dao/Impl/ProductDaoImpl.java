package cn.bdqn.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.ProductDao;
import cn.bdqn.easybuy.entity.Product;

public class ProductDaoImpl extends BaseDao implements ProductDao {

	@Override
	public int findTotalCount() {
		int count = 0;
		String sql = "SELECT COUNT(1) FROM `easybuy_product`";
		try {
			rs = executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);	// 第一列
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return count;
	}

	// 查找全部商品
	@Override
	public List<Product> findByList(int pageNo, int pageSize) {
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT \r\n" + 
				"  `id`,\r\n" + 
				"  `name`,\r\n" + 
				"  `description`,\r\n" + 
				"  `price`,\r\n" + 
				"  `stock`,\r\n" + 
				"  `categoryLevel1Id`,\r\n" + 
				"  `categoryLevel2Id`,\r\n" + 
				"  `categoryLevel3Id`,\r\n" + 
				"  `fileName`,\r\n" + 
				"  `isDelete` \r\n" + 
				"FROM\r\n" + 
				"  `easybuy1`.`easybuy_product` \r\n" + 
				"LIMIT ?, ?";
		try {
			rs = executeQuery(sql,(pageNo-1)*pageSize,pageSize);
			while (rs.next()) {
				 int id = rs.getInt("Id");
				 String name = rs.getString("name");
				 String description = rs.getString("description");
				 double price = rs.getDouble("price");
				 int stock = rs.getInt("stock");
				 int categoryLevel1Id = rs.getInt("categoryLevel1Id");
				 int categoryLevel2Id = rs.getInt("categoryLevel2Id");
				 int categoryLevel3Id = rs.getInt("categoryLevel3Id");
				 String fileName = rs.getString("fileName");
				 int isDelete = rs.getInt("isDelete");
				 list.add(new Product(id, name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, fileName, isDelete));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	
	
	
	@Override
	public Product findById(int id) {
		Product pro = null;
		String sql = "SELECT \r\n" + 
				"  `id`,\r\n" + 
				"  `name`,\r\n" + 
				"  `description`,\r\n" + 
				"  `price`,\r\n" + 
				"  `stock`,\r\n" + 
				"  `categoryLevel1Id`,\r\n" + 
				"  `categoryLevel2Id`,\r\n" + 
				"  `categoryLevel3Id`,\r\n" + 
				"  `fileName`,\r\n" + 
				"  `isDelete` \r\n" + 
				"FROM\r\n" + 
				"  `easybuy1`.`easybuy_product` \r\n" + 
				"where id = ?";
		try {
			rs = executeQuery(sql, id);
			while (rs.next()) {
				 String name = rs.getString("name");
				 String description = rs.getString("description");
				 double price = rs.getDouble("price");
				 int stock = rs.getInt("stock");
				 int categoryLevel1Id = rs.getInt("categoryLevel1Id");
				 int categoryLevel2Id = rs.getInt("categoryLevel2Id");
				 int categoryLevel3Id = rs.getInt("categoryLevel3Id");
				 String fileName = rs.getString("fileName");
				 int isDelete = rs.getInt("isDelete");
				 pro = new Product(id, name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, fileName, isDelete);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return pro;
	}


	// 删除一个商品
	@Override
	public int delProById(int id) {
		String sql = "DELETE\n" +
				"FROM\n" +
				"  `easybuy1`.`easybuy_product`\n" +
				"WHERE `id` = ?;\n";
		return executeUpdate(sql,id);
	}
}
