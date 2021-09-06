package conversion;

public class CastingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int intValue = 44032;
		// (변환타입) 변수명
		// 값 손실이 없는 상태,
		// 0~ 2^16 - 1
		char charValue = (char) intValue;
		System.out.println(charValue);
		
		// 데이터 손실이 있음 3.14 => 3
		double doubleValue = 3.14;
		intValue = (int) doubleValue;
		System.out.println(intValue);
	}

}
