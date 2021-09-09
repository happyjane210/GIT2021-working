package extends_keyword;

// 관리자
public class Admin extends User {
	private String deptNo;  // 부서번호

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	
}



// 알트 쉬프트 s : 겟셋단축키