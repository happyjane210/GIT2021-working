package exercise;

public class TvTest_2 {
	public static void main(String[] args) {
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		
		System.out.println("t1의 채널값은 " + t1.channel + "입니다");
		System.out.println("t2의 채널값은 " + t2.channel + "입니다");
		System.out.println("------------------------");
		
		t1.channel = 7;		// 7
		t1.channelDown();	// 7 - 1
		t2.channelUp();		// +1
		t2.channelUp();		// +1
		
		System.out.println("t1의 채널값은 " + t1.channel + "입니다");
		System.out.println("t2의 채널값은 " + t2.channel + "입니다");
		System.out.println("------------------------");
		
		t2 = t1;  // t1이 저장하고 있는 값을 t2에 저장한다, t2의 원래 인스턴스는 사라진다.
		t1.channel = 11;
		t1.channelUp();   // 11 + 1
		
		t2.channelDown();  // 12 - 1   참조한 객체 채널 바꿔도 연결된 변수값도 같이 바뀜
		
		System.out.println("t1의 채널값은 " + t1.channel + "입니다");
		System.out.println("t2의 채널값은 " + t2.channel + "입니다");
		
		
	}
}
