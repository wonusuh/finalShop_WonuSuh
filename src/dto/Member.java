package dto;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id, pw, memberName;

	public static void increaseNum() {
		num++;
	}

	public static int getNum() {
		return num;
	}

	/**
	 * @return the memberNum
	 */
	public int getMemberNum() {
		return memberNum;
	}

	/**
	 * @param memberNum the memberNum to set
	 */
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * @param pw the pw to set
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
