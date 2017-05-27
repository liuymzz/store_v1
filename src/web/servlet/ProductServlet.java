package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PageBean;
import entities.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;
import utils.CookUtils;
import utils.StringUtils;

/**
 * product servlet类
 * 
 * @author lym14
 *
 */
public class ProductServlet extends BaseServlet {

	/**
	 * 通过指定id获取商品对象并跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String getByid(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 实现查询部分功能
		 */
		// 从request域中获取id
		String pid = (String) request.getParameter("pid");

		ProductService productService = new ProductServiceImpl();
		// 根据id查找product
		Product product = null;
		try {
			product = productService.getById(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将product放置域对象中
		request.setAttribute("product", product);

		/**
		 * 实现浏览历史记录功能
		 */
		// 获取浏览记录cookie,指定保存浏览记录的cookie名为"history",格式为"***,***,**"
		Cookie[] cookies = request.getCookies();
		Cookie history = CookUtils.getCookieByName("history", cookies);
		String hStr = null; // 用来缓存cookies记录

		// 判断是否存在该cookie,如果不存在直接将pid放入缓存中
		if (history != null) {
			// 如果存在,先将String形式的cookie分割成list
			hStr = history.getValue();
			String[] strings = hStr.split("-");
			List<String> list = StringUtils.strings2List(strings);
			int index = 0;
			
			if (list.size() > 6) {
				list.remove(6);
			}

			// 再判断该商品存不存在于记录中,如果存在,则将位置移至第一个,如果不存在就直接放在第一个
			for (String string : list) {
				if (pid.equals(string)) {
					list.remove(pid);
					list.add(0, pid);
					break;
				}
				index++;
			}

			// 如果index的值大于等于list的长度则说明该商品不存在于记录中,直接将该商品的id添加至list中的第一个即可
			if (index >= list.size()) {
				list.add(0, pid);
			}

			// 最后将list转换成字符串即可
			hStr = StringUtils.list2String(list, "-");

		} else {
			hStr = pid;
		}

		Cookie cookie = new Cookie("history", hStr);
		cookie.setMaxAge(60*60*24*100);
		response.addCookie(cookie);

		// 跳转页面
		return "/jsp/product_info.jsp";
	}

	/**
	 * 分类显示数据
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");

		/**
		 * 关于分页查询的部分
		 */
		// 获取当前的页数
		int currPage = 1;
		int pageSize = 12;
		try {
			/**
			 * 从请求中或许当前是第几页,因为页码是通过链接传递 可能会有人手动更改为其他值从而无法解析为int数值,所以需要异常处理
			 */
			currPage = Integer.parseInt(request.getParameter("currPage"));
		} catch (Exception e) {
			request.setAttribute("msg", "请输入正确的页数");
			try {
				request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
				return null;
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		ProductService productService = new ProductServiceImpl();

		PageBean pageBean = null;
		try {
			pageBean = productService.findByPage(cid, currPage, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将pageBean放置域对象中
		request.setAttribute("pageBean", pageBean);

		/**
		 * 浏览记录部分
		 */
		// 先获取cookie
		Cookie[] cookies = request.getCookies();
		Cookie cookie = CookUtils.getCookieByName("history", cookies);
		String[] hStrs = null;
		List<Product> historyList = new ArrayList<>();

		if (cookie != null) {
			hStrs = cookie.getValue().split("-");
			// 利用id分别获取商品信息并放入list
			for (String string : hStrs) {
				try {
					historyList.add(productService.getById(string));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
		
		// 将list放入request域中
		request.setAttribute("hList", historyList);

		// 页面跳转
		return "/jsp/product_list.jsp";
	}

	
}
