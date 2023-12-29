package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController cont;
	private MemberDAO memberDAO;

	@Override
	public void init() {
		cont = MallController.getInstance();
		memberDAO = MemberDAO.getInstance();
		System.out.println("==========[ 관리자 회원목록 ]==========");
		System.out.println("[1] 회원목록");
		System.out.println("[2] 회원삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 3);
		if (sel == 1) { // 회원 목록
			memberDAO.printAllMembers();
		} else if (sel == 2) { // 회원삭제
			memberDAO.deleteAMember();
		} else if (sel == 3) { // 뒤로가기
			cont.setNext("AdminMain");
		} else if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		}
		return false;
	}
}
