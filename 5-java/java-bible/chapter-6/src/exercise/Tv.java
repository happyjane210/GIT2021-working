package exercise;

public class Tv { 
	// �ۺ� Ŭ������ �ݵ�� .java �ҽ����� �̸��� ��ġ�ؾ��� (��ҹ��� ����)
	// �ϳ��� java ���� �ݵ�� public class �Ѱ��� ���� (2���� ����)
	
	String color;   // ����
	boolean power;  // ��������
	int channel;    // ä��
	
	public void power() {
		power = !power;
	} // Ƽ�� Ű�ų� ���� ����� �ϴ� �ż���
	
	public void channelUp() {
		++channel;
	} // ä���� �ø��� ����� �ϴ� �ż���
	
	public void channelDown() {
		// TODO Auto-generated method stub
		--channel;
	}  // ä���� ������ ����� �ϴ� �ż���
	
	
}

	class Tvjskwnj {  // �ۺ� Ŭ������ �ƴϸ� ���ϸ� ��ġ���� �ʾƵ� ��
	
}