package variable;

public class VariableExample {
	public static void main(String[] args) {
		// 타입 변수명
		int age;
		double value = 0;
		
		// 변수 선언 및 대입 1
		int score;
		score = 90;
		
		// 변수 선언 및 대입 2
		int score2 = 90;
		
		// 초기화를 한 후에 참조해야함
		age = 30;
		int result = age + score;
		
		System.out.println(result);
		System.out.println(value);
		System.out.println(score2);
	}
}
