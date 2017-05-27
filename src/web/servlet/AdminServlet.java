package web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;
import service.AdminService;
import service.impl.AdminServiceImpl;

public class AdminServlet extends BaseServlet {

	
	
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "/admin_index.jsp";
	}

	/**
	 * admin登录操作
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AdminService adminService = new AdminServiceImpl();
		
		Admin admin = adminService.login(username,password);
		
		if (admin == null) {
			request.setAttribute("msg", "您的账户或者密码不正确");
			return "/admin_index.jsp";
		}
		
		request.getSession().setAttribute("admin", admin);
		
		return "/admin_jsp/index.jsp";
	}
	
}
