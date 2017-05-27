package dao;

import java.util.List;

import entities.Order;
import entities.OrderItem;
import entities.User;

public interface OrderDao {

	void addOrder(Order order)throws Exception;

	void addOrdetItem(OrderItem orderItem) throws Exception;

	int getOrderCountByUser(User user) throws Exception;

	List<Order> findByPage(int currPage, int pageSize, User user) throws Exception;

	int getAllOrderCount() throws Exception;

	List<Order> findAllByPage(int currPage, int pageSize) throws Exception;

	Order getById(String oid) throws Exception;

}
