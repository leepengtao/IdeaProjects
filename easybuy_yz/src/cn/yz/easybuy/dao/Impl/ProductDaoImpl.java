package cn.yz.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.ProductDao;
import cn.yz.easybuy.entity.Product;

public class ProductDaoImpl extends BaseDao implements ProductDao {
	//找到有多少商品的总数
	@Override
	public int findTotalCount() {
		String sql="SELECT COUNT(1) FROM easybuy_product";
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
	//找到所有商品
	@Override
	public List<Product> findByList(int pageNo, int pageSize) {
		List<Product> list=new ArrayList<Product>();
		String sql="SELECT id,`name`,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete FROM easybuy_product LIMIT ?, ?";
		try {
			conn =getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String description=rs.getString("description");
				Double price=rs.getDouble("price");      //价格
				int stock=rs.getInt("stock");     //库存
				int categoryLevel1Id=rs.getInt("categoryLevel1Id");   //分类1
				int categoryLevel2Id=rs.getInt("categoryLevel2Id");	//分类2
				int categoryLevel3Id=rs.getInt("categoryLevel3Id");	//分类3
				String fileName=rs.getString("fileName");	//文件名称
				int isDelete=rs.getInt("isDelete");   //是否删除(1：删除 0：未删除
				list.add(new Product(id,name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, fileName, isDelete));	
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,rs);
		}
		return list;
	}
	//通过Id找到商品对象
	@Override
	public Product findById(int id) {
		Product product=null;
		String sql="SELECT id,`name`,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete FROM easybuy_product WHERE id=?";
		try {
			conn =getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				String description=rs.getString("description");
				Double price=rs.getDouble("price");      //价格
				int stock=rs.getInt("stock");     //库存
				int categoryLevel1Id=rs.getInt("categoryLevel1Id");   //分类1
				int categoryLevel2Id=rs.getInt("categoryLevel2Id");	//分类2
				int categoryLevel3Id=rs.getInt("categoryLevel3Id");	//分类3
				String fileName=rs.getString("fileName");	//文件名称
				int isDelete=rs.getInt("isDelete");   //是否删除(1：删除 0：未删除
				product=new Product(id,name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, fileName, isDelete);	
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,rs);
		}
		return product;
	}
	//新增商品
	@Override
	public int addProduct(Product product) {
		String sql="INSERT INTO easybuy_product (id,NAME,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete)VALUES(0,?,?,?,?,?,?,?,?,0)";
		Object[] args={product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName()};
		return executeUpdate(sql, args);		
	}
	
	//修改商品
	@Override
	public int updateProduct(Product product) {
		String sql="UPDATE easybuy_product SET `name` =?,description =?,price =?,stock =?,categoryLevel1Id =?,categoryLevel2Id =?,categoryLevel3Id =?,fileName =? WHERE id =?";
		Object[] args={product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName(),product.getId()};
		return executeUpdate(sql, args);		
	}
	
	//删除商品
	@Override
	public int delProduct(int id) {
		String sql="DELETE FROM easybuy_product WHERE id =?";
		return executeUpdate(sql, id);		
	}
	
	//删除目录的同时删除目录下的商品
	@Override
	public int delCategoryByProduct(int parentId) {
		String sql="DELETE FROM easybuy_product WHERE categoryLevel1Id =? OR categoryLevel2Id=? OR categoryLevel3Id=?";
		Object[] args= {parentId,parentId,parentId};
		return executeUpdate(sql, args);		
	}

}
