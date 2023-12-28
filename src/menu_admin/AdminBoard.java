package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;
	private BoardDAO boardDAO;

	@Override
	public void init() {
		cont = MallController.getInstance();
		boardDAO = BoardDAO.getInstance();
		System.out.println("==========[ 관리자 게시판 ]==========");
		System.out.println("[1] 게시글 목록");
		System.out.println("[2] 게시글 삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 3);
		if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		} else if (sel == 1) { // 게시글 목록
			boardDAO.showAdminBoard();
		} else if (sel == 2) { // 게시글 삭제
		} else if (sel == 3) { // 뒤로가기
			cont.setNext("AdminMain");
		}
		return false;
	}
}
