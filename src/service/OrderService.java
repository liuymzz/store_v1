package service;

import entities.Order;
import entities.PageBean;
import entities.User;

public interface OrderService {

	void add(Order order) throws Exception;

	PageBean<Order> findByPage(int currPage, int pageSize, User user) throws Exception;

	PageBean<Order> findAllByPage(int currPage, int pageSize) throws Exception;

	Order getById(String oid) throws Exception;

}
