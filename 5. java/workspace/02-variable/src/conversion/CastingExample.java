package conversion;

public class CastingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int intValue = 44032;
		// (��ȯŸ��) ������
		// �� �ս��� ���� ����,
		// 0~ 2^16 - 1
		char charValue = (char) intValue;
		System.out.println(charValue);
		
		// ������ �ս��� ���� 3.14 => 3
		double doubleValue = 3.14;
		intValue = (int) doubleValue;
		System.out.println(intValue);
	}

}
