package cn.bdqn.easybuy.service;

import java.util.List;

import cn.bdqn.easybuy.dao.ProductDao;
import cn.bdqn.easybuy.dao.Impl.ProductDaoImpl;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.util.PageBean;

public class ProductService {

	// 新建Dao层对象, 索要总商品数TotalCount 和 单页面要显示的东西, 传入第几个页面(pageNo) 和单页面显示的条数(PageSize)
	private ProductDao productDao = new ProductDaoImpl();
	// 分页
	public PageBean<Product> findByPage(int pageNo, int pageSize){
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPageSize(pageSize); 								// 传入每页显示的条数
		int totalCount = productDao.findTotalCount();			 // 查询商品的总记录数
		pageBean.setTotalCount(totalCount); 						// 传入商品的总记录数
		pageBean.setPageNo(pageNo); 								// 传入当前页码数
		List<Product> pageList = productDao.findByList(pageBean.getPageNo(), pageBean.getPageSize()); // 获取页码数和一页多少个记录, 然乎返回记录列表 
		pageBean.setPageList(pageList); // 把列表存入pageBean
		return pageBean;
	}

	// 查找商品
	public Product findById(int id) {
		return productDao.findById(id);
	}

	// 删除商品
	public int delById(int id) {
		return productDao.delProById(id);
	}
}

