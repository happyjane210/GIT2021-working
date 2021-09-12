package exam;

public class Account {
	//filed
	private String ano;
	private String owner;
	private int balance;
	
	//constructor
	public Account(String ano ,String owner, int balance) {
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
	}
	
	

	public String getAno() {
		return ano;
	}
	
	public String setAno(String ano) {
		this.ano = ano;
		return ano;
	}
	
	
	
	public String getOwner() {
		return owner;
	}
	
	public String setOwner(String owner) {
		this.owner = owner;
		return owner;
	}
	
	
	
	public int getBalance() {
		return balance;
	}
	
	public int setBalance(int balance) {
		return this.balance += balance;
		
	}
	
	
	
	// Method
	public void withdraw(int withd) {
		this.balance -= withd;
	}







	
	
}
