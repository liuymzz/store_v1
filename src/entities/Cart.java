package entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车实体类
 * 
 * @author lym14
 *
 */
public class Cart implements Serializable {

	// 存放所有的购物车项,key是商品id,value是购物车项
	private Map<String, CartItem> items = new LinkedHashMap<>();

	// 存放总金额
	private Double total = 0.0;

	/**
	 * 将购物车项添加至购物车
	 * 
	 * @param item
	 */
	public void add2Cart(CartItem item) {
		// 首先需要判断购物车项是否已经存在于购物车中
		String pid = item.getProduct().getPid();
		if (items.containsKey(pid)) {
			// 如果已经存在则按照现有的商品数量加上新的数量
			CartItem oldItem = items.get(pid);
			oldItem.setCount(oldItem.getCount() + item.getCount());
		} else {
			// 否则直接添加
			items.put(pid, item);
		}
		
		//否则直接修改金额
		total += item.getTotal();
	}

	/**
	 * 从购物车中删除某一项
	 * @param pid
	 */
	public void removeFromCart(String pid) {
		// 先从集合中删除元素
		CartItem item = items.remove(pid);

		// 再修改金额
		total = total - item.getTotal();
	};

	
	/**
	 * 清空购物车
	 */
	public void clear() {
		// 清空集合
		items.clear();

		// 金额清零
		total = 0.0;
	};

	public Map<String, CartItem> getItems() {
		return items;
	}

	public void setItems(Map<String, CartItem> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
