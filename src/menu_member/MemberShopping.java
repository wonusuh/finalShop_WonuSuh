package menu_member;

import java.util.List;

import _mall.MenuCommand;
import controller.MallController;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private ItemDAO itemDAO;
	private List<String> categories;

	@Override
	public void init() {
		itemDAO = ItemDAO.getInstance();
		categories = itemDAO.getCategoryList();
		System.out.println("==========[ 쇼핑몰에 오신것을 환영합니다. ]==========");
		printCategories();
		System.out.println("[0] 뒤로가기");
	}

	@Override
	public boolean update() {
		MallController cont = MallController.getInstance();
		int sel = Util.getValue("원하는 카테고리 입력", 0, categories.size()) - 1;
		if (sel == -1) {
			cont.setNext("MemberMain");
			return false;
		}
		itemDAO.shopping(categories.get(sel));
		return false;
	}

	private void printCategories() {
		for (int i = 0; i < categories.size(); i += 1) {
			System.out.printf("[%d] %s\n", i + 1, categories.get(i));
		}
	}
}
