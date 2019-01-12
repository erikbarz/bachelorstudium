package bank.model;

public class CheckingAccount extends Account {

	private double overdrawLimit;

	public CheckingAccount(int accountNo, double balance, double overdrawLimit) {
		super(accountNo, balance);
		this.setOverdrawLimit(overdrawLimit);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(int accountNo, String owner, double balance,
			double overdrawLimit) {
		super(accountNo, owner, balance);
		this.setOverdrawLimit(overdrawLimit);
	}

	public void setOverdrawLimit(double overdrawLimit) {
		if (overdrawLimit <= 0 && overdrawLimit>=-100000) {
			this.overdrawLimit = overdrawLimit;
		} else {
			throw new IllegalArgumentException(
					"Wert muss kleiner oder gleich null sein");
		}
	}

	public double getOverdrawLimit() {
		return overdrawLimit;
	}


	@Override
	public void withdraw(double value) {
		// TODO Auto-generated method stub
		if(isLocked()){
			throw new IllegalArgumentException("Account gesperrt");
		}
		if (balance - value >= overdrawLimit) {
			balance -= value;
			log = log + "Sie haben sich " + value + " auszahlen lassen.\n";
		} else {
			throw new IllegalArgumentException("Sie können nicht so viel abheben");
		}
	}

}
