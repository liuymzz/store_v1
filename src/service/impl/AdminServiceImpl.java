package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entities.Admin;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

	@Override
	public Admin login(String username, String password) throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.login(username,password);
	}

}
