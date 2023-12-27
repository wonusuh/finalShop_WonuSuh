package menu_admin;

import java.util.Map;
import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController cont;
	private ItemDAO itemDAO;

	@Override
	public void init() {
		cont = MallController.getInstance();
		itemDAO = ItemDAO.getInstance();
		System.out.println("==========[ 관리자 쇼핑몰 관리 ]==========");
		System.out.println("카테고리 순으로 정렬 카테고리가 같으면 아이템 이름순으로 정렬");
		System.out.println("[1] 아이템 추가");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 총 매출 아이템 갯수 출력 (판매량 높은 순으로)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 4);
		if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		} else if (sel == 1) { // 아이템 추가
			itemDAO.showAllItems();
		} else if (sel == 2) { // 아이템 삭제
		} else if (sel == 3) { // 총 매출 아이템 갯수 출력 (판매량 높은 순으로)
		} else if (sel == 4) { // 뒤로가기
			cont.setNext("AdminMain");
		}
		return false;
	}
}
