package cn.bdqn.easybuy.dao;

import java.util.List;

import cn.bdqn.easybuy.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
	
	// 查询所有记录
	@Select("SELECT COUNT(1) FROM `easybuy_product`")
	int findTotalCount();
	
	// 分页查询
	@Select("SELECT \n" +
			"  `id`,\n" +
			"  `name`,\n" +
			"  `description`,\n" +
			"  `price`,\n" +
			"  `stock`,\n" +
			"  `categoryLevel1Id`,\n" +
			"  `categoryLevel2Id`,\n" +
			"  `categoryLevel3Id`,\n" +
			"  `fileName`,\n" +
			"  `isDelete` \n" +
			"FROM\n" +
			"  `easybuy1`.`easybuy_product` \n" +
			"LIMIT #{pageNo}, #{pageSize} ;\n")
	List<Product> findByList(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

	// 商品详情页的查询
	@Select("select * from `easybuy1`.`easybuy_product` where id = #{id}")
	Product findById(@Param("id")int id);

	// 删除一个商品
	int delProById(int id);
}
