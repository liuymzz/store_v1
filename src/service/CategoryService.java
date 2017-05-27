package service;

import java.util.List;

import entities.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

	void del(String cid) throws Exception;

	void update(String cid, String cname) throws Exception;

	void add(Category category) throws Exception;

}
