package dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import dao.UserDao;
import entities.User;
import utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * 用户注册的数据库插入操作
	 */
	@Override
	public void register(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into `store_v1`.`user`(" + "`uid`,`username`,`password`,`name`,"
				+ "`email`,`telephone`,`birthday`,`sex`," + "`state`,`code`)values(?,?,?,?,?,?,?,?,?,?);";

		queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
	}

	
	/**
	 * 根据指定的user code属性查询user对象
	 */
	@Override
	public User getUserByCode(String code) throws Exception {
		User user = null;
		String sql = "select * from user where code=?";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		user = queryRunner.query(sql, new BeanHandler<>(User.class),code);
		return user;
	}

	
	/**
	 * 激活user账户
	 */
	@Override
	public void active(User user) throws Exception {
		//更改user中的状态数据即可
		String sql = "UPDATE USER SET state=1,code=NULL WHERE uid='"+user.getUid()+"'";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		queryRunner.update(sql);
	}


	
	/**
	 * 根据用户名与密码获取匹配的用户数据
	 */
	@Override
	public User getByNameAndPwd(String username, String password) throws Exception {
		String sql = "select * from user where username=? and password=?";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		User user = queryRunner.query(sql, new BeanHandler<>(User.class),username,password);
		return user;
	}

}
