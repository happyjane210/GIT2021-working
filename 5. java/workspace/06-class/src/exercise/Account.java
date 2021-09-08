package exercise;

public class Account {

	private int balance;
	private final int MIN_BALANCE = 0;
	private final int MAX_BALANCE = 0;

	public int getBalance() {
		return 0;
	}

	public void setBalance(int balance) {
		if (balance < MIN_BALANCE || balance > MAX_BALANCE) {
			return;
		}
		this.balance = balance;
	}
	
	
}
