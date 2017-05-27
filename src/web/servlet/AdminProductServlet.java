package web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import entities.Category;
import entities.PageBean;
import entities.Product;
import entities.UpLoadImg;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import utils.UUIDUtils;

public class AdminProductServlet extends BaseServlet {

	/**
	 * 分页查找所有商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 10;

		ProductService productService = new ProductServiceImpl();
		PageBean<Product> pageBean = productService.findAllByPage(currPage, pageSize);

		request.setAttribute("pageBean", pageBean);

		return "/admin_jsp/product.jsp";
	}

	/**
	 * 因为添加商品页面中需要查询分类数据，所以需要经过servlet
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryService categoryService = new CategoryServiceImpl();

		List<Category> list = categoryService.findAll();
		request.setAttribute("category", list);

		return "/admin_jsp/add_product.jsp";
	}

	/**
	 * 新增商品时异步上传图片的部分
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String uploadPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 先获取图片存放路径
		String path = request.getSession().getServletContext().getRealPath("products");

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

		// 设置头文件编码格式
		servletFileUpload.setHeaderEncoding("utf-8");

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 存放实际文件路径
		String filePath = null;
		// 存放实际文件名
		String fileName = null;

		List<FileItem> list = servletFileUpload.parseRequest(request);
		if (list != null && list.size() > 0) {
			for (FileItem fileItem : list) {
				if (!fileItem.isFormField()) {
					// 生成实际文件名
					fileName = UUIDUtils.getCode() + "-" + (new File(fileItem.getName()).getName());
					// 生成文件存放路径
					filePath = path + File.separator + fileName;
					File storeFile = new File(filePath);
					// 最终将文件写入磁盘
					fileItem.write(storeFile);
				}
			}
		}

		// 将要返回的数据先封装成实体类
		UpLoadImg img = new UpLoadImg();
		img.setPath(request.getContextPath() + "/products/" + fileName);
		img.setpName(fileName);

		// 再转换成json格式的数据
		String string = new Gson().toJson(img);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(string);
		return null;
	}

	/**
	 * 添加商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 封装商品实体类，需要手动设置id跟时间
		Product product = new Product();
		product.setPid(UUIDUtils.getId());
		product.setPdate(new Date());
		BeanUtils.populate(product, request.getParameterMap());
		product.setPimage("products/" + product.getPimage());
		ProductService productService = new ProductServiceImpl();

		try {
			productService.add(product);
		} catch (Exception e) {
			request.setAttribute("msg", "添加失败");
			return "/admin_jsp/add_product.jsp";
		}

		CategoryService categoryService = new CategoryServiceImpl();

		List<Category> list = categoryService.findAll();
		request.setAttribute("category", list);

		request.setAttribute("msg", "添加成功");
		return "/admin_jsp/add_product.jsp";
	}

	
	/**
	 * 删除商品
	 * @throws Exception 
	 */
	public  String del(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pid = request.getParameter("pid");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		
		ProductService productService = new ProductServiceImpl();
		productService.del(pid,realPath);
		
		return null;
	}

}


