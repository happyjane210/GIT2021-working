package chpa13_1;

import java.util.Objects;

public class Student {
	// filed
	public int sno;
	public String name;
	
	// constructor ������
	public Student(int sno, String name) {
		this.sno = sno;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		// �л� ��ü ���� ��ȸ
		if(obj instanceof Student) {
			// Student ��ü��� Student�� Ÿ�� ��ȯ
			Student student = (Student) obj;
			return sno == student.sno && name.equals(student.name);
		} else {
			return false;
		}
	}
	
	
	// �ؽ��ڵ� ������
	@Override
	public int hashCode() {
		return Objects.hash(sno, name)
	}
}
