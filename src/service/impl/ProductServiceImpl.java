package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entities.PageBean;
import entities.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	/**
	 * 最热商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findHot();
	}

	/**
	 * 最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findNew();
	}

	/**
	 * 根据id查找product
	 */
	@Override
	public Product getById(String pid) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.getById(pid);
	}

	@Override
	public PageBean findByPage(String cid, int currPage,int pageSize) throws Exception {
		ProductDao productDao = new ProductDaoImpl();

		//分别获取分页查询的数据和分类下所有数据的总量
		List<Product> list = productDao.findByPage(cid,currPage,pageSize);
		int totalcount = productDao.getTotal(cid);
		
		PageBean pageBean = new PageBean(list, currPage, pageSize, totalcount);
		
		return pageBean;
	}

	@Override
	public PageBean<Product> findAllByPage(int currPage, int pageSize) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		List<Product> list = null;
		int totalCount = 0;
		
		list = productDao.findAllByPage(currPage,pageSize);
		totalCount = productDao.getAllTotal();
		
		return new PageBean<Product>(list, currPage, pageSize, totalCount);
	}

	@Override
	public void add(Product product) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		productDao.add(product);
		
	}

	
	
	@Override
	public void del(String pid,String realPath) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		
		//需要先获取商品图片的路径将其删除
		Product product = productDao.getById(pid);
		File file = new File(realPath+product.getPimage());
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		
		//再从数据库中删除数据
		productDao.del(pid);
	}

}
