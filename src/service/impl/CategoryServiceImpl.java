package service.impl;

import java.util.List;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import entities.Category;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.findAll();
	}

	@Override
	public void del(String cid) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.del(cid);
		
		
	}

	@Override
	public void update(String cid, String cname) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.update(cid,cname);
	}

	@Override
	public void add(Category category) throws Exception {

		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.add(category);
		
	}

}
