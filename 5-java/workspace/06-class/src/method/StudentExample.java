package method;

public class StudentExample {
	public static void main(String[] args) {
		
		Student s1 = new Student();
		s1.name = "smith";
		s1.age = 20;
		s1.semester = 1;
		s1.major = "Art";
		s1.printPersonInfo();
		
		Student s3 = new Student("hero", 27, 3, "javaweb");
		s3.printPersonInfo();
		System.out.println(s3.getMajorInfo());
	}
}
