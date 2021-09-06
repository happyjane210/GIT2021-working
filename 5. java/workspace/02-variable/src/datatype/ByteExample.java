package datatype;

public class ByteExample {
	public static void main(String[] args) {
		System.out.println("--byte data range--");
		byte var1 = -128;
		byte var2 = 0;    // 128 은 오류남, 범위에서 벗어나기 때문
		byte var3 = 127;
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
	}

}
