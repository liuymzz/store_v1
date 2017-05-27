package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {

	/**
	 * 从数据库中查询数据并添加到域中再跳转至index.jsp页面
	 */
	public String index(HttpServletRequest request, HttpServletResponse response){

		ProductService productService = new ProductServiceImpl();
		
		//查询热门商品
		List<Product> hotList = new ArrayList<>();
		try {
			hotList = productService.findHot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//查询最新商品
		List<Product> newList = new ArrayList<>();
		try {
			newList = productService.findNew();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将数据放置域对象中
		request.setAttribute("hotList", hotList);
		request.setAttribute("newList", newList);
		
		//请求转发
		return "/jsp/index.jsp";
	}

}
