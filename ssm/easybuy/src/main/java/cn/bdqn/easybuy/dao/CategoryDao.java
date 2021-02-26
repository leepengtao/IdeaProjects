package cn.bdqn.easybuy.dao;

import java.util.List;

import cn.bdqn.easybuy.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao {
	@Select("SELECT \r\n" + "  `id`,\r\n" + "  `name`,\r\n" + "  `parentId`,\r\n" + "  `type`,\r\n"
			+ "  `iconClass` \r\n" + "FROM\r\n" + "  `easybuy1`.`easybuy_product_category` " + "WHERE `parentId`= #{parentId}")
	List<Category> findByParentId(int parentId);
}
