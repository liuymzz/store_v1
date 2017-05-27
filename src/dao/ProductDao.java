package dao;

import java.util.List;

import javax.swing.tree.ExpandVetoException;

import entities.Product;

public interface ProductDao {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(String cid, int currPage, int pageSize) throws Exception;

	Integer getTotal(String cid) throws Exception;

	List<Product> findAllByPage(int currPage, int pageSize) throws Exception;

	int getAllTotal() throws Exception;

	void add(Product product) throws Exception;

	void del(String pid) throws Exception;

}
