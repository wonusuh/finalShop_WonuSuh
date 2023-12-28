package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	private CartDAO cartDAO;
	private MallController cont;

	@Override
	public void init() {
		cartDAO = CartDAO.getInstance();
		cont = MallController.getInstance();
		System.out.println("==========[ 구매내역 ]==========");
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로기기");
		System.out.println("[0] 프로그램 종료");
		System.out.println("====================");
		cartDAO.showMyCart(cont.getLoginId());
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 1) { // 쇼핑하기
			cont.setNext("MemberShopping");
		} else if (sel == 2) { // 뒤로가기
			cont.setNext("MemberMain");
		} else if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		}
		return false;
	}
}
