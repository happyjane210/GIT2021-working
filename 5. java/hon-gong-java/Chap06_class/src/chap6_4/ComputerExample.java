package chap6_4;

public class ComputerExample {
	public static void main(String[] args) {
		Computer com = new Computer();
		
		// 변수를 배열로 설정해서 메소드 매개변수로 줌
		int[] values1 = {1,2,3};
		int result1 = com.sum1(values1);  // 매개로 배열 변수를 줌
		System.out.println("result1: " + result1);
		
		// 배열을 따로 선언하지 않고, sum1은 new int[]의 값을 모두 더해서 result2 로 대입해준다
		int result2 = com.sum1(new int[] {1,2,3,4,5});
		System.out.println("result2: " + result2);
	
		// 값의 목록으로 메서드의 매개값을 줌
		int result3 = com.sum2(1,2,3);  // 매개로 값의 목록을 줌: 배열의 내용
		System.out.println("result3: " + result3);
		
		int result4 = com.sum2(1,2,3,4,5);
		System.out.println("result4: " + result4);
	}
}
