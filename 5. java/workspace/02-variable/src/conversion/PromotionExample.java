package conversion;

public class PromotionExample {

	public static void main(String[] args) {
		byte byteValue = 10;    // -128 0 127
		// ���� �ս��� ���� ������ �ٷ� ��ȯ ����, ��������(1byte) -> ū������ ����(4byte)
		int intValue = byteValue;   // -21�� 0 21��
		System.out.println(intValue);
	}

}
