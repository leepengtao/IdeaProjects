package cn.yz.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import cn.yz.easybuy.dao.ProductDao;
import cn.yz.easybuy.dao.Impl.ProductDaoImpl;
import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.util.PageBean;

public class ProductService {
	
	private ProductDao productDao=new ProductDaoImpl();
	
	public PageBean<Product> findBypage(int pageNo, int pageSize){
		PageBean<Product> pbProduct=new PageBean<Product>();
		List<Product> list=new ArrayList<Product>();						
		pbProduct.setPageSize(pageSize);
		int totalCount=productDao.findTotalCount();
		pbProduct.setTotalCount(totalCount);
		pbProduct.setPageNo(pageNo);
		list=productDao.findByList(pbProduct.getPageNo(), pageSize);
		pbProduct.setPageList(list);		
		return pbProduct;
	}
	public Product findById(int id) {
		return productDao.findById(id);
	}
	public int addProduct(Product product) {
		return productDao.addProduct(product);		
	}
	public int updateProduct(Product product) {
		return productDao.updateProduct(product);		
	}
	public int delProduct(int id) {
		return productDao.delProduct(id);		
	}
	public int delCategoryByProduct(int parentId) {
		return productDao.delCategoryByProduct(parentId);		
	}	
}
