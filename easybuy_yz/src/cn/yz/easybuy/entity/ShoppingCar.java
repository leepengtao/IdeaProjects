package cn.yz.easybuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ﳵ��
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
	//��ӹ��ﳵ
	public void addItem(Product product,int num) {
		for (int i = 0; i < items.size(); i++) {
			//�жϹ��ﳵ���Ƿ����д���Ʒ����������ۼ�����	
			int a=items.get(i).getProduct().getId();
			int b=product.getId();
			if(a==b) {
				items.get(i).setNum(items.get(i).getNum()+num);
				return;
			}					
		}		
		items.add(new ShoppingCarItem(product, num));
	}
	//ɾ��,�жϹ��ﳵ���Ƿ����д���Ʒ���������ɾ��
	public void delItem(int proId) {
		int index=-1;
		for (int i = 0; i < items.size(); i++) {						
			if(items.get(i).getProduct().getId()==proId) {
				index=i;
			}
		}
		items.remove(index);
	}
	//�޸ģ�
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
	//�����ܼ۸�--�ṩҳ����${totalCost}
	public double getTotalCost() {
		double sum = 0;
		for (ShoppingCarItem item : items) {
			sum = sum + item.getCost();
		}
		return sum;
	}
}
