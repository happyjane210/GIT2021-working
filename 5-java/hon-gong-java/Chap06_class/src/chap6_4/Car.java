package chap6_4;

public class Car {
	//filed
	int gas;
	
	// Constructor
	
	//Method
	void setGas(int gas) {
		this.gas = gas;
	}
	
	// boolean �޼��� �̸��� is-- �� �����ϴ� ��찡 ����, ~�����ƴ���
	boolean isLeftGas() {
		if (gas == 0) {
			System.out.println("gas�� �����ϴ�.");
			return false;
		}
		System.out.println("gas�� �ֽ��ϴ�.");
		return true;
	}
	
	void run() {
		while(true) {
			if (gas > 0) {
				System.out.println("�޸��ϴ�. (gas�ܷ�:" + gas + ")");
				gas -= 1;  // gas--;
			} else {
				System.out.println("����ϴ�. (gas�ܷ�:" + gas + ")");
				return;
			}
		}
	}
}
