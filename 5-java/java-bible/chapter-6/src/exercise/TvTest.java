package exercise;

public class TvTest {
	public static void main(String[] args) {
		Tv t;				// tv�ν��Ͻ��� �����ϱ� ���� ���� t ����
		t = new Tv();		// tv�ν��Ͻ� ����
		t.channel = 7;		// tv�ν��Ͻ� ������� channel �� ���� 7���Ѵ�
		t.channelDown();	// tv�ν��Ͻ� �޼��� channelDown ȣ��
		
		System.out.println("���� ä���� " + t.channel + " �Դϴ�.");
		//���� ä���� 6 �Դϴ�.

	}
}
