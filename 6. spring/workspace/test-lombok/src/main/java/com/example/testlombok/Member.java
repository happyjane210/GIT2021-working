package com.example.testlombok;

import lombok.Data;		// ctrl + 1 : import lombok

@Data
// �Һ� �÷������� java�ڵ带 ������ �� ��(�����Ҷ�)
// �Һ� ������̼ǵ�(@Data)�� �ִ� Ŭ����, �������̽�, �ʵ�, �޼������ Ž��
// getter, setter, equals, hashcode, toString �޼��尡
// ������ �ڵ����� �������
public class Member {
	private int id;
	private String name;
}
