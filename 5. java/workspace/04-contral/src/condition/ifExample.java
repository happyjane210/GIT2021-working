package condition;

public class ifExample {
	public static void main(String[] args) {
		// String empty = ""; 자바에선 허용안됨
		// boolean 의 연산값만 if 문의 조건식으로 올수있음
		boolean empty = "" != null;
		
		if(empty) {
			
		}
		
		
		int a = 10;
		// if (a) 불가능
		if (a > 9) {
			System.out.println(a);
		}
		
		if (true) {
			System.out.println(a);
		}
	}
}
