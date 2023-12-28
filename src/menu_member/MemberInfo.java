package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MemberInfo implements MenuCommand {
	private MemberDAO memberDAO;
	private MallController cont;
	Member me;

	@Override
	public void init() {
		memberDAO = MemberDAO.getInstance();
		cont = MallController.getInstance();
		me = memberDAO.getMemberById(cont.getLoginId());
		System.out.println("==========[ 내 정보 ]==========");
		System.out.println("[1] 비밀번호 변경");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
		System.out.println("====================");
		System.out.printf("[%-6d] [%10s] [%10s] [%10s]\n", me.getMemberNum(), me.getId(), me.getPw(),
				me.getMemberName());
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if (sel == 1) { // 비밀번호 변경
			memberDAO.modifyMyPw(me);
		} else if (sel == 2) { // 뒤로가기
			cont.setNext("MemberMain");
		} else if (sel == 0) { // 프로그램 종료
			cont.setNext(null);
		}
		return false;
	}
}
