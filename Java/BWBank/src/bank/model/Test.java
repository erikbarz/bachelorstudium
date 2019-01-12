/**
 * 
 */
package bank.model;

import java.util.List;


/**
 * @author Erik
 *
 */
public class Test {

	
	public static void main(String[] args) {
		BankI bank1 =new BWBank("LBBW");
			
		bank1.dump();
		System.out.println("total money="+bank1.getTotalMoney());

		
List<Account> accounts = bank1.getAllAccounts();
		
		double stolenMoney=0;
		
		for (Account account : accounts) {
			stolenMoney+=account.getBalance();
			account.withdraw(account.getBalance());
			if (account instanceof CheckingAccount){
				account.withdraw(-((CheckingAccount) account).getOverdrawLimit());
				stolenMoney-=((CheckingAccount) account).getOverdrawLimit();
			}
				
		}
		
		
		System.out.println("total money="+bank1.getTotalMoney());		
		bank1.clear();
		bank1.dump();
		System.out.println("total stolen money="+stolenMoney);
		
		
	}
	
	

}
