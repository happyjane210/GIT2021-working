package OOP;

public class Method2 {
	
	// add 매소드를 생성
	public int add(int num1, int num2) {
		return num1 + num2 ;
	}
	
	public static void main(String[] args) {
		
		
		Method2 method1 = new Method2();  // 객체를 생성
		
		int result = method1.add(10, 30);  // add 메소드를 호출
		
		System.out.println(result);  // result를 출력
	}
}

// 호출 스택
// 컴퓨터적 사고 [실행 -> main() -> 객체를 생성 -> add 메소드를 호출 -> 5번줄 add매서드 실행 -> 더한 값 리턴 -> result를 출력].
// [실행 -> main() -> add()호출 -> print()호출]
// 마지막 함수까지 실행하면 역순으로 코드를 지운다 [print() -> add() -> main() -> 공백]
