package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import dao.CategoryDao;
import entities.Category;
import utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> list = queryRunner.query(sql, new BeanListHandler<>(Category.class));
		return list;
	}

	/**
	 * 删除分类
	 */
	@Override
	public void del(String cid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "DELETE FROM `store_v1`.`category` WHERE `cid` = ?; ";
		queryRunner.update(sql, cid);
		
	}

	/**
	 * 更改分类名称
	 */
	@Override
	public void update(String cid, String cname) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "UPDATE  `store_v1`.`category` SET  `cname` = ? WHERE `cid` = ?;";
		queryRunner.update(sql,cname,cid);
		
	}

	/**
	 * 添加分类
	 */
	@Override
	public void add(Category category) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "INSERT INTO `store_v1`.`category` VALUES  (?, ?);";
		queryRunner.update(sql, category.getCid(),category.getCname());
	}

}
