// �������̵� : ��ӹ��� �θ��� �ż��� ������ �ڽĴ������� �ٲܼ����� (�̸��� ����)

package OOP;
// �� ��Ű�� �ȿ� ���� �̸��� Ŭ���� ������ �ȵ�!! �ٲ������

public class Animal2 {  // �θ�
	// ������ Ư¡
	int legs;
	int ears;
	String bark;
	
	// ����� ������ �ż��嵵 �ٲ��� , �ż����� ������ �ٲٴ� ���� �������̵��̶���� (�̸��� ����)
	public void bark() {
		System.out.println(bark);
	}

}



class Cat1 extends Animal2{    // �ڽ�


	public Cat1( String bark) {
		this.bark = bark;   // this : Ŭ���� �ڱ��ڽ��� �����ϰ� ����
	}
	
	public void bark() {   // �������̵�
		System.out.println(bark + legs + ears);
		super.bark();  // super : ����� �θ� �ż��带 �����ؼ� ���⼭ ȣ��
	}
	
}


class House1 {
	public static void main(String[] args) {
		

		Cat1 cat = new Cat1("miu");
		cat.bark();
		
	}
}
