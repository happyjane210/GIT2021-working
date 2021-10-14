package polymorphism;

// 사용자 중에 멤버십이 있는 사용자 (쿠팡 멤버십)
public class Member extends User{
	private int point;
	
	// 매서드 오버라이딩 - 매서드 재정의
	// @Override
	// 매서드 시그니처 (signature) : 매서드명 + 매개변수(타입, 순서, 개수)
	// 매서드 시그니처는 동일해야함
	// @override - 써주는게 좋음 , 그래야 재정의한지 알수있음
	// 자식 파생클래스라고 봐야함 
	// public을 private 같이 수정범위 좁힐수는 없는데,
	// private-default 를 public 으로 수정범위를 넓힐수는 있음 
	@Override
	public void printUserInfo() {
		// 구현내용은 다름
		
		//1. 직접새로구현
		//System.out.println(this.getName() + ", " + this.getPhone()  + ", " + this.getPoint() + " - 포인트: " + this.point);
		//2. 부모매서드 사용 구현 (재활용)
		super.printUserInfo();
		System.out.print( " - 포인트: " + this.point);
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
