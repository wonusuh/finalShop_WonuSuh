package dao;

import java.util.ArrayList;
import java.util.List;

import controller.MallController;
import dto.Cart;
import dto.Item;
import util.Util;

public class ItemDAO {
	ArrayList<Item> itemList;
	CartDAO cartDAO;

	private ItemDAO() {
		itemList = new ArrayList<Item>();
		cartDAO = CartDAO.getInstance();
	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		return instance;
	}

	public void putDataIn(String data) {
		String[] datas = data.split("\n");
		for (String str : datas) {
			String[] splitted = str.split("/");
			Item temp = new Item();
			Item.increaseNum();
			temp.setItemNum(Item.getNum());
			temp.setCategoryName(splitted[1]);
			temp.setItemName(splitted[2]);
			temp.setPrice(Integer.parseInt(splitted[3]));
			itemList.add(temp);
		}
	}

	public void showAllItems() {
		System.out.println("==========[ 카테고리별 아이템 목록 ]==========");
		itemList.stream().sorted().forEach(item -> System.out.printf("[%-3d] [%5s] [%5s] [%10d]\n", item.getItemNum(),
				item.getCategoryName(), item.getItemName(), item.getPrice()));
	}

	public List<String> getCategoryList() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < itemList.size(); i += 1) {
			boolean isDupl = false;
			for (int j = 0; j < list.size(); j += 1) {
				if (list.get(j).equals(itemList.get(i).getCategoryName()))
					isDupl = true;
			}
			if (isDupl)
				continue;
			list.add(itemList.get(i).getCategoryName());
		}
		return list;
	}

	public void shopping(String category) {
		MallController cont = MallController.getInstance();
		while (true) {
			System.out.printf("[ %s 의 아이템 목록 ]\n", category);
			int numbering = 0;
			for (Item item : itemList) {
				if (item.getCategoryName().equals(category))
					System.out.printf("[%d] %s %d\n", ++numbering, item.getItemName(), item.getPrice());
			}
			String itemName = Util.getValue("구매할 아이템의 이름을 입력하세요.");
			int itemNum = getItemNumByName(itemName);
			if (itemNum == -1) {
				System.out.println("존재하지 않는 아이템입니다.");
				continue;
			}
			int howMany = Util.getValue("구매할 수량을 입력하세요.", 1, 100);
			Cart order = new Cart();
			order.setCartNum(Cart.getNum());
			order.setId(cont.getLoginId());
			order.setItemNum(itemNum);
			order.setItemCnt(howMany);
			cartDAO.putOrderInCart(order);
			System.out.printf("[ %s %d개를 구매했습니다. ]\n", itemName, howMany);
			break;
		}
		cont.setNext("MemberMain");
	}

	private int getItemNumByName(String itemName) {
		for (Item item : itemList) {
			if (item.getItemName().equals(itemName))
				return item.getItemNum();
		}
		return -1;
	}

	public Item getTheObjectByNum(int key) { // 아이템 고유번호로 검색해서 해당하는 아이템객체를 리턴합니다.
		for (Item o : itemList) {
			if (o.getItemNum() == key)
				return o;
		}
		return null;
	}
}
