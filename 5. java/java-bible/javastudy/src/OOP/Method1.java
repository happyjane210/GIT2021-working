package OOP;

public class Method1 {
	
	// add 매소드를 생성
	public int add(int num1, int num2) {
		return num1 + num2 ;
	}
	
	public static void main(String[] args) {
		
		
		Method1 method1 = new Method1();  // 객체를 생성
		
		int result = method1.add(10, 30);  // add 메소드를 호출
		
		System.out.println(result);  // result를 출력
	}
}
