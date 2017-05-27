package service;

import entities.Admin;

public interface AdminService {

	Admin login(String username, String password) throws Exception;

}
