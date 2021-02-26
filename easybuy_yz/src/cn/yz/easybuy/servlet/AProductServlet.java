package cn.yz.easybuy.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.yz.easybuy.entity.Category;
import cn.yz.easybuy.entity.CategoryVo;
import cn.yz.easybuy.entity.Product;
import cn.yz.easybuy.entity.ProductVo;
import cn.yz.easybuy.service.AdminProductService;
import cn.yz.easybuy.service.CategoryService;
import cn.yz.easybuy.service.ProductService;
import cn.yz.easybuy.util.PageBean;

/**
 * Servlet implementation class AdminProduct
 */
@WebServlet("/AProductServlet")
public class AProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService=new ProductService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opr=request.getParameter("opr");
		AdminProductService adminProductService=new AdminProductService();
		CategoryService categoryService=new CategoryService();
		// 获得目录信息
		List<CategoryVo> allCategory = categoryService.findAllCategory();
		// 获得商品信息
		if("selectProduct".equals(opr)) {
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			int pageNo = 1;
			int pageSize = 10;
		    String pageNoStr=request.getParameter("pageNo"); 
			if(null!=pageNoStr) { 
				pageNo=Integer.parseInt(pageNoStr); 
			}		
			PageBean<Product> findpage = productService.findBypage(pageNo, pageSize);
			for (int i = 0; i < findpage.getPageList().size(); i++) {
				int id = findpage.getPageList().get(i).getCategoryLevel3Id();				
				Category category = adminProductService.findById(id);				
				List<Category> categoryList = adminProductService.categoryList(category.getName());
				ProductVo productVo=new ProductVo(findpage.getPageList().get(i), categoryList);
				productVoList.add(productVo);
			}
			request.setAttribute("productVoList", productVoList);
			request.setAttribute("findpage", findpage);
			request.getRequestDispatcher("backend/selectProduct.jsp").forward(request, response);
		}
		//删除
		if("delCategory".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			productService.delProduct(id);
			request.getRequestDispatcher("AProductServlet?opr=selectProduct").forward(request, response);
		}
		//修改跳转
		if("updateCategory".equals(opr)) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Product product = productService.findById(id);
			product.getCategoryLevel3Id();
			request.setAttribute("product", product);
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/updateProduct.jsp").forward(request, response);
		}
		//修改商品的方法
		if("update".equals(opr)) {				
			String name=null;
			String description=null;
			Double price=0.0;
			int id=0;
			String FName2=null;
			int stock=0;
			int categoryLevel1Id=0;
			int categoryLevel2Id=0;
			int categoryLevel3Id=0;
			String FName=null;
			String path=null;
			//判断是否是上传表单
			boolean isMulti = ServletFileUpload.isMultipartContent(request);			
			if(isMulti){
				//获取上传到哪的绝对路径
				path=request.getServletContext().getRealPath("images/");
				//获取磁盘文件项工厂，对request语句进行加工
				FileItemFactory factory = new DiskFileItemFactory();
				//创建一个解析request的对象
				ServletFileUpload upload = new ServletFileUpload(factory);				
				//设置上传大小
				upload.setSizeMax(1024*60);
				upload.setHeaderEncoding("UTF-8");
				try {
					//解析request语句，返回一个items集合
					List<FileItem> itemList = upload.parseRequest(request);
					for (FileItem item : itemList) {
						//如果是普通表单
						if(item.isFormField()) {
							String fieldName = item.getFieldName();
							if("type".equals(fieldName)){
								String type = item.getString("UTF-8");
								List<Category> categoryList = adminProductService.categoryList(type);
								categoryLevel1Id=categoryList.get(0).getId();
								categoryLevel2Id=categoryList.get(1).getId();
								categoryLevel3Id=categoryList.get(2).getId();									
							}
							if("id".equals(fieldName)){
								String idStr = item.getString();
								id = Integer.parseInt(idStr);
							}
							if("name".equals(fieldName)){
								name = item.getString("UTF-8");
							}
							if("FName2".equals(fieldName)){
								FName2 = item.getString("UTF-8");
							}
							if("description".equals(fieldName)){
								description = item.getString("UTF-8");	
							}
							if("price".equals(fieldName)){
								String priceStr = item.getString();
								price = Double.parseDouble(priceStr);
							}
							if("stock".equals(fieldName)){
								String stockStr = item.getString();
								stock = Integer.parseInt(stockStr);
							}							
						}else {//否则就是上传表单
							FName = item.getName();
							if(FName==null||FName=="") {								
							}else {
								item.write(new File(path,FName));	
							}
						}
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("不是上传表单");
			}		
			if(FName==null||FName=="") {
				productService.updateProduct(new Product(id, name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, FName2, 0));
			}else {				
				productService.updateProduct(new Product(id, name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, FName, 0));
			}
												
			request.getRequestDispatcher("AProductServlet?opr=selectProduct").forward(request, response);
		}
		//新增的跳转
		if("addCategory".equals(opr)) {		
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/addProduct.jsp").forward(request, response);
		}
		//新增商品的方法
		if("add".equals(opr)) {				
			String name=null;
			String description=null;
			Double price=0.0;
			int stock=0;
			int categoryLevel1Id=0;
			int categoryLevel2Id=0;
			int categoryLevel3Id=0;
			String FName=null;
			//判断是否是上传表单
			boolean isMulti = ServletFileUpload.isMultipartContent(request);			
			if(isMulti){
				//获取上传到哪的绝对路径
				String path=request.getServletContext().getRealPath("images/");
				//获取磁盘文件项工厂，对request语句进行加工
				FileItemFactory factory = new DiskFileItemFactory();
				//创建一个解析request的对象
				ServletFileUpload upload = new ServletFileUpload(factory);				
				//设置上传大小
				upload.setSizeMax(1024*60);
				upload.setHeaderEncoding("UTF-8");
				try {
					//解析request语句，返回一个items集合
					List<FileItem> itemList = upload.parseRequest(request);
					for (FileItem item : itemList) {
						//如果是普通表单
						if(item.isFormField()) {
							String fieldName = item.getFieldName();
							if("type".equals(fieldName)){
								String type = item.getString("UTF-8");
								List<Category> categoryList = adminProductService.categoryList(type);
								categoryLevel1Id=categoryList.get(0).getId();
								categoryLevel2Id=categoryList.get(1).getId();
								categoryLevel3Id=categoryList.get(2).getId();
							}
							if("name".equals(fieldName)){
								name = item.getString("UTF-8");
							}
							if("description".equals(fieldName)){
								description = item.getString("UTF-8");	
							}
							if("price".equals(fieldName)){
								String priceStr = item.getString();
								price = Double.parseDouble(priceStr);
							}
							if("stock".equals(fieldName)){
								String stockStr = item.getString();
								stock = Integer.parseInt(stockStr);
							}							
						}else {//否则就是上传表单
							FName = item.getName();
							item.write(new File(path,FName));
						}
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("不是上传表单");
			}
			productService.addProduct(new Product(0, name, description, price, stock, categoryLevel1Id, categoryLevel2Id, categoryLevel3Id, FName, 0));						
			request.getRequestDispatcher("AProductServlet?opr=selectProduct").forward(request, response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
