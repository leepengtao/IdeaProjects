package cn.bdqn.easybuy.servlet.backendservlet;

import cn.bdqn.easybuy.entity.CategoryVo;
import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.service.CategoryService;
import cn.bdqn.easybuy.service.ProductService;
import cn.bdqn.easybuy.service.backendservice.ProductManageService;
import cn.bdqn.easybuy.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductManageServlet")
public class ProductManageServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        // 显示所有商品，
        if ("showall".equals(opr)) {

            // 查询商品总数
            // 商品分页
            int pageNo = 1;// 定义商品起始页
            String pageNoStr = request.getParameter("pageNo");// 获得商品页数
            if (null!=pageNoStr) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            int pageSize = 10;
            PageBean<Product> proPage = productService.findByPage(pageNo, pageSize);

            // 直接讲proPage传过去， 只显示了商品信息， 并没有按目录显示商品，这才是难点
            // 想要解决这个问题,就要看老师是怎么解决的, 这里参照index.jsp
            // 如果cv.category.id == product.id， 那么就显示商品

            request.setAttribute("proPage", proPage);// 存入结果
            request.getRequestDispatcher("/backend/ProManage.jsp").forward(request,response);// 转发
        }
        // 按类目查询
        if("showcat".equals(opr)){
            List<CategoryVo> cvList = categoryService.findAllCategory();
            // 先不做，用ajax做，要不然会产生太多的页面
        }


        // 删除一件商品
        if ("delProduct".equals(opr)) {

            // 获取要删除商品的id
            String proIdStr = request.getParameter("id");
            int proId = Integer.parseInt(proIdStr);
            int ret =  productService.delById(proId);
            if (ret > 0) {// 表示执行成功，返回Administrator.jsp
                request.getRequestDispatcher("ProductManageServlet?opr=showall").forward(request, response);
            }
        }

        // 批量删除
        if ("delSelected".equals(opr)) {
            String[] userIdStrs = request.getParameterValues("uid");
            /*for (int i = 0; i<userIdStrs.length;i++) {
                int ret = userService.delUserById(Integer.parseInt(userIdStrs[i]));
                int[] rets = new int[];
                rets
            }*/ // 这段代码本想循环调用删除方法,
            int delFailCount = 0;
            for (String id : userIdStrs) {
                int ret = productService.delById(Integer.parseInt(id));
                if (ret == 0) {
                    delFailCount++;
                }
            }
            request.setAttribute("delFailCount",delFailCount);
            request.getRequestDispatcher(request.getContextPath()+"/ProductManageServlet?opr=showall").forward(request,response);
        }


        // 改
        if ("jumpToModUPro".equals(opr)){
            // 获取商品id
            String productIdStr = request.getParameter("productId");
            int productId = Integer.parseInt(productIdStr);
            Product pro = productService.findById(productId);// 找到商品

            request.setAttribute("pro",pro);// 回显
            request.getRequestDispatcher("/backend/modPro.jsp").forward(request,response);
        }
        // 增

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
