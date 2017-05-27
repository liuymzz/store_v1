package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author lym14
 *
 */
public class Order {
	/**
	 * `oid` VARCHAR(32) NOT NULL, `ordertime` DATETIME DEFAULT NULL, `total`
	 * DOUBLE DEFAULT NULL, `state` INT(11) DEFAULT NULL, `address` VARCHAR(30)
	 * DEFAULT NULL, `name` VARCHAR(20) DEFAULT NULL, `telephone` VARCHAR(20)
	 * DEFAULT NULL, `uid` VARCHAR(32) DEFAULT NULL,
	 */

	private String oid;
	private Date ordertime;
	private Double total;
	private Integer state = 0; // 订单状态0:未支付 1:已支付
	private String address;
	private String name;
	private String telephone;

	private User user;

	private List<OrderItem> items = new ArrayList<>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
