package cn.bdqn.easybuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 购物车
 * @author WindLin
 *
 */
public class ShoppingCart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3371649930928479579L;
	public List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();	
	//获取购物车中所有商品
	public List<ShoppingCartItem> getItems() {
		return items;
	}	
	//添加一项
	public void addItem(Product product, int quantity) {
		for(int i=0;i<items.size();i++){
			//判断购物车中是否已有此商品，如果有则累计数量
			if((items.get(i).getProduct().getId()==product.getId())){
				items.get(i).setQuantity(items.get(i).getQuantity()+quantity);
				return;
			}
		}
		items.add(new ShoppingCartItem(product, quantity));
	}

	//移除一项
	public void removeItem(int epId) {
		int index = -1; 
		for(int i=0;i<items.size();i++){
			//判断购物车中是否已有此商品，如果有则累计数量
			if((items.get(i).getProduct().getId()==epId)){
				index = i; //记下下标
				break;
			}
		}
		items.remove(index);
	}

	//修改数量
	public void modifyQuantity(int epId, int num) {
		int index = -1; 
		for(int i=0;i<items.size();i++){
			//判断购物车中是否已有此商品，如果有则累计数量
			if((items.get(i).getProduct().getId()==epId)){
				index = i; //记下下标
				break;
			}
		}
		items.get(index).setQuantity(num);
	}

	//计算总价格--提供页面用${totalCost}
	public double getTotalCost() {
		double sum = 0;
		for (ShoppingCartItem item : items) {
			sum = sum + item.getCost();
		}
		return sum;
	}

}
