package dao;

import entities.Admin;

public interface AdminDao {

	Admin login(String username, String password) throws Exception;

}
