package OOP;

public class ObjEx1 {  // Ŭ���� ����
	
	// �ν��Ͻ� ����, ��ü ���� , ��ü ���鶧 ���� �ʵ�  , ���ø� 
	int age;
	String name;
	
	static private int age2; // Ŭ���� ����, ��ü ���� ���ص� ���� ���� ����, Ŭ���� ����޸𸮿� ����
	
	public static void main(String[] args) {  // �ż��� ����
		
		
		// ���� ����, �׳� ���� �� �� ���� �޼��� �ȿ����� ����, �޼��尡 ������ ������
		int age;
		String name;
		age = 12;
		name = "smith";
		
		
		// �ν��Ͻ� ����, ��ü ����, Ŭ���� �������� ����� ��������
		// ��ü ����
		ObjEx1 obj = new ObjEx1();     // ��ü�� �����Ǹ� �޸𸮿� ����
		obj.age = 12;   // �ν��Ͻ� ����
		obj.name = "jane";
		
		
		//  static Ŭ���� ���� , ��ü�� ������ �ʰ� Ŭ�������� �ٷ� �����
		ObjEx1.age2 = 12;
	}
}
