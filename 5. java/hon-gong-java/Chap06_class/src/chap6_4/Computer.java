package chap6_4;

public class Computer {
	//filed
	
	//Constructor
	
	//Method
	// Computer ��� ��ü�� �־�� ���� �ִ� �޼��� (computer Ŭ���� �ȿ� �ֱ� ����?)
	// �迭 ������ �Ű������� ����
	public int sum1(int[] values) {  // sum1�ż����� �Ű��������� ��� ���ؼ� ���ϰ����� ������
		int sum = 0;
		for(int i = 0 ; i < values.length ; i++) {
			sum += values[i];
		}
		return sum;
	}
	
	// int ... values == values�� �迭 Ÿ������ �ν���
	// ... ���� �� �Ű������� ���� : �迭������ ����
	int sum2(int ... values) {
		int sum = 0;
		for(int i = 0 ; i < values.length ; i++) {
			sum += values[i];
		}
		return sum;
	}
	
}
