package web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.Order;
import entities.PageBean;
import service.OrderService;
import service.impl.OrderServiceImpl;

public class AdminOrderServlet extends BaseServlet {

	/**
	 * 查询所有的订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currPage = 1;
		currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 10;

		OrderService orderService = new OrderServiceImpl();
		PageBean<Order> pageBean = orderService.findAllByPage(currPage, pageSize);

		request.setAttribute("bean", pageBean);
		return "/admin_jsp/orders.jsp";
	}

	/**
	 * 查询单个订单的详情，需要响应json格式数据
	 * 
	 * @throws Exception
	 */
	public String getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");

		OrderService orderService = new OrderServiceImpl();

		Order order = orderService.getById(oid);

		Gson gson = new Gson();
		String json = gson.toJson(order.getItems());
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(json);

		return null;
	}
}
