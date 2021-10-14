package exercise;

public class MyMath {
	
	void printGugudan(int dan) {   //이름이 printGugudan 인 매서드
		if(!(2<=dan && dan <= 9))
			return;		// 입력받은 단(dan)이 2-9가 아니면, 매서드 종료하고 돌아가기,
						//for 문까지 안가고 매서드를 나감
		
		for (int i=1 ; i <= 9 ; i++) {
			System.out.printf("%d * %d = %d%n", dan, i, dan * i);
			//				  dan *  i = dan*i      dan: 메서드 입력값, i 반복문 출력값 1-9
		}
	}
	
	public long add(long a, long b) {
		return a + b;
	 	// 아래 두줄과 같음
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
		//result = a > b ? a : b;   삼항 연산자
		
//		if(a>b) {
//			result = a;
//		} else {
//			result = b;
//		}
		return a > b ? a : b;       // return result 에 바로 대입가능
	}
	
	long min (long a, long b) {
		//return a < b ? a : b;
		if(a < b)
			return a;
		else
			return b;
		
	}
	
		

	

}
