package chap6_4;

public class ComputerExample {
	public static void main(String[] args) {
		Computer com = new Computer();
		
		// ������ �迭�� �����ؼ� �޼ҵ� �Ű������� ��
		int[] values1 = {1,2,3};
		int result1 = com.sum1(values1);  // �Ű��� �迭 ������ ��
		System.out.println("result1: " + result1);
		
		// �迭�� ���� �������� �ʰ�, sum1�� new int[]�� ���� ��� ���ؼ� result2 �� �������ش�
		int result2 = com.sum1(new int[] {1,2,3,4,5});
		System.out.println("result2: " + result2);
	
		// ���� ������� �޼����� �Ű����� ��
		int result3 = com.sum2(1,2,3);  // �Ű��� ���� ����� ��: �迭�� ����
		System.out.println("result3: " + result3);
		
		int result4 = com.sum2(1,2,3,4,5);
		System.out.println("result4: " + result4);
	}
}
