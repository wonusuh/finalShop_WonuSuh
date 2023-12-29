package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	private static final String CUR_PATH = System.getProperty("user.dir") + "//src//files//";

	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");

		private String name;

		FileName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private FileDAO() {
		init();
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}

	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			// System.out.println("파일이 이미 있음");
		}
	}

	private void init() {
		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);
	}

	public static void load() { // 파일의 데이터를 읽어서 각 DAO의 배열에 넣습니다.
		BoardDAO boardDAO = BoardDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();
		ItemDAO itemDAO = ItemDAO.getInstance();
		memberDAO.putDataIn(getFileAsString(FileName.MEMBER.name));
		boardDAO.putDataIn(getFileAsString(FileName.BOARD.name));
		cartDAO.putDataIn(getFileAsString(FileName.CART.name));
		itemDAO.putDataIn(getFileAsString(FileName.ITEM.name));
	}

	private static String getFileAsString(String fileName) { // 파일을 읽어서 String으로 리턴합니다.
		String data = "";
		try (FileReader fr = new FileReader(CUR_PATH + fileName); BufferedReader br = new BufferedReader(fr)) {
			while (true) {
				String oneLine = br.readLine();
				if (oneLine == null)
					break;
				data += oneLine + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = data.substring(0, data.length() - 1);
		System.out.println(fileName + " 데이터를 로드했습니다.=====");
		return data;
	}

	public static void collectDataFromDAOs() { // 각 DAO의 데이터들을 파일로 저장합니다.
		BoardDAO boardDAO = BoardDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();
		ItemDAO itemDAO = ItemDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
//		System.out.println(boardDAO.getBoardDataAsString());
//		System.out.println(cartDAO.getCartDataAsString());
//		System.out.println(itemDAO.getItemDataAsString());
//		System.out.println(memberDAO.getMemberDataAsString());
		save("board.txt", boardDAO.getBoardDataAsString());
		save("cart.txt", cartDAO.getCartDataAsString());
		save("item.txt", itemDAO.getItemDataAsString());
		save("member.txt", memberDAO.getMemberDataAsString());
	}

	private static void save(String fileName, String data) { // String data 를 파일로 저장합니다.
		try (FileWriter fr = new FileWriter(CUR_PATH + fileName)) {
			fr.write(data);
			System.out.printf("%s 에 저장했습니다.\n", fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
