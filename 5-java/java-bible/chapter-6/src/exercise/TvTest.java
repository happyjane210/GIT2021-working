package exercise;

public class TvTest {
	public static void main(String[] args) {
		Tv t;				// tv인스턴스를 참조하기 위한 변수 t 선언
		t = new Tv();		// tv인스턴스 생성
		t.channel = 7;		// tv인스턴스 멤버변수 channel 의 값을 7로한다
		t.channelDown();	// tv인스턴스 메서드 channelDown 호출
		
		System.out.println("현재 채널은 " + t.channel + " 입니다.");
		//현재 채널은 6 입니다.

	}
}
