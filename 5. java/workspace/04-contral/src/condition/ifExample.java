package condition;

public class ifExample {
	public static void main(String[] args) {
		// String empty = ""; �ڹٿ��� ���ȵ�
		// boolean �� ���갪�� if ���� ���ǽ����� �ü�����
		boolean empty = "" != null;
		
		if(empty) {
			
		}
		
		
		int a = 10;
		// if (a) �Ұ���
		if (a > 9) {
			System.out.println(a);
		}
		
		if (true) {
			System.out.println(a);
		}
	}
}
