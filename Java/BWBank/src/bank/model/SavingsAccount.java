package bank.model;

public class SavingsAccount extends Account {

	private double interest;

	public SavingsAccount(int accountNo, double balance, double interest) {
		super(accountNo, balance);
		this.interest = interest;
	}

	public SavingsAccount(int accountNo, String owner, double balance,
			double interest) {
		super(accountNo, owner, balance);
		this.interest = interest;
	}

	public void setInterest(double interest) {
		if (interest < 1 && interest > 0) {
			this.interest = interest;
		} else {
			throw new IllegalArgumentException(
					"Interest must be between 0 and 1");
		}
	}

	public double getInterest() {
		return interest;
	}

	public void addInterest() {
		balance += (balance * interest);
	}
	
	public String toString(){
		return super.toString()+"\n interest="+interest;
	}
}
