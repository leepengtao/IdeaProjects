package cn.bdqn.easybuy.service.backendservice;

import cn.bdqn.easybuy.dao.Impl.ProductDaoImpl;
import cn.bdqn.easybuy.dao.ProductDao;
import cn.bdqn.easybuy.entity.CategoryVo;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.service.CategoryService;

import java.util.List;

public class ProductManageService {
    private ProductDao productDao = new ProductDaoImpl();

    // 返回商品列表
    public List<Product> showAllPro() {


        return null;
    }

    // 查询总商品数
    public int totalCount() {
        return productDao.findTotalCount();
    }
}
