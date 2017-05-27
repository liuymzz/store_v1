package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类查询,显示一页的实体
 * 
 * @author lym14
 *
 */
public class PageBean<T> {

	private List<T> list = new ArrayList<>(); // 所有的产品实体
	private Integer currPage; // 当前页
	private Integer pageSize; // 每页显示数据的数量
	private Integer totalPage; // 所有的页数
	private Integer totalCount; // 所有的条数

	public PageBean() {
	}

	public PageBean(List<T> list, Integer currPage, Integer pageSize, Integer totalCount) {
		super();
		this.list = list;
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return (int) Math.ceil(totalCount * 1.0 / pageSize);
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "PageBean [list=" + list + ", currPage=" + currPage + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", totalCount=" + totalCount + "]";
	}
	
	

}
