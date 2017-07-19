package service;

import javax.servlet.http.HttpServletRequest;

import entities.User;

public interface UserService {

	void register(User user,HttpServletRequest request) throws Exception;

	User active(String code) throws Exception;

	User login(String username, String password) throws Exception;

}
