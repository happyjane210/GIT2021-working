package OOP;

public class Method1 {
	
	// add �żҵ带 ����
	public int add(int num1, int num2) {
		return num1 + num2 ;
	}
	
	public static void main(String[] args) {
		
		
		Method1 method1 = new Method1();  // ��ü�� ����
		
		int result = method1.add(10, 30);  // add �޼ҵ带 ȣ��
		
		System.out.println(result);  // result�� ���
	}
}
