package entities;

/**
 * 分类实体类
 * 
 * @author lym14
 *
 */
public class Category {

	/**
	 * `cid` varchar(32) NOT NULL, 
	 * `cname` varchar(20) DEFAULT NULL
	 */
	
	private String cid;
	private String cname;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	

}
