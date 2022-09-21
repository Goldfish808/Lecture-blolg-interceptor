package site.metacoding.red;

import org.junit.jupiter.api.Test;

import site.metacoding.red.util.MyUtils;

public class MyTest {
	
	@Test
	public void 한글_입력_테스트() {
//		String s= "   ";
//		char[] c1 = s.toCharArray();
//		for(char c : c1) {
//			System.out.println((int)c);
//			if((int)c > 122) {
//				System.out.println("한글이라 사용할 수 없어요");
//			}
//		}
		int result = MyUtils.한글체크("유전S");
		System.out.println(result);
	}
}
