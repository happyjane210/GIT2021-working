// import: �ٸ� ��Ű���鳢�� Ŭ������ �ҷ����� ��

package forimport;

import java.util.Scanner;  
//     ���� ��� java>util>Scanner Ŭ������ �ҷ��� 

import OOP.Animal;
import OOP.Human5;
import OOP.Overloading;

public class Forimport {
	// ��ü�� ����� ���� Ŭ������ ����Ʈ
	
	Animal animal = new Animal();
	
	Overloading over = new Overloading();
	
	//Scanner scanner = new Scanner();   
	
	public static void main(String[] args) {
		System.out.println();  // sysout �� ��� �ż��� �ȿ����� �����, Ŭ���� ���������� ����
	}
	
	public static void main1(String[] args) {
		// ��ü�� main�ȿ� ���൵ �Ǵµ�, ��ü�� ���� �����Ϸ��� main�ȿ� �������
		Human5 human = new Human5();
		human.age =123;
		human.setId(1234);
		human.name = "dd";
		human.password = "34232";
		
		
	}
	
class women {
	public static void main(String[] args) {
		Human5 human = new Human5();
		human.setAge(12);
		human.getAge(); // ��ϵ� ���� ������
}
}
