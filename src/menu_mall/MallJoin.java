package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		System.out.println("=====[ 회원가입 ]=====");

		System.out.println("[ 아이디는 4 ~ 10글자의 알파벳과 숫자만 가능합니다. ]");
		String id = Util.getValue("아이디 ");
		if (dao.getMemberById(id) != null) {
			System.out.println("[ 이미 사용하는 아이디 ]");
			return false;
		}
		if (!Util.isAppropriateId(id)) {
			System.out.println("아이디 조건을 만족하지 않습니다.");
			return false;
		}

		System.out.println("[ 비밀번호는 공백을 포함할 수 없으며 4 ~ 10글자 입니다. ]");
		String pw = Util.getValue("비밀번호");
		if (!Util.isAppropriatePw(pw)) {
			System.out.println("비밀번호 조건을 만족하지 않습니다.");
			return false;
		}

		System.out.println("[ 이름은 공백이나 특수문자를 포함할 수 없으며 2 ~ 10글자 입니다. ]");
		String name = Util.getValue("이름");
		if (!Util.isAppropriateName(name)) {
			System.out.println("이름 조건을 만족하지 않습니다.");
			return false;
		}

		if (dao.insertMember(id, pw, name)) {
			System.out.println("[ 회원 추가 완료 ]");
		} else {
			System.out.println("[ 회원 추가 실패] ");
		}
		cont.setNext("MallMain");
		return false;
	}
}
