package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 公共Servlet
 */
public class BaseServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 利用反射获取子类的字节码
		Class clazz = this.getClass();

		
		String m = request.getParameter("method");

		//如果m为空则表示不存在指定方法
		if (m == null) {
			m = "index";
		}
		
		Method method = null;
		

			// 根据指定的参数获取子类的方法
			try {
				method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		String path = null;

		try {
			// 执行子类的方法得到返回值并利用返回值进行跳转
			path = (String) method.invoke(this, request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果返回的路径不为空则请求转发至指定路径
		if (path != null) {
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	// 确保所有的子类都有index方法
	public String index(HttpServletRequest request, HttpServletResponse response) {

		return "/index.jsp";
	}

}
