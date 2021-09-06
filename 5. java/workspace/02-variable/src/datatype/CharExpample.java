package datatype;

public class CharExpample {
	public static void main(String[] args) {
		char c1 = 'A';       // 문자형에 문자 직접 저장
		char c2 = 65;        // 10진수 유니코드
		char c3 = '\u0041';  // 16진수 유니코드
		
		char c4 = '가';       // 문자형에 문자 직접 저장
		char c5 = 44032;      // 10진수 유니코드
		char c6 = '\uac00';   // 16진수 유니코드
		
		int unicodeA = c1;
		int unicodeGa = c4;
		
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);
		System.out.println("c3 : " + c3);
		
		System.out.println("c4 : " + c4);
		System.out.println("c5 : " + c5);
		System.out.println("c6 : " + c6);
		
		System.out.println(unicodeA);   // 유니코드를 숫자로 인식하고 출력
		System.out.println(unicodeGa);
	}

}
