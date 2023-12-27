package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.BoardDAO;
import dao.CartDAO;
import dao.FileDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import menu_admin.AdminBoard;
import menu_admin.AdminItem;
import menu_admin.AdminMember;
import menu_admin._AdminMain;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall._MallMain;
import menu_member.MemberBoard;
import menu_member.MemberCart;
import menu_member.MemberInfo;
import menu_member.MemberQuit;
import menu_member.MemberShopping;
import menu_member._MemberMain;
import util.Util;

public class MallController {
	BoardDAO boardDAO;
	CartDAO cartDAO;
	ItemDAO itemDAO;
	MemberDAO memberDAO;

	private MallController() {
		boardDAO = BoardDAO.getInstance();
		cartDAO = CartDAO.getInstance();
		itemDAO = ItemDAO.getInstance();
		memberDAO = MemberDAO.getInstance();
		FileDAO.load();
	}

	static private MallController instance = new MallController();

	static public MallController getInstance() {
		return instance;
	}

	private String loginId, next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new _MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMain", new _AdminMain());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberMain", new _MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());
		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					System.out.println("[ 프로그램 종료 ]");
					Util.closeScanner();
					return;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
