package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	ArrayList<Member> memberList;

	private MemberDAO() {
		memberList = new ArrayList<Member>();
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	public Member getMemberById(String id) {
		for (Member m : memberList) {
			if (m.getId().equals(id))
				return m;
		}
		return null;
	}

	public boolean insertMember(String id, String pw, String name) {
		Member temp = new Member();
		temp.setMemberNum(Member.getNum());
		temp.setId(id);
		temp.setPw(pw);
		temp.setMemberName(name);
		memberList.add(temp);
		Member.increaseNum();
		return true;
	}

	public Object isValidMember(String id, String pw) {
		for (Member m : memberList) {
			if (m.getId().equals(id) && m.getPw().equals(pw))
				return m;
		}
		return null;
	}

	public void putDataIn(String data) {
		String[] datas = data.split("\n");
		for (String str : datas) {
			String[] splitted = str.split("/");
			Member temp = new Member();
			temp.setMemberNum(Integer.parseInt(splitted[0]));
			temp.setId(splitted[1]);
			temp.setPw(splitted[2]);
			temp.setMemberName(splitted[3]);
			memberList.add(temp);
			Member.increaseNum();
		}
	}

	public void printAllMembers() {
		System.out.println("===== 전체 회원 목록 =====");
		for (Member m : memberList) {
			System.out.printf("[%-6d] [%10s] [%10s] [%10s]\n", m.getMemberNum(), m.getId(), m.getPw(),
					m.getMemberName());
		}
	}
}
