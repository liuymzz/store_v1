package web.servlet;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import entities.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

public class CategoryServlet extends BaseServlet {
       

	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> list = categoryService.findAll();
		
		Gson gson = new  Gson();
		String json = gson.toJson(list);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		
		return null;
	}

}
