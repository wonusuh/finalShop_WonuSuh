package dao;

import java.util.ArrayList;
import dto.Board;
import dto.Member;
import util.Util;

public class BoardDAO {
	ArrayList<Board> boardList;

	private BoardDAO() {
		boardList = new ArrayList<Board>();
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public void putDataIn(String data) { // String 으로 된 data를 배열에 저장합니다.
		String[] datas = data.split("\n");
		for (String str : datas) {
			String[] splitted = str.split("/");
			Board temp = new Board();
			Board.increaseNum();
			temp.setBoradNum(Board.getNum());
			temp.setTitle(splitted[1]);
			temp.setContents(splitted[2]);
			temp.setId(splitted[3]);
			temp.setDate(splitted[4]);
			temp.setHits(Integer.parseInt(splitted[5]));
			boardList.add(temp);
		}
	}

	public void showBoard() {
		System.out.println("=====[ 전체 게시글 목록 ]=====");
		System.out.printf("총 게시글 %d 개\n", boardList.size());
		int i = 0;
		for (Board b : boardList) {
			System.out.printf("(%3d) [ 제목 : %-10s 작성자 : %-10s 날짜 : %s 조회수 : %-3d ]\n", ++i, b.getTitle(), b.getId(),
					b.getDate(), b.getHits());
		}
	}
}
