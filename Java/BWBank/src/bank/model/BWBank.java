/**
 * 
 */
package bank.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik
 * 
 */
public class BWBank implements BankI, Clearable {

	private int lastAccountNo = 1000;
	private final String name;
	public final List<Account> accountList = new ArrayList();

	public BWBank(String name) {
		this.name = name;
		load();
	}

	public int getLastAccountNo() {

		return lastAccountNo;
	}

	public void setLastAccountNo(int lastAccountNo) {
		this.lastAccountNo = lastAccountNo;
	}

	public String getName() {
		return name;
	}

//	public SavingsAccount createSavingsAccount(String owner, double balance,
//			double interest) {
//		int accNo = nextId();
//		SavingsAccount acc = new SavingsAccount(accNo, owner, balance, interest);
//		accountList.add(acc);
//		return acc;
//	}

//	public CheckingAccount createCheckingAccount(String owner, double balance,
//			double overdrawLimit) {
//		int accNo = nextId();
//		CheckingAccount acc = new CheckingAccount(accNo, owner, balance,
//				overdrawLimit);
//		accountList.add(acc);
//		return acc;
//	}
	
	private Account createCheckingAccount(String owner, double balance,
			double overdrawLimit) {
		return addAccount(new CheckingAccount(nextId(),owner, balance, overdrawLimit));
	}
	
	private Account createSavingsAccount(String owner, double balance,
			double interest) {
		return addAccount(new SavingsAccount(nextId(),owner, balance, interest));
	}

	private Account addAccount(Account account){
		accountList.add(account);
		return account;
	}
	
	private int nextId(){
		return ++lastAccountNo;
	}
	
	public void removeAccount(int accountNo) {
		accountList.remove(accountList.indexOf(getAccountByNo(accountNo)));
	}

	public Account getAccountByNo(int accountNo) {
		// TODO Auto-generated method stub
		for (Account account : accountList) {
			if (account.getAccountNo() == accountNo) {
				System.out.println(account);
				return account;
			}
		}
		return null;
	}

	public Account getAccountByOwner(String owner) {
		// TODO Auto-generated method stub
		for (Account account : accountList) {
			if (account.getOwner().equals(owner)) {
				System.out.println(account);
				return account;
			}
		}
		return null;

	}

	public double getTotalMoney() {
		double total = 0;
		for (Account account : accountList) {
			total += account.getBalance();
		}
		// System.out.println("total money = " +total);
		return total;
	}

	public String getBankName() {
		// TODO Auto-generated method stub
		return name;
	}

	private void load() {
		createSavingsAccount("Ich", 1000, 0.02);
		createSavingsAccount("Du", 6000, 0.02);
		createSavingsAccount("Er", 100, 0.02);
		createSavingsAccount("Sie", 400, 0.02);
		createSavingsAccount("Es", 2500, 0.02);
		createCheckingAccount("Wir", 10000, -2000);
		createCheckingAccount("Ihr", 5000, -1000);
		createCheckingAccount("Sie", 20000, -4000);
	}

	public void dump() {
		System.out.println("Bank " + getBankName() + " has " + getTotalMoney()
				+ " Euro");
		for (Account account : accountList) {
			System.out.println(account);
		}

	}

	public List<Account> getAllAccounts() {
		List listCopy = new ArrayList();
		listCopy.addAll(accountList);
		return listCopy;

	}

	public Account[] getAllAccountsAsArray() {
		return accountList.toArray(new Account[accountList.size()]);
	}

	public void clear() {
		for (Account account : accountList) {
			account.clear();
		}
	}

	public void addInterest() {
		for (Account account : accountList) {
			if (account instanceof SavingsAccount) {
				((SavingsAccount) account).addInterest();
			}
		}
	}
	
	
}
