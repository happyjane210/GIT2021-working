package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//Map<Key, Value> human = new HashMap<>();

public class MapExample {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Map<Integer, Human> human = new HashMap<>();
		
		// Ű���� boolean, Integer, String ����
		human.put(1, new Human("��浿", 123, "����", 1534000));
		// hong �̶�� Ű������ new Human �̶�� ��ü�� ��  => HashMap�� ������
		human.put(2, new Human("�̱浿", 122343, "����", 15000));
		human.put(3, new Human("�ڱ浿", 132342, "�뱸", 1500320));
		human.put(4, new Human("�ֱ浿", 111233, "�λ�", 15023400));
		human.put(5, new Human("��浿", 1313, "����", 15023400));
		human.put(6, new Human("ȫ�浿", 1873, "����", 1523424000));
		
		// �����ö� �޼��尡 �ʿ���
		Human human1 = human.get("��");  // Ű�� ���ؼ� ���� ������
		
		// �ڵ����� toString() �� �پ�����
		// toString()�� generate �������
		
		// human.size()   �迭�� .length �� ����  0���� ������
		// for ���ڷ� 
//		for(int i = 0; i < human.size(); i++) {
//			System.out.print("���ڸ� �Է��ϼ���> ");
//			System.out.println(human.get(scanner.nextInt()));
//		}
		
//		System.out.print("���ڸ� �Է��ϼ���> ");
//		System.out.println(human.get(scanner.nextInt()));
		
		// Ű ���� ã�� for�� , (�ڹٽ�ũ��Ʈ for-each���� ���� ���),  ó������ ������ �ݺ�,
		for (Integer id : human.keySet()) {
			if(id == 5) {  // Ű�� 5���� ȣ����
				System.out.println(human.get(id));
			}
			//System.out.println(human.get(id));
		}
		
	}
}
