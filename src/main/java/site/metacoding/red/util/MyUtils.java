package site.metacoding.red.util;

public class MyUtils {

	public static int 한글체크(String username) {
		int size = 0;
		for (int i = 0; i < username.length(); i++) {
			int num = username.charAt(i);
			System.out.println(username.charAt(i));
			if (num > 122) { // 유니코드 122 보다 위에있는 것들은 잡다한 문자
				System.out.println("한글이라 사용할 수 없어요");
				size = size + 3; // 한글은 3바이트라서
			} else {
				size = size + 1; // 영문은 1바이트
			}
		
		}
		return size;
	}
}