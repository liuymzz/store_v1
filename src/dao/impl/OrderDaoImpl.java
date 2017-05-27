package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import dao.OrderDao;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.User;
import utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加订单
	 */
	@Override
	public void addOrder(Order order) throws Exception {
		QueryRunner queryRunner = new QueryRunner();
		/**
		 * 'oid', 'ordertime', 'total', 'state', 'address', 'name', 'telephone',
		 * 'uid'
		 */
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		queryRunner.update(DataSourceUtils.getConnection(), sql, order.getOid(), order.getOrdertime(), order.getTotal(),
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	/**
	 * 添加订单中的项
	 */
	@Override
	public void addOrdetItem(OrderItem orderItem) throws Exception {
		QueryRunner queryRunner = new QueryRunner();
		/**
		 * 'itemid', 'count', 'subtotal', 'pid', 'oid'
		 */
		String sql = "insert into orderitem values(?,?,?,?,?)";
		queryRunner.update(DataSourceUtils.getConnection(), sql, orderItem.getItemid(), orderItem.getCount(),
				orderItem.getSubtotal(), orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
	}

	/**
	 * 查询用户的所有订单数量
	 */
	@Override
	public int getOrderCountByUser(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid = ?";
		return ((Long) (queryRunner.query(sql, new ScalarHandler(1), user.getUid()))).intValue();
	}

	/**
	 * 根据用户分页查询订单
	 */
	@Override
	public List<Order> findByPage(int currPage, int pageSize, User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

		// 先查询出用户所有的订单
		String sql = "SELECT * FROM orders WHERE uid=? ORDER BY ordertime limit ?,?";
		List<Order> orders = queryRunner.query(sql, new BeanListHandler<>(Order.class), user.getUid(),
				(currPage - 1) * pageSize, pageSize);

		// 再补充用户订单中的其他对象信息
		sql = "SELECT * FROM orderitem oi,product p WHERE oi.`oid`=? AND oi.`pid`=p.`pid`";
		for (Order order : orders) {
			List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : list) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				orderItem.setOrder(order);

				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);

				orderItem.setProduct(product);
				order.getItems().add(orderItem);
			}

		}

		return orders;
	}

	/**
	 * 管理员查询所有订单数量
	 * 
	 * @throws Exception
	 */
	@Override
	public int getAllOrderCount() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders";

		return ((Long) queryRunner.query(sql, new ScalarHandler(1))).intValue();
	}

	/**
	 * 管理员分页查询订单
	 */

	@Override
	public List<Order> findAllByPage(int currPage, int pageSize) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT  * FROM  `store_v1`.`orders` ORDER BY ordertime DESC LIMIT ?, ?;";

		// 先查询所有订单
		List<Order> list = new ArrayList<>();
		list = queryRunner.query(sql, new BeanListHandler<>(Order.class), (currPage - 1) * pageSize, pageSize);

		// 再分别补充所有订单中的详细信息
		sql = "SELECT * FROM orderitem oi,product p WHERE oi.`oid`=? AND oi.`pid`=p.`pid`";
		for (Order order : list) {
			// 先查询出每个订单所有的订单项以及订单项中的产品信息并封装到list集合中
			List<Map<String, Object>> maps = queryRunner.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : maps) {

				// 封装至实体类中
				Product product = new Product();
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(product, map);
				BeanUtils.populate(orderItem, map);

				// 再将每个订单项添加到对应的订单中
				orderItem.setProduct(product);
				order.getItems().add(orderItem);

			}

		}

		return list;
	}

	/**
	 * 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

		// 先查询出订单信息
		String sql = "SELECT * FROM `store_v1`.`orders` WHERE oid = ?";
		Order order = queryRunner.query(sql, new BeanHandler<>(Order.class), oid);

		// 再查出订单关联信息
		sql = "SELECT * FROM orderitem oi,product p WHERE oi.`oid`= ? AND oi.`pid`=p.`pid`";
		List<Map<String, Object>> maps = queryRunner.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : maps) {
			//将查询出的数据封装至实体对象
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			orderItem.setProduct(product);
			order.getItems().add(orderItem);
		}

		return order;
	}

}
