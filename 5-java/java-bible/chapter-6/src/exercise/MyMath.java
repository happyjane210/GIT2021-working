package exercise;

public class MyMath {
	
	void printGugudan(int dan) {   //�̸��� printGugudan �� �ż���
		if(!(2<=dan && dan <= 9))
			return;		// �Է¹��� ��(dan)�� 2-9�� �ƴϸ�, �ż��� �����ϰ� ���ư���,
						//for ������ �Ȱ��� �ż��带 ����
		
		for (int i=1 ; i <= 9 ; i++) {
			System.out.printf("%d * %d = %d%n", dan, i, dan * i);
			//				  dan *  i = dan*i      dan: �޼��� �Է°�, i �ݺ��� ��°� 1-9
		}
	}
	
	public long add(long a, long b) {
		return a + b;
	 	// �Ʒ� ���ٰ� ����
		// long result = a + b;
		// return result;
	}
	
	long subtract (long a, long b) {
		return a - b;
	}
	
	long multiply (long a, long b) {
		return a * b;
	}
	
	double divide (double a, double b) {
		return a / b;
	}
	
	long max (long a, long b) {
		//long result = 0;
		//result = a > b ? a : b;   ���� ������
		
//		if(a>b) {
//			result = a;
//		} else {
//			result = b;
//		}
		return a > b ? a : b;       // return result �� �ٷ� ���԰���
	}
	
	long min (long a, long b) {
		//return a < b ? a : b;
		if(a < b)
			return a;
		else
			return b;
		
	}
	
		

	

}
