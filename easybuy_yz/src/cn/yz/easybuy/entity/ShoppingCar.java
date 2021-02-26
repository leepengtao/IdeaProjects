package cn.yz.easybuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车类
 * @author
 *
 */
public class ShoppingCar implements Serializable{
	public List<ShoppingCarItem> items=new ArrayList<ShoppingCarItem>();
	
	public List<ShoppingCarItem> getItems() {
		return items;
	}
	public int getSize() {
		return items.size();
	}
	//添加购物车
	public void addItem(Product product,int num) {
		for (int i = 0; i < items.size(); i++) {
			//判断购物车中是否已有此商品，如果有则累计数量	
			int a=items.get(i).getProduct().getId();
			int b=product.getId();
			if(a==b) {
				items.get(i).setNum(items.get(i).getNum()+num);
				return;
			}					
		}		
		items.add(new ShoppingCarItem(product, num));
	}
	//删除,判断购物车中是否已有此商品，如果有则删除
	public void delItem(int proId) {
		int index=-1;
		for (int i = 0; i < items.size(); i++) {						
			if(items.get(i).getProduct().getId()==proId) {
				index=i;
			}
		}
		items.remove(index);
	}
	//修改，
	public void modifyNum(int proId,int num) {
		int index=-1;
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getProduct().getId()==proId) {
				index=i;
				break;
			}
			
		}
		items.get(index).setNum(num);
	}
	//计算总价格--提供页面用${totalCost}
	public double getTotalCost() {
		double sum = 0;
		for (ShoppingCarItem item : items) {
			sum = sum + item.getCost();
		}
		return sum;
	}
}
