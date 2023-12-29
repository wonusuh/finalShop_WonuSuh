package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.MallController;
import dto.Board;
import util.Util;

public class BoardDAO {
	private ArrayList<Board> boardList;

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

	public void showBoard() { // 게시판을 페이지를 나누어 보여줍니다.
		int size = 5, curPage = 1, lastPage = boardList.size() / size;
		if (boardList.size() % size != 0)
			lastPage += 1;
		while (true) {
			int firstPost = (curPage - 1) * size, lastPost = firstPost + size;
			if (lastPost > boardList.size())
				lastPost = boardList.size();
			System.out.printf("총 게시글 %d 개\n", boardList.size());
			System.out.printf("현재 페이지 [%d / %d]\n", curPage, lastPage);
			for (int i = firstPost; i < lastPost; i += 1) {
				System.out.printf("(%3d) [ 제목 : %-10s 작성자 : %-10s 날짜 : %s 조회수 : %-3d ]\n", i + 1,
						boardList.get(i).getTitle(), boardList.get(i).getId(), boardList.get(i).getDate(),
						boardList.get(i).getHits());
			}
			System.out.println("[1] 이전");
			System.out.println("[2] 이후");
			System.out.println("[3] 게시글 보기");
			System.out.println("[0] 뒤로가기");
			int sel = Util.getValue("메뉴 선택", 0, 3);
			if (sel == 1) { // 이전
				if (curPage == 1)
					continue;
				curPage -= 1;
			} else if (sel == 2) { // 이후
				if (curPage == lastPage)
					continue;
				curPage += 1;
			} else if (sel == 3) { // 게시글 보기
				int choice = Util.getValue("게시글 번호 입력", firstPost + 1, lastPost) - 1;
				boardList.get(choice).setHits(boardList.get(choice).getHits() + 1);
				System.out.printf("[%d][ 제목 : %-10s 작성자 : %-10s 날짜 : %s 조회수 : %-3d ]\n",
						boardList.get(choice).getBoradNum(), boardList.get(choice).getTitle(),
						boardList.get(choice).getId(), boardList.get(choice).getDate(),
						boardList.get(choice).getHits());
				System.out.println("----------");
				System.out.printf("    %s\n", boardList.get(choice).getContents());
			} else if (sel == 0) { // 뒤로가기
				MallController cont = MallController.getInstance();
				if (cont.getLoginId().equals("admin"))
					cont.setNext("AdminBoard");
				else
					cont.setNext("MemberBoard");
				break;
			}
		}
	}

	public void addAPost() { // 게시글을 한 개 추가합니다.
		MallController cont = MallController.getInstance();
		Board post = new Board();
		Board.increaseNum();
		post.setBoradNum(Board.getNum());
		String title = Util.getValue("제목");
		post.setTitle(title);
		String content = Util.getContent("내용");
		post.setContents(content);
		post.setId(cont.getLoginId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowTime = sdf.format(now);
		post.setDate(nowTime);
		boardList.add(post);
		System.out.println("게시글을 추가했습니다.");
	}

	public void showMyPosts() { // 내 게시글들을 관리합니다.
		MallController cont = MallController.getInstance();
		while (true) {
			System.out.println("----------[ 내 게시글 목록 ]----------");
			for (Board b : boardList) {
				if (b.getId().equals(cont.getLoginId())) {
					System.out.printf("(%3d) [ 제목 : %-10s 작성자 : %-10s 날짜 : %s 조회수 : %-3d ]\n", b.getBoradNum(),
							b.getTitle(), b.getId(), b.getDate(), b.getHits());
					System.out.println("    " + b.getContents());
					System.out.println("--------------------");
				}
			}
			System.out.println("[1] 삭제");
			System.out.println("[0] 뒤로가기");
			int sel = Util.getValue("메뉴 선택", 0, 1);
			if (sel == 1) { // 삭제
				deleteAPost();
			} else if (sel == 0) { // 뒤로가기
				break;
			}
		}
	}

	private void deleteAPost() { // 번호를 입력받아서 게시글 하나를 삭제합니다.
		MallController cont = MallController.getInstance();
		int num = Util.getValue("삭제할 게시물의 번호 입력", 1, Board.getNum());
		for (Board post : boardList) {
			if (post.getBoradNum() == num) {
				if (post.getId().equals(cont.getLoginId())) {
					boardList.remove(post);
					System.out.println("게시글을 삭제했습니다.");
					return;
				} else {
					System.out.println("내 게시글만 삭제할 수 있습니다.");
				}
			}
		}
		System.out.println("삭제에 실패했습니다.");
	}

	public void deleteAPostAsAdmin() {
		for (Board b : boardList) {
			System.out.printf("(%3d) [ 제목 : %-10s 작성자 : %-10s 날짜 : %s 조회수 : %-3d ]\n", b.getBoradNum(), b.getTitle(),
					b.getId(), b.getDate(), b.getHits());
			System.out.println("    " + b.getContents());
			System.out.println("--------------------");
		}
		int num = Util.getValue("삭제할 게시글의 고유번호를 입력하세요.", 1, Board.getNum());
		for (Board post : boardList) {
			if (post.getBoradNum() == num) {
				boardList.remove(post);
				System.out.println("게시글을 삭제했습니다.");
				return;
			}
		}
		System.out.println("존재하지 않는 게시물입니다.");
	}
}
