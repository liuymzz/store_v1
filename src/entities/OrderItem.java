package entities;

public class OrderItem {
/**
 * `itemid` VARCHAR(32) NOT NULL,
		  `count` INT(11) DEFAULT NULL,
		  `subtotal` DOUBLE DEFAULT NULL,
		  `pid` VARCHAR(32) DEFAULT NULL,
		  `oid` VARCHAR(32) DEFAULT NULL,
 */

	private String itemid;
	private Integer count;
	private Double subtotal;
	
	private Product product;
	
	private Order order;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}
