package OOP;

public class Method2 {
	
	// add �żҵ带 ����
	public int add(int num1, int num2) {
		return num1 + num2 ;
	}
	
	public static void main(String[] args) {
		
		
		Method2 method1 = new Method2();  // ��ü�� ����
		
		int result = method1.add(10, 30);  // add �޼ҵ带 ȣ��
		
		System.out.println(result);  // result�� ���
	}
}

// ȣ�� ����
// ��ǻ���� ��� [���� -> main() -> ��ü�� ���� -> add �޼ҵ带 ȣ�� -> 5���� add�ż��� ���� -> ���� �� ���� -> result�� ���].
// [���� -> main() -> add()ȣ�� -> print()ȣ��]
// ������ �Լ����� �����ϸ� �������� �ڵ带 ����� [print() -> add() -> main() -> ����]
