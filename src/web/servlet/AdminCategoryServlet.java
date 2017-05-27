package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import utils.UUIDUtils;

public class AdminCategoryServlet extends BaseServlet {

	/**
	 * 查询分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll(HttpServletRequest request,HttpServletResponse response){
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> list = null;
		try {
			list = categoryService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		
		return "/admin_jsp/category.jsp";
	}
	
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String del(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		CategoryService categoryService = new CategoryServiceImpl();
		
		try {
			categoryService.del(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 修改分类名称
	 * @param request
	 * @param response
	 * @return
	 */
	public String update(HttpServletRequest request,HttpServletResponse response){
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		CategoryService categoryService = new  CategoryServiceImpl();
		
		try {
			categoryService.update(cid,cname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String add(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCid(UUIDUtils.getCode());
		category.setCname(cname);
		
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.add(category);
		
		return null;
	}
}
