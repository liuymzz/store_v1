package service;

import java.util.List;

import entities.PageBean;
import entities.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean findByPage(String cid, int currPage, int pageSize) throws Exception;

	PageBean<Product> findAllByPage(int currPage, int pageSize) throws Exception;

	void add(Product product) throws Exception;

	void del(String pid,String realPath) throws Exception;

}
