package dto;

public class Item implements Comparable<Item> {
	private static int num;
	private int itemNum, price;
	private String categoryName, itemName;

	public static void increaseNum() {
		num++;
	}

	/**
	 * @return the num
	 */
	public static int getNum() {
		return num;
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
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public int compareTo(Item o) {
		if (this.categoryName.compareTo(o.categoryName) < 0) {
			return 1;
		} else if (this.categoryName.compareTo(o.categoryName) == 0) {
			if (this.itemName.compareTo(o.itemName) < 0) {
				return -1;
			} else if (this.itemName.compareTo(o.itemName) == 0) {
				return 0;
			} else if (this.itemName.compareTo(o.itemName) > 0) {
				return 1;
			}
		} else if (this.categoryName.compareTo(o.categoryName) > 0) {
			return -1;
		}
		return 123123;
	}
}
