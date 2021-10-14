package exercise;

import java.util.Date;

public class Printer {
	
	static Printer pinter = new Printer();

	public static void println(int i) {
		// TODO Auto-generated method stub
		System.out.println(i);
	}

	public static void println(boolean b) {
		// TODO Auto-generated method stub
		System.out.println(b);
	}

	public static void println(double d) {
		// TODO Auto-generated method stub
		System.out.println(d);
	}

	@SuppressWarnings("deprecation")
	public static void println(String string) {
		// TODO Auto-generated method stub
		System.out.println(new Date().toLocaleString() + ":" +  string);
	}
	
	public static void println1(String string3) {
		// TODO Auto-generated method stub
		System.out.println(string3);
	}
	

	public static void println(String string, int i) {
		
		System.out.println(i + " " + string);
	}

	public static void println(String string, int i, String string2) {
		
		System.out.println(i + " " + string + " " + string2);
	}
	
	
	// 출력할때 앞에 테코레이터를 추가해서 출력
	//ptintln("홍길동", 1)
	// 1 홍길동
	
	// 출력할때 앞뒤에 데코레이터를 추가해서 출력
	// println("홍길동", 1 , "--")
	// 1 홍길동 --
}
