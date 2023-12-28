package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dto.Cart;
import dto.Item;
import dto.Member;

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

	public void showMyCart(String id) { // 로그인 중인 회원의 장바구니를 출력합니다.
		ItemDAO itemDAO = ItemDAO.getInstance();
		List<Cart> myList = new ArrayList<Cart>();
		for (Cart c : cartList) {
			if (c.getId().equals(id))
				myList.add(c);
		}
		Map<Integer, Integer> myCart = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < myList.size(); i += 1) {
			if (!myCart.containsKey(myList.get(i).getItemNum())) {
				myCart.put(myList.get(i).getItemNum(), myList.get(i).getItemCnt());
			} else {
				myCart.replace(myList.get(i).getItemNum(),
						myCart.get(myList.get(i).getItemNum()) + myList.get(i).getItemCnt());
			}
		}
		Iterator<Integer> keys = myCart.keySet().iterator();
		int i = 0;
		int numberOfItems = 0;
		int total = 0;
		while (keys.hasNext()) {
			int key = keys.next();
			Item o = itemDAO.getTheObjectByNum(key);
			System.out.printf("[%3d]%10s(%10d원)%10d개 총 %d원\n", ++i, o.getItemName(), o.getPrice(), myCart.get(key),
					myCart.get(key) * o.getPrice());
			numberOfItems += myCart.get(key);
			total += myCart.get(key) * o.getPrice();
		}
		System.out.printf("총 %d 개 ( %d 원 )\n", numberOfItems, total);
		System.out.println("====================");
	}

	public void clearCartByObject(Member o) { // 장바구니에서 해당하는 회원의 주문을 모두 지웁니다.
		for (int i = 0; i < cartList.size(); i += 1) {
			if (cartList.get(i).getId().equals(o.getId())) {
				cartList.remove(i);
				i -= 1;
			}
			System.out.println(cartList);
		}
	}
}
