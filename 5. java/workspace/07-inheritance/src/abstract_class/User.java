package abstract_class;

// �����  - �ڽİ�ü ������ ��Ÿ���� Ŭ����

// final class ��� �Ұ��� Ŭ����
// public final class User { 
// �ڽİ�ü�� �⺻�ʵ�, �ż��� �߰� �����ؾߵǴ� �ż����� ���븸 ����
public abstract class User {
	private String id;
	private String name;
	private String phone;
	
	// @override
	public abstract void printUserInfo() {
		
	}
	//�⺻ ���� ����
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	

}

