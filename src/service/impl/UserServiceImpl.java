package service.impl;

import javax.servlet.http.HttpServletRequest;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;
import service.UserService;
import utils.MailUtils;

public class UserServiceImpl implements UserService {

	/**
	 * 注册账号
	 */
	@Override
	public void register(User user,HttpServletRequest request) throws Exception {

		UserDao userDao = new UserDaoImpl();
		userDao.register(user);

		// 发送邮件
		String email = user.getEmail();
		String msg = "这是来自刘亚明商城项目的账号激活邮件,<a href=\""+request.getServerName()+"/store_v1/user?method=active&code=" + user.getCode()
				+ "\">点这里激活</a>";
		/*String msg = "<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
			"<meta charset='UTF-8'>"+
		"</head>"+
		"<body>"+
		"这是来自刘亚明商城项目的账号激活邮件,<a href='"+request.getServerName()+"/store_v1/user?method=active&code=" + user.getCode()
		+ "'>点这里激活</a>"+
		"</body>"+
	"</html>";*/

		System.out.println(msg);
		MailUtils.sendMail(email, msg);
	}

	/**
	 * 激活账号
	 */
	@Override
	public User active(String code) throws Exception {
		// 先根据code查询user
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByCode(code);

		// 如果查询到了用户不为空则执行激活操作,否则返回null
		if (user != null) {
			userDao.active(user);
		}
		return user;
	}

	
	/**
	 * 登陆逻辑,获取user对象并返回
	 */
	@Override
	public User login(String username, String password) throws Exception {
		UserDao userDao = new UserDaoImpl();
		return userDao.getByNameAndPwd(username,password);
	}

}
