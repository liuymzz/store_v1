package dao;

import java.util.List;

import entities.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void del(String cid) throws Exception;

	void update(String cid, String cname) throws Exception;

	void add(Category category) throws Exception;

}
