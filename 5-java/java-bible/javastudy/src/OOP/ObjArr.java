package OOP;

public class ObjArr {
	int age;
	String name;
	
	public ObjArr(int age, String name) {
		this.age = age;     // this(Ŭ���� ��ü�� ����Ŵ): ObjArr �� age
		this.name = name;
	}
}

//-------------------------------------------------

class ObjArrEx {
	public static void main(String[] args) {
		// �ϳ��� ��ü ����
		ObjArr objArr = new ObjArr(12,"������");
		
		// ������ ��ü�� �迭�� ����   objArr2 �迭�� �̸�
		ObjArr[] objArr2 = {new ObjArr(14, "�̼���"), new ObjArr(30, "���̹�"), new ObjArr(14, "����") };
		// Ŭ������[] �迭�� = { 0, 1, 2 }
		
		System.out.println(objArr.name + " " + objArr.age);
		System.out.println(objArr2[0].name + " " + objArr2[0].age);
		System.out.println(objArr2[1].name + " " + objArr2[1].age);
	}
}
