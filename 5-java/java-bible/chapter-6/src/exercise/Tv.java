package exercise;

public class Tv { 
	// 퍼블릭 클래스는 반드시 .java 소스파일 이름과 일치해야함 (대소문자 구분)
	// 하나의 java 에는 반드시 public class 한개만 존재 (2개는 에러)
	
	String color;   // 색상
	boolean power;  // 전원상태
	int channel;    // 채널
	
	public void power() {
		power = !power;
	} // 티비를 키거나 끄는 기능을 하는 매서드
	
	public void channelUp() {
		++channel;
	} // 채널을 올리는 기능을 하는 매서드
	
	public void channelDown() {
		// TODO Auto-generated method stub
		--channel;
	}  // 채널을 내리는 기능을 하는 매서드
	
	
}

	class Tvjskwnj {  // 퍼블릭 클래스가 아니면 파일명 일치하지 않아도 됨
	
}