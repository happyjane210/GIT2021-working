package super_contructor;

public class Child extends Parent {
	private String name;
	
	// ȣ�� - 1
	public Child() {
		// super();  �Ͻ���(�ڵ�)���� ȣ���� ��
		this("ȫ�浿");
		// ��� -4
		System.out.println("Child() call");
	}
	
	// ȣ�� -4
	public Child(String name) {
		this.name = name;
		// ��� - 3
		System.out.println("Child(String name) call");
	}
	
	
}
