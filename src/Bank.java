import java.io.PrintWriter;
import java.util.Scanner;

public class Bank {
	final int MAX_NUM = 50;
	int numAccts = 0; // number of accounts
	private BankAccount[] bankAcc = new BankAccount[MAX_NUM];
	private String bankName ;
	

	
	public Bank() {
		Bank bank = new Bank();
		bankName ="";
	}
	
	public boolean openNewAccount( int accountNum, double accBal, String first, 
			String last, String socSec, String type) 
	{	
		bankAcc[numAccts]= new BankAccount();
		bankAcc[numAccts].setAccNum(accountNum);
		bankAcc[numAccts].setAccType(type);
		bankAcc[numAccts].setAccBal(accBal);
		bankAcc[numAccts].setAccDet(first,last, socSec);
		numAccts++;
		return true;
		
	}
	
	public boolean deleteAcct() 
	{
		
	}
	
	public int findAcct()
	{
		
	}
	
	public int findAcctSSN() 
	{
		
	}
	
	public BankAccount getAcct() 
	{
		
	}
	
	public void setAcct() 
	{
		
	}
}