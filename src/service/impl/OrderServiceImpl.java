package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import entities.Order;
import entities.OrderItem;
import entities.PageBean;
import entities.User;
import service.OrderService;
import utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	/**
	 * 添加订单
	 */
	@Override
	public void add(Order order) throws Exception {

		try {
			//开启事务
			DataSourceUtils.startTransaction();

			OrderDao orderDao = new OrderDaoImpl();

			orderDao.addOrder(order);

			for (OrderItem orderItem : order.getItems()) {
				orderDao.addOrdetItem(orderItem);
			}
			
			//关闭事物
			DataSourceUtils.commitAndClose();

		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 用户订单分页查询
	 */
	@Override
	public PageBean<Order> findByPage(int currPage, int pageSize, User user) throws Exception {
		List<Order> list = new ArrayList<>();
		int totalCount = 0;
		
		OrderDao orderDao = new OrderDaoImpl();
		
		totalCount = orderDao.getOrderCountByUser(user);
		list = orderDao.findByPage(currPage,pageSize,user);
	
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	
	/**
	 * 管理员所有订单查询
	 */
	@Override
	public PageBean<Order> findAllByPage(int currPage, int pageSize) throws Exception {
		List<Order> list = new ArrayList<>();
		int totalCount = 0;
		
		OrderDao orderDao = new OrderDaoImpl();
		totalCount = orderDao.getAllOrderCount();
		list = orderDao.findAllByPage(currPage,pageSize);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	
	
	/**
	 * 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		
		return orderDao.getById(oid);
		
	}

}
