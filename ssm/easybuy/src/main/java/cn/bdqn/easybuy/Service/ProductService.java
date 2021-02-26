package cn.bdqn.easybuy.Service;

import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.util.PageBean;

public interface ProductService {


    // 按页码查找商品
    PageBean<Product> findByPage(int pageNo, int pageSize);
    // 查找商品
    public Product findById(int id);
    // 删除商品
    public int delById(int id);
}
