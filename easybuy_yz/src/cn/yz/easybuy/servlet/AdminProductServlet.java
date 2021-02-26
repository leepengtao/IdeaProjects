package cn.yz.easybuy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yz.easybuy.entity.Category;
import cn.yz.easybuy.entity.CategoryVo;
import cn.yz.easybuy.service.AdminProductService;
import cn.yz.easybuy.service.CategoryService;
import cn.yz.easybuy.service.ProductService;

/**
 * ��ƷĿ¼��ɾ�Ĳ�
 * Servlet implementation class AdminProductServlet
 */
@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService=new CategoryService();
		AdminProductService adminProductService=new AdminProductService();
		ProductService productService=new ProductService();
		String opr = request.getParameter("opr");
		// ���Ŀ¼��Ϣ
		List<CategoryVo> allCategory = categoryService.findAllCategory();
		if("findAllCategory".equals(opr)) {						
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/selectProductCategory.jsp").forward(request, response);
		}
		//������ת
		if("addCategory".equals(opr)) {		
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/addProductCategory.jsp").forward(request, response);
		}
		//����Ŀ¼
		if("add".equals(opr)) {
			String type = request.getParameter("type");
			String type1 = request.getParameter("type1");
			String name = request.getParameter("name");	
			String[] types= {type,type1};
			if("һ��Ŀ¼֮��".equals(type)&&"����Ŀ¼֮��".equals(type1)) {				
				adminProductService.addCategory(new Category(0, name, 0, 1, null));
			}else{
				Category category = adminProductService.fingChoice(types);
				int parentId = category.getId();
				int type2=category.getType()+1;
				adminProductService.addCategory(new Category(0, name, parentId, type2, null));								
			}
			request.getRequestDispatcher("AdminProductServlet?opr=findAllCategory").forward(request, response);
		}
		//�޸���ת
		if("updateCategory".equals(opr)) {
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/updateProductCategory.jsp").forward(request, response);
		}
		//�޸ĵķ���
		if("update".equals(opr)) {
			String typeOne = request.getParameter("typeOne");
			String typeTow = request.getParameter("typeTow");
			String typeThree = request.getParameter("typeThree");			
			String name = request.getParameter("name");
			String type1 = request.getParameter("type1");
			String type2 = request.getParameter("type2");
			int parentId =0;
			int type=0;
			String[] types= {type1,type2};
			String[] types2= {typeOne,typeTow,typeThree};
			//ѡ���޸ĵ��ĸ�Ŀ¼��
			if("һ��Ŀ¼֮��".equals(type1)&&"����Ŀ¼֮��".equals(type2)) {				
				type=type+1;
			}else{
				Category category = adminProductService.fingChoice(types);
				type=category.getType()+1;
				parentId=category.getId();								
			}
			//�ҵ���Ҫ�޸ĵĶ���
			Category category2 = adminProductService.fingChoice(types2);
			int id = category2.getId();			
			adminProductService.updateCategory(name, parentId, type, id);
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("AdminProductServlet?opr=findAllCategory").forward(request, response);
		}
		//ɾ����ת
		if("delCategory".equals(opr)) {
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("backend/delProductCategory.jsp").forward(request, response);
		}
		//ɾ������
		if("del".equals(opr)) {
			String typeOne = request.getParameter("typeOne");
			String typeTow = request.getParameter("typeTow");
			String typeThree = request.getParameter("typeThree");
			String[] types= {typeOne,typeTow,typeThree};
			//�ҵ���Ҫɾ���Ķ���
			Category category = adminProductService.fingChoice(types);
			int id=category.getId();
			for (int i = 0; i < allCategory.size(); i++) {
				int id2=allCategory.get(i).getCategory().getId();
				if(id2!=id) {
					for (int j = 0; j < allCategory.get(i).getCategoryListVo().size(); j++) {
						int id3=allCategory.get(i).getCategoryListVo().get(j).getCategory().getId();
						if(id3!=id) {
							for (int k = 0; k < allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().size(); k++) {
								int id4=allCategory.get(i).getCategoryListVo().get(j).getCategoryListVo().get(k).getCategory().getId();
								if(id4==id) {
									adminProductService.delCategory(id4);
								}
							}
						}else {
							adminProductService.delCategory(id3);
							adminProductService.delParentIdCategory1(id3);
						}
					}
				}else {
					for (int j = 0; j < allCategory.get(i).getCategoryListVo().size(); j++) {
						int parentId = allCategory.get(i).getCategoryListVo().get(j).getCategory().getId();
						adminProductService.delParentIdCategory1(parentId);
					}					
					adminProductService.delCategory(id2);
					adminProductService.delParentIdCategory1(id2);
				}
			}
			productService.delCategoryByProduct(id);
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("AdminProductServlet?opr=findAllCategory").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
