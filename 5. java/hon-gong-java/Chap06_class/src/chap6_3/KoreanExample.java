package chap6_3;

public class KoreanExample {
	public static void main(String[] args) {
		Korean k1 = new Korean("±èÀÚ¹Ù", "001122-1234567");
		System.out.println("k1.name: " + k1.name);
		System.out.println("k1.ssn: " + k1.ssn);
		
		System.out.println("-----------");
		
		Korean k2 = new Korean("¹ÚÈ¥°ø", "112233-7654321");
		System.out.println("k2.name: " + k2.name);
		System.out.println("k2.ssn: " + k2.ssn);
		
	}
}
