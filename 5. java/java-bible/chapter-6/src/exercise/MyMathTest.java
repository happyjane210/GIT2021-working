package exercise;

public class MyMathTest {
	public static void main(String[] args) {
		
		MyMath mm = new MyMath();   // �ν��Ͻ� ����
		long resultmin = mm.min(5, 3);
		long result = mm.max(5L, 3L);
		long result1 = mm.add(5L, 3L);
		long result2 = mm.subtract(5L, 3L);
		long result3 = mm.multiply(5L, 3L);
		double result4 = mm.divide(5L, 3L);
		mm.printGugudan(5);
		
		System.out.println("min(5L, 3L) = " + resultmin);
		System.out.println("max(5L, 3L) = " + result);
		System.out.println("add(5L, 3L) = " + result1);
		System.out.println("subtract(5L, 3L) = " + result2);
		System.out.println("multiply(5L, 3L) = " + result3);
		System.out.println("divide(5L, 3L) = " + result4);
	
		
		
		
	}
	
}
