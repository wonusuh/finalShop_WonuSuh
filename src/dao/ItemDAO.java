package dao;

import java.util.ArrayList;
import java.util.List;

import controller.MallController;
import dto.Cart;
import dto.Item;
import util.Util;

public class ItemDAO {
	private ArrayList<Item> itemList;
	private CartDAO cartDAO;

	private ItemDAO() { // 생성자 입니다.
		itemList = new ArrayList<Item>();
		cartDAO = CartDAO.getInstance();
	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() { // 생성자를 리턴합니다.
		return instance;
	}

	public void putDataIn(String data) { // String 타입의 데이터를 배열로 만듭니다.
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

	private void showAllItems() { // 아이템 목록을 출력합니다.
		System.out.println("==========[ 카테고리별 아이템 목록 ]==========");
		itemList.stream().sorted().forEach(item -> System.out.printf("[%-3d] [%5s] [%5s] [%10d]\n", item.getItemNum(),
				item.getCategoryName(), item.getItemName(), item.getPrice()));
	}

	public List<String> getCategoryList() { // 중복을 제거한 카테고리 리스트를 만들어 리턴합니다.
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

	public void shopping(String category) { // 해당하는 카테고리의 아이템을 쇼핑합니다.
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

	private int getItemNumByName(String itemName) { // 아이템의 이름을 입력받아 해당하는 객체의 인덱스를 리턴합니다.
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

	public void addAnItem() { // 아이템을 한 개 추가합니다.
		showAllItems();
		String itemName = Util.getValue("아이템의 이름을 입력하세요.");
		if (isTheCategoryExist(itemName) || getItemNumByName(itemName) != -1) {
			System.out.println("이미 존재하는 카테고리/아이템 이름입니다.");
			return;
		}
		String categoryName = Util.getValue("카테고리의 이름을 입력하세요.");
		int price = Util.getValue("가격을 입력하세요.", 100, 1000000);
		Item newItem = new Item();
		Item.increaseNum();
		newItem.setItemNum(Item.getNum());
		newItem.setCategoryName(categoryName);
		newItem.setItemName(itemName);
		newItem.setPrice(price);
		itemList.add(newItem);
		System.out.println("아이템을 추가했습니다.");
	}

	private boolean isTheCategoryExist(String itemName) { // 아이템 이름으로 검색해서 카테고리가 존재하는지 아닌지를 리턴합니다.
		for (Item item : itemList) {
			if (item.getCategoryName().equals(itemName))
				return true;
		}
		return false;
	}

	public void deleteAnItem() { // 아이템의 번호를 입력받아 삭제합니다.
		showAllItems();
		System.out.println("[ 아이템을 삭제하면 구매내역도 사라집니다. ]");
		int itemNum = Util.getValue("삭제할 아이템의 번호를 입력하세요.", 1, Item.getNum());
		for (Item item : itemList) {
			if (item.getItemNum() == itemNum) {
				itemList.remove(item);
				cartDAO.clearCartByItemNum(itemNum);
				System.out.println("아이템을 삭제했습니다.");
				return;
			}
		}
		System.out.println("아이템삭제에 실패했습니다.");
	}

	protected String getItemDataAsString() { // 배열을 String으로 리턴합니다.
		String data = "";
		for (int i = 0; i < itemList.size(); i += 1) {
			data += itemList.get(i).getItemNum() + "/" + itemList.get(i).getCategoryName() + "/"
					+ itemList.get(i).getItemName() + "/" + itemList.get(i).getPrice();
			if (i != itemList.size() - 1)
				data += "\n";
		}
		return data;
	}
}
