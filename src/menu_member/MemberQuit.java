package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MemberQuit implements MenuCommand {
	private MallController cont;
	private MemberDAO memberDAO;
	private CartDAO cartDAO;
	private Member me;

	@Override
	public void init() {
		cont = MallController.getInstance();
		memberDAO = MemberDAO.getInstance();
		cartDAO = CartDAO.getInstance();
		me = memberDAO.getMemberById(cont.getLoginId());
		System.out.println("==========[ 회원 탈퇴 ]==========");
	}

	@Override
	public boolean update() {
		String pw = Util.getValue("비밀번호를 입력하세요.");
		if (me.getPw().equals(pw)) {
			cartDAO.clearCartByObject(me);
			memberDAO.quit(me);
			cont.setLoginId(null);
			cont.setNext("MallMain");
			System.out.println("탈퇴에 성공했습니다.");
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
		return false;
	}
}
