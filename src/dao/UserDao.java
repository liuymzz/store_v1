package dao;

import entities.User;

public interface UserDao {

	void register(User user) throws Exception;

	User getUserByCode(String code) throws Exception;

	void active(User user) throws Exception;

	User getByNameAndPwd(String username, String password) throws Exception;

}
