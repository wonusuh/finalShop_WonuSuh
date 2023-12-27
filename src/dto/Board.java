package dto;

public class Board {
	private static int num;
	private int boradNum, hits;
	private String title, contents, id, date;

	/**
	 * @return the num
	 */
	public static int getNum() {
		return num;
	}

	public static void increaseNum() {
		Board.num++;
	}

	/**
	 * @return the boradNum
	 */
	public int getBoradNum() {
		return boradNum;
	}

	/**
	 * @param boradNum the boradNum to set
	 */
	public void setBoradNum(int boradNum) {
		this.boradNum = boradNum;
	}

	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
