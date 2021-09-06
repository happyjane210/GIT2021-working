package unary;

public class DenyLogicOperator {

	public static void main(String[] args) {
		boolean play = true;
		System.out.println(play);
		
		play = !play;
		System.out.println(play);
		
		play = !!play;  // !! 이중부정 true -> false
		System.out.println(play);  

	}

}
