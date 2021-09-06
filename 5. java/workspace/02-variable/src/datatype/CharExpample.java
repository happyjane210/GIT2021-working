package datatype;

public class CharExpample {
	public static void main(String[] args) {
		char c1 = 'A';       // �������� ���� ���� ����
		char c2 = 65;        // 10���� �����ڵ�
		char c3 = '\u0041';  // 16���� �����ڵ�
		
		char c4 = '��';       // �������� ���� ���� ����
		char c5 = 44032;      // 10���� �����ڵ�
		char c6 = '\uac00';   // 16���� �����ڵ�
		
		int unicodeA = c1;
		int unicodeGa = c4;
		
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);
		System.out.println("c3 : " + c3);
		
		System.out.println("c4 : " + c4);
		System.out.println("c5 : " + c5);
		System.out.println("c6 : " + c6);
		
		System.out.println(unicodeA);   // �����ڵ带 ���ڷ� �ν��ϰ� ���
		System.out.println(unicodeGa);
	}

}
