package abstract_class;

// 사용자  - 자식개체 구조만 나타내는 클래스

// final class 상속 불가한 클래스
// public final class User { 
// 자식객체의 기본필드, 매서드 추가 구현해야되는 매서드의 뼈대만 제공
public abstract class User {
	private String id;
	private String name;
	private String phone;
	
	// @override
	public abstract void printUserInfo() {
		
	}
	//기본 뼈대 제공
	
	
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

