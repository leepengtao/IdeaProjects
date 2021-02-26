package cn.bdqn.easybuy.Service.Impl;

import cn.bdqn.easybuy.Service.ProductService;
import cn.bdqn.easybuy.dao.ProductDao;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;
    @Override
    public PageBean<Product> findByPage(int pageNo, int pageSize) {
        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPageSize(pageSize); 								// 传入每页显示的条数
        int totalCount = productDao.findTotalCount();			 // 查询商品的总记录数
        pageBean.setTotalCount(totalCount); 						// 传入商品的总记录数
        pageBean.setPageNo(pageNo); 								// 传入当前页码数, 是先存在用
        if (pageNo > 1) {
            pageNo = (pageNo - 1) * pageSize;
        }
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
