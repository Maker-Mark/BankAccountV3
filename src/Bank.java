import java.io.PrintWriter;
import java.util.Scanner;

public class Bank {
	final int MAX_NUM = 50;
	
	int numAccts ; // number of accounts
	private BankAccount[] bankAcc;
	private String bankName ;
	

	
	public Bank() {
//		Bank bank = new Bank();
		bankName ="";
		bankAcc = new BankAccount[MAX_NUM];
	}
	
	
	public void openNewAccount( BankAccount bankAccount) {
		bankAcc[numAccts]=  bankAccount;
		numAccts++;
		
	}
	
	public  boolean openNewAccount( int numAccts, int accountNum, double accBal, String first, 
			String last, String socSec, String type) 
	{	
		bankAcc = new BankAccount[numAccts];		
		bankAcc[numAccts].setAccNum(accountNum);
		bankAcc[numAccts].setAccType(type);
		bankAcc[numAccts].setAccBal(accBal);
		bankAcc[numAccts].setAccDet(first,last, socSec);
		numAccts++;
		return true;
		
	}
	
//	public boolean deleteAcct() 
//	{
//		
//	}
////	
//	public int findAcct()
//	{
//		
//	}
//	//Data member
//	//another one
//	public int findAcctSSN() 
//	{
//		
//	}
//	
	public BankAccount getAcctInfo(int index) 
	{
		return bankAcc[index];
	}
	public int getNumAcc() {
		return numAccts;
	}
	public void setAcct() 
	{
		
	}
}
