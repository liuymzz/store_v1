package web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cart;
import entities.CartItem;
import entities.Product;
import service.impl.ProductServiceImpl;

public class CartServlet extends BaseServlet {

	/**
	 * 从session中获取购物车
	 * 
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		//从session中获取购物车对象,如果没有则新建一个放入
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		return cart;
	}

	/**
	 * 添加至购物车中
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String add2Cart(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		int count = 0;

		try {
			count = Integer.parseInt(request.getParameter("count"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "请输入正确的数量");
			request.getRequestDispatcher(request.getContextPath() + "/jsp/msg.jsp");
		}

		Product product = null;
		try {
			product = new ProductServiceImpl().getById(pid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		CartItem item = new CartItem(product, count);
		//将购物车项添加至购物车中
		getCart(request).add2Cart(item);

		try {
			response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 移除某个购物车项
	 * @param request
	 * @param response
	 * @return
	 */
	public String remove(HttpServletRequest request,HttpServletResponse response){
		String pid = request.getParameter("pid");
		
		getCart(request).removeFromCart(pid);
		
		try {
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 */
	public String clear(HttpServletRequest request,HttpServletResponse response){
		getCart(request).clear();
		try {
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
