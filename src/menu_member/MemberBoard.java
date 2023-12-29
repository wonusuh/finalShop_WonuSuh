package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class MemberBoard implements MenuCommand {
	private BoardDAO boardDAO;
	private MallController cont;

	@Override
	public void init() {
		boardDAO = BoardDAO.getInstance();
		cont = MallController.getInstance();
		System.out.println("==========[ 게시판 ]==========");
		System.out.println("[1] 게시글 보기");
		System.out.println("[2] 게시글 추가");
		System.out.println("[3] 내 게시글 (삭제)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 4);
		if (sel == 1) { // 게시글 보기
			boardDAO.showBoard();
		} else if (sel == 2) { // 게시글 추가
			boardDAO.addAPost();
		} else if (sel == 3) { // 내 게시글 (삭제)
			boardDAO.showMyPosts();
		} else if (sel == 4) { // 뒤로가기
			cont.setNext("MemberMain");
		} else if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		}
		return false;
	}
}
