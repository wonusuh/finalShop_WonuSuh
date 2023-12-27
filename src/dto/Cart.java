package dto;

public class Cart {
	private static int num;
	private int cartNum, itemNum, itemCnt;
	private String id;

	public static void increseNum() {
		num++;
	}

	/**
	 * @return the num
	 */
	public static int getNum() {
		return num;
	}

	/**
	 * @return the cartNum
	 */
	public int getCartNum() {
		return cartNum;
	}

	/**
	 * @param cartNum the cartNum to set
	 */
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	/**
	 * @return the itemNum
	 */
	public int getItemNum() {
		return itemNum;
	}

	/**
	 * @param itemNum the itemNum to set
	 */
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * @return the itemCnt
	 */
	public int getItemCnt() {
		return itemCnt;
	}

	/**
	 * @param itemCnt the itemCnt to set
	 */
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
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
}
