package exercise;

public class TvTest_2 {
	public static void main(String[] args) {
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		
		System.out.println("t1�� ä�ΰ��� " + t1.channel + "�Դϴ�");
		System.out.println("t2�� ä�ΰ��� " + t2.channel + "�Դϴ�");
		System.out.println("------------------------");
		
		t1.channel = 7;		// 7
		t1.channelDown();	// 7 - 1
		t2.channelUp();		// +1
		t2.channelUp();		// +1
		
		System.out.println("t1�� ä�ΰ��� " + t1.channel + "�Դϴ�");
		System.out.println("t2�� ä�ΰ��� " + t2.channel + "�Դϴ�");
		System.out.println("------------------------");
		
		t2 = t1;  // t1�� �����ϰ� �ִ� ���� t2�� �����Ѵ�, t2�� ���� �ν��Ͻ��� �������.
		t1.channel = 11;
		t1.channelUp();   // 11 + 1
		
		t2.channelDown();  // 12 - 1   ������ ��ü ä�� �ٲ㵵 ����� �������� ���� �ٲ�
		
		System.out.println("t1�� ä�ΰ��� " + t1.channel + "�Դϴ�");
		System.out.println("t2�� ä�ΰ��� " + t2.channel + "�Դϴ�");
		
		
	}
}
