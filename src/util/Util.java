package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	private Util() {
	}

	private static Scanner sc = new Scanner(System.in);

	public static void closeScanner() {
		sc.close();
	}

	public static int getValue(String menu, int start, int end) {
		while (true) {
			System.out.printf("▶ %s [%d-%d] : ", menu, start, end);
			try {
				int sel = Util.sc.nextInt();
				sc.nextLine();
				if (sel < start || sel > end)
					throw new Exception();
				return sel;
			} catch (Exception e) {
				System.out.println("숫자를 확인하세요.");
				sc.nextLine();
			}
		}
	}

	public static String getValue(String string) {
		System.out.printf("▶ %s : ", string);
		String input = sc.next();
		sc.nextLine();
		return input;
	}

	public static boolean isAppropriateId(String id) {
		Pattern p = Pattern.compile("^[0-9A-Za-z]{4,10}$");
		Matcher m = p.matcher(id);
		if (m.matches())
			return true;
		return false;
	}

	public static boolean isAppropriatePw(String pw) {
		Pattern p = Pattern.compile("^[^ ]{4,10}$");
		Matcher m = p.matcher(pw);
		if (m.matches())
			return true;
		return false;
	}

	public static boolean isAppropriateName(String name) {
		Pattern p = Pattern.compile("^[0-9a-zA-Z가-힣]{2,10}$");
		Matcher m = p.matcher(name);
		if (m.matches())
			return true;
		return false;
	}

	public static String getContent(String string) {
		System.out.printf("▶ %s 입력 : ", string);
		String input = sc.nextLine();
		if(sc.hasNext()) {
			sc.nextLine();
		}
		return input;
	}
}
