package conversion;

public class CastingExample {

	public static void main(String[] args) {
		
		int intValue = 44032;   //"��"�� �����ڵ�
		// (��ȯŸ��) ������
		// �� �ս��� ���� ����,
		// 0~ 2^16 - 1
		// char : ������, �ѱ��ڸ� �������ִ� '' �ܵ���ǥ
		//              (a������) b�������� �����Ѵ�
		char charValue = (char) intValue;
		System.out.println(charValue);
		
		// ������ �ս��� ���� 3.14 => 3  , int�� �������̱� ���� 
		double doubleValue = 3.14;
		intValue = (int) doubleValue;
		System.out.println(intValue);
		
		// ��
		// 3
	}

}
