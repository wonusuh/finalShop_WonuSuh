package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import dto.Cart;
import dto.Item;

public class CartDAO {
	ArrayList<Cart> cartList;

	private CartDAO() {
		cartList = new ArrayList<Cart>();
	}

	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}

	public void putDataIn(String data) {
		String[] datas = data.split("\n");
		for (String str : datas) {
			String[] splitted = str.split("/");
			Cart temp = new Cart();
			temp.setCartNum(Cart.getNum());
			temp.setId(splitted[1]);
			temp.setItemNum(Integer.parseInt(splitted[2]));
			temp.setItemCnt(Integer.parseInt(splitted[3]));
			cartList.add(temp);
			Cart.increseNum();
		}
	}

	public void putOrderInCart(Cart order) {
		cartList.add(order);
	}
}
