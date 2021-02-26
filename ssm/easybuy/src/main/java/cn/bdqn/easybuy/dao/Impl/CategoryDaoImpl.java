package cn.bdqn.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.CategoryDao;
import cn.bdqn.easybuy.entity.Category;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	@Override
	public List<Category> findByParentId(int parentId) {

		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT \r\n" + "  `id`,\r\n" + "  `name`,\r\n" + "  `parentId`,\r\n" + "  `type`,\r\n"
				+ "  `iconClass` \r\n" + "FROM\r\n" + "  `easybuy1`.`easybuy_product_category` " + "WHERE `parentId`=?";
		try {
			rs = executeQuery(sql, parentId);
			while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int type = rs.getInt("type");
			String iconClass = rs.getString("iconClass");
			list.add(new Category(id, name, parentId, type, iconClass));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

}
