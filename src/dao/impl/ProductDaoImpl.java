package dao.impl;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import dao.ProductDao;
import entities.Category;
import entities.Product;
import utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	/**
	 * 最热商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=1 order by pdate desc limit 9";
		return queryRunner.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit 9";
		return queryRunner.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 根据id查找product
	 */
	@Override
	public Product getById(String pid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

		// 查product
		String sql = "select * from product where pid=? limit 1";
		Product product = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);

		// 先查出产品所属的类别id
		sql = "select cid from product where pid=? limit 1";
		String cid = (String) queryRunner.query(sql, new ScalarHandler("cid"), pid);
		// 再根据类别id查找类别对象并将之添加到产品对象中
		sql = "select * from category where cid=? limit 1";
		Category category = queryRunner.query(sql, new BeanHandler<>(Category.class), cid);
		product.setCategory(category);

		return product;
	}

	/**
	 * 根据分类分页查找
	 */
	@Override
	public List<Product> findByPage(String cid, int currPage, int pageSize) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
		return queryRunner.query(sql, new BeanListHandler<>(Product.class), cid, (currPage - 1) * pageSize, pageSize);
	}

	/**
	 * 查找数量
	 */
	@Override
	public Integer getTotal(String cid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		return ((Long) queryRunner.query(sql, new ScalarHandler(1), cid)).intValue();
	}

	/**
	 * 分页查找所有的商品
	 */
	@Override
	public List<Product> findAllByPage(int currPage, int pageSize) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product LIMIT ?,?";
		return queryRunner.query(sql, new BeanListHandler<>(Product.class), (currPage - 1) * pageSize, pageSize);
	}

	/**
	 * 获取所有商品的数量
	 */
	@Override
	public int getAllTotal() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product";

		return ((Long) queryRunner.query(sql, new ScalarHandler(1))).intValue();
	}

	/**
	 * 添加商品
	 */
	@Override
	public void add(Product product) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "INSERT INTO `store_v1`.`product` VALUES  (?,?,?,?,?,?,?,?,?,?);";
		//'pid','pname','market_price','shop_price','pimage','pdate','is_hot','pdesc','pflag','cid'
		queryRunner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
				product.getPdesc(),product.getPflag(),product.getCid());
		
	}

	
	/**
	 * 删除商品
	 */
	@Override
	public void del(String pid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "DELETE FROM  `store_v1`.`product` WHERE `pid` = ?;";
		
		queryRunner.update(sql,pid);
		
	}

}
