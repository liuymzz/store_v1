package web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cart;
import entities.CartItem;
import entities.Order;
import entities.OrderItem;
import entities.PageBean;
import entities.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.UUIDUtils;

public class OrderServlet extends BaseServlet {

	/**
	 * 添加订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 先判断用户是否登陆
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			request.setAttribute("msg", "您还没登陆,请先登录");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
		}

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// 封装order
		Order order = new Order();
		order.setOid(UUIDUtils.getId());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setUser(user);

		// 封装order中的orderItem
		for (CartItem cartItem : cart.getItems().values()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setItemid(UUIDUtils.getId());
			orderItem.setSubtotal(cartItem.getTotal());
			order.getItems().add(orderItem);
		}

		// 调用orderService将订单添加至数据库
		OrderService orderService = new OrderServiceImpl();
		orderService.add(order);

		request.setAttribute("order", order);

		cart.clear();

		return "/jsp/order_info.jsp";
	}

	/**
	 * 从数据库中分页查询出用户的订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//执行操作之前需要先判断是否登陆
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			request.setAttribute("msg", "执行该操作之前请先登陆");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
		}
		
		
		// 获取页数
		int currPage = 1;
		// 每页中数据的数量
		int pageSize = 10;

		try {
			// 为了避免用户非法修改参数
			currPage = Integer.parseInt(request.getParameter("currPage"));
		} catch (Exception e) {
			request.setAttribute("msg", "请输入正确的页数");
			try {
				request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//由orderService处理
		OrderService orderService = new OrderServiceImpl();
		PageBean<Order> pageBean = orderService.findByPage(currPage,pageSize,user);
		
		//添加到域对象中
		request.setAttribute("pageBean", pageBean);

		//转发
		return "/jsp/order_list.jsp";
	}
}
