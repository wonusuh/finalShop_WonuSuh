package menu_member;
import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;
public class MemberCart implements MenuCommand {
	@Override
	public void init() {
		System.out.println("==========[ 구매내역 ]==========");
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로기기");
		System.out.println("[0] 종료");
	}

	@Override
	public boolean update() {
		return false;
	}
}
