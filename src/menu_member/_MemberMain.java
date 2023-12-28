package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("==========[ 회원 %s 님 ]==========\n", cont.getLoginId());
		System.out.println("[1] 상품구매");
		System.out.println("[2] 구매내역");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[6] 로그 아웃");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 6);
		if (sel == 1) { // 상품구매
			cont.setNext("MemberShopping");
		} else if (sel == 2) { // 구매내역
			cont.setNext("MemberCart");
		} else if (sel == 3) { // 게시판
			cont.setNext("MemberBoard");
		} else if (sel == 4) { // 나의 정보
			cont.setNext("MemberInfo");
		} else if (sel == 5) { // 회원 탈퇴
		} else if (sel == 6) { // 로그아웃
		} else if (sel == 0) { // 프로그램 종료
		}
		return false;
	}
}
