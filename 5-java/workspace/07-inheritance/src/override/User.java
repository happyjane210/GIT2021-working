package override;

// 사용자

// final class 상속 불가한 클래스
// public final class User { 
public class User {
	private String id;
	private String name;
	private String phone;
	
	// @override
	public void printUserInfo() {
		System.out.println("\n" + name + ", " + phone);
	}
	
	
	
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

