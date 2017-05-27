package service;

import entities.User;

public interface UserService {

	void register(User user) throws Exception;

	User active(String code) throws Exception;

	User login(String username, String password) throws Exception;

}
