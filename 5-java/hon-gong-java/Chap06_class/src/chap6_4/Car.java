package chap6_4;

public class Car {
	//filed
	int gas;
	
	// Constructor
	
	//Method
	void setGas(int gas) {
		this.gas = gas;
	}
	
	// boolean 메서드 이름은 is-- 로 시작하는 경우가 많음, ~인지아닌지
	boolean isLeftGas() {
		if (gas == 0) {
			System.out.println("gas가 없습니다.");
			return false;
		}
		System.out.println("gas가 있습니다.");
		return true;
	}
	
	void run() {
		while(true) {
			if (gas > 0) {
				System.out.println("달립니다. (gas잔량:" + gas + ")");
				gas -= 1;  // gas--;
			} else {
				System.out.println("멈춥니다. (gas잔량:" + gas + ")");
				return;
			}
		}
	}
}
