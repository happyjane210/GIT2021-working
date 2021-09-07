package exercise;

public class PrinterExample {
	public static void main(String[] args) {
		
		Printer.println(10);
		Printer.println(true);
		Printer.println(5.7);
		Printer.println("홍길동");
		
		Printer.println1("-----------");
		
		Printer.println("메시지", 1);
		Printer.println("알림 메시지", 1, "!!");
	}
}
