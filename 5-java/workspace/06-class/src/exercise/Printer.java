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
	
	
	// ����Ҷ� �տ� ���ڷ����͸� �߰��ؼ� ���
	//ptintln("ȫ�浿", 1)
	// 1 ȫ�浿
	
	// ����Ҷ� �յڿ� ���ڷ����͸� �߰��ؼ� ���
	// println("ȫ�浿", 1 , "--")
	// 1 ȫ�浿 --
}
