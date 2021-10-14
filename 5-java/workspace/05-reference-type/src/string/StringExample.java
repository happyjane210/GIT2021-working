package string;

public class StringExample {
	public static void main(String[] args) {
		String name1 = "이주은";  // string 은 " " , char 는 ' ' 한글자 
		String name2 = "이주은";
		// String 동치비교에서 이 방법 쓰지마
		System.out.println(name1 == name2);
		// String 인 경우 동치비교에 equal 함수를 사용
		System.out.println(name1.equals(name2));
		
		//---------------------------------
		
		String name3 = new String("햅삐제인");
		String name4 = new String("happyjane");
		// String 동치비교에서 이 방법 쓰지마
		System.out.println(name3 == name4);
		// String 인 경우 동치비교에 equal 함수를 사용
		System.out.println(name3.equals(name4));
		
		// 절대하지마
//		if(name3 == "고대근") {	
//		}
		
		// equal 함수만 사용
//		if(name3.equals("고대근")) {
//		}
		
		
		
	}
}
