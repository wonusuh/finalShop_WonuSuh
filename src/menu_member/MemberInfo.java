package menu_member;

import _mall.MenuCommand;
import util.Util;

public class MemberInfo implements MenuCommand {
	@Override
	public void init() {
		System.out.println("==========[ 내 정보 ]==========");
		System.out.println("[1] 비밀번호 변경");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if (sel == 1) { // 비밀번호 변경
		} else if (sel == 2) { // 뒤로가기
		} else if (sel == 0) { // 프로그램 종료
		}
		return false;
	}
}
