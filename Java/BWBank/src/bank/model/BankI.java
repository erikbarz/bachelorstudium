package bank.model;

import java.util.List;

public interface BankI extends Clearable{
	
	public void removeAccount(int accountNo);

	public Account getAccountByNo(int accountNo);

	public Account getAccountByOwner(String owner);

	double getTotalMoney();

	String getBankName();
	
	void dump();
	
	List<Account> getAllAccounts();

	Account[] getAllAccountsAsArray();
	
	public void addInterest();
}
