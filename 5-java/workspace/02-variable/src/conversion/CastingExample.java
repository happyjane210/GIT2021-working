package conversion;

public class CastingExample {

	public static void main(String[] args) {
		
		int intValue = 44032;   //"가"의 유니코드
		// (변환타입) 변수명
		// 값 손실이 없는 상태,
		// 0~ 2^16 - 1
		// char : 문자형, 한글자만 가질수있다 '' 단따옴표
		//              (a변수에) b변수값을 대입한다
		char charValue = (char) intValue;
		System.out.println(charValue);
		
		// 데이터 손실이 있음 3.14 => 3  , int는 정수형이기 때문 
		double doubleValue = 3.14;
		intValue = (int) doubleValue;
		System.out.println(intValue);
		
		// 가
		// 3
	}

}
