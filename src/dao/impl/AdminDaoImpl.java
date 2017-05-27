package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.AdminDao;
import entities.Admin;
import utils.DataSourceUtils;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(String username, String password) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM admin WHERE admin_name=? AND admin_password=?";
		Admin admin = queryRunner.query(sql, new BeanHandler<>(Admin.class),username,password);
		
		return admin;
	}

}
