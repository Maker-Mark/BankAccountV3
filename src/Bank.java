

public class Bank {
	private final int MAX_NUM = 50;
	private int numAccts ; // number of accounts
	private BankAccount[] bankAcc;
	private String bankName ;


	//Default constructor for creating bank account   

	public Bank() {
		bankName = "E Corp";
		bankAcc = new BankAccount[MAX_NUM];
		numAccts = 0;
	}

	/*
	 * Method openNewAccount(): 
	 * Input: BankAccount object
	 * Process: Assigns object passed to numAcct spot of bank account arry
	 * Output: Void
	 */
	public void openNewAccount( BankAccount bankAccount) {
		bankAcc[numAccts]=  bankAccount;
		numAccts++;

	}
	/*
	 * Method openNewAccount(): 
	 * Input: Parameters for making a new account with attributes being read in
	 * ie from a file or keyboard.
	 * Process: Makes new account in bankAcct array, at numAccts spot, uses setter methods
	 * to set the details. Nested classes are used and passed to successfully do this.
	 * Output: 
	 */

	public void openNewAccount( int numAccts, int accountNum, double accBal, String first, 
			String last, String socSec, String type) 
	{	
		bankAcc = new BankAccount[numAccts];		
		bankAcc[numAccts].setAccNum(accountNum);
		bankAcc[numAccts].setAccType(type);
		bankAcc[numAccts].setAccBal(accBal);
		bankAcc[numAccts].setAccDet(first,last, socSec);
		numAccts++;
	}

	/*
	 * Method deleteAcct(): 
	 * Input:  index of account to delete
	 * Process: Manipulates BankAccount object array to remove and replace the
	 * deleted account.
	 * Output: none
	 */
	public void deleteAcct(int index) 

	{
		//Efficiently deletes account by cutting last account in array 
		//and replacing the selected account found for deletion.

		bankAcc[index].setAccBal(bankAcc[numAccts -1].getAccBal());
		bankAcc[index].setAccNum(bankAcc[numAccts - 1].getAccNum());
		bankAcc[index].setAccDet(bankAcc[numAccts - 1].getAccDet().
				getNameOnAcc().getFirst(), 
				bankAcc[numAccts - 1].getAccDet()
				.getNameOnAcc().getLast(),
				bankAcc[numAccts- 1].getAccDet().getSocSec());
		bankAcc[index].setAccType(bankAcc[numAccts - 1].getAccType());


		numAccts--;
		//decreases numAccts, the number of accounts.
	}



	public int findAcct( int requestedAccount)
	{
		for (int index = 0; index < numAccts; index++)
			if (bankAcc[index].getAccNum() == requestedAccount)
				return index;// returns index
		return -1;
	}

	public int findAcctSSN(String social ) 
	{

		if(social.length() == 9) {
			for (int index = 0; index < numAccts; index++) {
				if (bankAcc[index].getAccDet().
						getSocSec().equals(social)) {
					return index;
				}
			}
			return -1;
			//flag for no account found

		} else {
			//flag for invalid length
			return -2;
		}
	}

	public BankAccount getAcct(int index) 
	{
		return bankAcc[index];
	}
	public int getNumAcc() {
		return numAccts;
	}
	/**
	 * Does deposits and withdrawals using boolean to distinguish 
	 * if making a deposit, if not, subtracts the amount from acct bal and 
	 * sets the account balance to that amount.
	 */
	public void setAcctInfo(int index, double amount, boolean deposit) 
	{ 
		if (deposit) {
			bankAcc[index].setAccBal((bankAcc[index].getAccBal() + amount ));
		}else {

			bankAcc[index].setAccBal((bankAcc[index].getAccBal() - amount));

		}
	}
}
