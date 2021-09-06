package conversion;

public class PromotionExample {

	public static void main(String[] args) {
		byte byteValue = 10;    // -128 0 127
		// 값의 손실이 없기 때문에 바로 변환 가능, 작은범위(1byte) -> 큰범위에 대입(4byte)
		int intValue = byteValue;   // -21억 0 21억
		System.out.println(intValue);
	}

}
