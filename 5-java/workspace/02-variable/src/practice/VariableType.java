package practice;

public class VariableType {
	public static void main(String[] args) {
		// 숫자형
			// 정수형 1,2,3,4,5,-2,-5
				int c; // -21억~21억
				//long e; // 
			// 실수형 0.1, 0.3
				double f; // double > int 보다 더 큼
				//float d; // 7개자리수
			
		// 문자형
			char a = 'A'; // 무조건 단따옴표'', 한글자밖에 못씀 'AB'
		
		// 문자열형 (참조형)
			String b = "ABC";  // 무조건 쌍따옴표"", 여러글자 써도됨
		
		// 논리형 (참 거짓)
			boolean q = true;
				int num1 = 12;
				int num2 = 23;
				int num3 = 18;
				
				boolean middleNum = (num1 < num2) && (num2 < num3);
				// && 둘중에 하나라도 거짓이면 거짓
				// || 둘중에 하나라도 참이면 참
		
		// 참조형
				
		

		
	
		
	}

	public static void main(String[] args) {
		// 선언과 초기화 (저장)
		// 정수타입의 a라는 변수 (선언)
		int a;   //let Number(a)
		// 정수타입의 a라는 변수에 10을 저장
		int a = 10; 
		
		// 문자타입의 b라는 변수 선언
		char b;
		// 문자타입의 b라는 변수에 'B'를 저장
		char b = 'B';
		
		
		// 문자열타입에 c라는 변수에 문자열 "abc"를 저장
		String c = "abc";
		
		//double > int 보다 큰 범위(자동 형변환)
		double d = 1;
		// int가 double보다 작기 때문에 자동으로 형변환이 안됨
		int f = (int)(0.1)
			
		String a = "이주은";
		int b = 26;
		System.out.println(a + b);
	}
	public static void main(String[] args) {
		int[] i = new int[100];
		for (int arr : i) {
			arr= arr++
			System.out.println(arr++);
		}
				}
}
