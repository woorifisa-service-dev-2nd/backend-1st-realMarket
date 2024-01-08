package dev.kiosk.customer;

public class Customer {
	private String name;
	private String grade;
	private int balance;

	public Customer(String name, String grade, int balance) {
		this.name = name;
		this.grade = grade;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}
	

	public String getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", grade=" + grade + ", balance=" + balance + "]";
	}

}
