package polymorphism;

// ����� �߿� ������� �ִ� ����� (���� �����)
public class Member extends User{
	private int point;
	
	// �ż��� �������̵� - �ż��� ������
	// @Override
	// �ż��� �ñ״�ó (signature) : �ż���� + �Ű�����(Ÿ��, ����, ����)
	// �ż��� �ñ״�ó�� �����ؾ���
	// @override - ���ִ°� ���� , �׷��� ���������� �˼�����
	// �ڽ� �Ļ�Ŭ������� ������ 
	// public�� private ���� �������� �������� ���µ�,
	// private-default �� public ���� ���������� �������� ���� 
	@Override
	public void printUserInfo() {
		// ���������� �ٸ�
		
		//1. �������α���
		//System.out.println(this.getName() + ", " + this.getPhone()  + ", " + this.getPoint() + " - ����Ʈ: " + this.point);
		//2. �θ�ż��� ��� ���� (��Ȱ��)
		super.printUserInfo();
		System.out.print( " - ����Ʈ: " + this.point);
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
