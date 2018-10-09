//NAME: Mark Goldstein
/**
 * 
 * @author Mark Goldstein
 * @version 0.02
 * @date 9/26/2018
 * this is a test of changes
 * 
 */
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BankAcctV3_Main {



	/*
	 * Method (): 
	 * Input: 
	 * Process: 
	 * Output: 
	 */

	public static void main(String[] args) throws IOException {
		// constant definitions

		char choice; // menu item selected
		boolean not_done = true; // loop control flag

		Bank bank = new Bank();// default bank object creation

		// Open input test cases file
		//File testFile = new File("myinput.txt");
		File testFile = new File("mytestcases.txt");

		// Create Scanner object
		Scanner kybd = new Scanner(testFile);
		//Scanner kybd = new Scanner(System.in);

		// open the output file
		//PrintWriter outFile = new PrintWriter("myoutput.txt");
		PrintWriter outFile = new PrintWriter(System.out);

		/* fill and print initial database */
		readAccts(bank);
		printAccts(bank, outFile);
		/* prompts for a transaction and then calls */
		/* on functions to process the requested transaction */
		do {
			menu();
			choice = kybd.next().charAt(0);
			switch (choice) {
			case 'q':
			case 'Q':
				not_done = false;
				outFile.println("Final Database of Accounts:\n");
				printAccts(bank, outFile);
				break;
			case 'b':
			case 'B':
				balance(bank, outFile, kybd);
				break;
			case 'i':
			case 'I':
				accountInfo(bank, outFile, kybd);
				break;
			case 'd':
			case 'D':
				deposit(bank, outFile, kybd);
				break;
			case 'w':
			case 'W':
				withdrawal(bank, outFile, kybd);
				break;
			case 'n':
			case 'N':
				newAcct(bank, outFile, kybd);
				break;
			case 'x':
			case 'X':
				deleteAcct(bank, outFile, kybd);
				break;
			default:
				outFile.println("Error: " + choice + 
						" is an invalid selection -  try again");
				outFile.println();
				outFile.flush();
				break;
			}
			// give user a chance to look at output before printing menu
			//pause(kybd);
		} while (not_done);

		// close the output file
		outFile.close();

		// close the test cases input file
		kybd.close();

		System.out.println();
		System.out.println("The program is terminating");
	}

	/*
	 * Method readAccts(): 
	 * Input: "myinput.txt", which uses a constructor to initialize
	 * the data members of a new account.
	 * the  maxAccts - maximum number of active accounts allowed 
	 * 
	 * Process: Reads the initial database of accounts, balances,
	 * social security number and account type into database.
	 * 
	 * Output: Fills in the initial BankAccount bankAcc
	 * array and returns the number of active accounts
	 */

	public static int readAccts(Bank bank) 
			throws IOException {
		// Open database input file
		// Create File object for initial database
		File dbFile = new File("myinput.txt");
		Scanner sc = new Scanner(dbFile);

		//Strings to read in and assign account info local variable
		String line;
		int count = 0;
		double bal;

		while (sc.hasNext()) {

			line = sc.nextLine();
			StringTokenizer lineTok = new StringTokenizer(line);	

			//sets depositor,name and bank acc in one step sends name and makes nested name with used params
			BankAccount bankAcc = new BankAccount(lineTok.nextToken(), 
					lineTok.nextToken(),lineTok.nextToken(), Integer.parseInt(lineTok.nextToken()), 
					lineTok.nextToken(), Double.parseDouble(lineTok.nextToken()));

			bank.openNewAccount(bankAcc);

			System.out.println(bankAcc.getAccType());
		}

		// closes the input file
		sc.close();

		// return the account number count
		return count;
	}

	/*
	 * Method printAccts(): 
	 * Input: BankAccount[] array "bankAcc" - array of accounts
	 * numAccts - number of active accounts
	 * outFile - reference to the output file 
	 * Process: Prints the database of bank accounts and
	 * their respective attributes.
	 * 
	 * Output: Prints the database of account
	 */

	public static void printAccts(Bank bank, PrintWriter outFile) {

		Name myName = new Name();
		//		Depositor myDepositor = new Depositor();
		BankAccount myBankAcc = new BankAccount();

		outFile.println("\t\t\t\t\t\tDatabase of Bank Accounts\n");
		outFile.printf("First \t   Last\t\t    Social Security#    Account#"
				+ "\tAccount Type   Balance \n");
		outFile.println("/----------------------------------------------"
				+ "----------------------------------\\");

		for (int index = 0; index < bank.getNumAcc(); index++) {
			outFile.println();
			myBankAcc = bank.getAcct(index);
			myName = myBankAcc.getAccDet().getNameOnAcc();
			outFile.printf("%-11s", myName.getFirst());
			outFile.printf("%-16s", myName.getLast());
			outFile.printf("%-17s",myBankAcc.getAccDet().getSocSec());

			outFile.printf("%-13s", myBankAcc.getAccNum());
			outFile.printf("%-14s", myBankAcc.getAccType());
			outFile.printf("$%9.2f", myBankAcc.getAccBal());
			outFile.println();

		}
		outFile.println("\\----------------------------------------------"
				+ "----------------------------------/");
		outFile.println();
		// Flushes the output file
		outFile.flush();
	}

	/*
	 * Method menu(): 
	 * Input: none 
	 * 
	 * Process: Prints the menu of transaction choices
	 * 
	 * Output: Prints the menu of transaction choices
	 */

	public static void menu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     W -- Withdrawal");
		System.out.println("\t     D -- Deposit");
		System.out.println("\t     N -- New Account");
		System.out.println("\t     B -- Balance Inquiry");
		System.out.println("\t     I -- Account Information");
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println();
		System.out.print("\tEnter your selection: ");

	}

	//	/*
	//	 * Method findAcct(): Input:BankAccount[] array "bankAcc"
	//	 * requestedAccount - requested account
	//	 * 
	//	 * Process: Performs a linear search on the bankAcc array for
	//	 * the requested account. 
	//	 * 
	//	 * Output: If found, the index of the requested account is
	//	 * returned Otherwise, returns -1.
	//	 */

	/*
	 * Method accountInfo():
	 * Input:Takes a BankAccount object from the array the number of accounts,
	 * scanner and outfile.
	 * 
	 * Process: Allows user to query database by inputting
	 * the accounts' associated social security number.If the number is incorrect
	 * or invalid, an error message occurs.
	 * 
	 * Output:Prints the account information associated with the inputed social
	 * security number.
	 */

	public static void accountInfo(Bank bank, 
			PrintWriter outFile, Scanner kybd) {
		//Makes temp string to ensure validity
		String tempInput;
		int temp = 0 ;// temporary index that will be tested for validity.
		outFile.println();
		System.out.println("Enter Social Secial Security Number"
				+ " to Get Account Information");
		outFile.flush();
		if (kybd.hasNextInt()) //validates input as integer
		{
			tempInput = kybd.next();				
			temp = bank.findAcctSSN(tempInput);
			if(temp > -1) {

				outFile.println("Transaction Requested: Account Information");
				outFile.print("Sucessfully found account linked to SS# \"" 
						+ tempInput+ "\" below:\n\n");
				outFile.printf("First \t   Last\t\t    Social Security#    Account#"
						+ "\tAccount Type   Balance\n");
				outFile.println("/----------------------------------------------"
						+ "----------------------------------\\");
				outFile.printf("%-11s", bank.getAcct((temp)).
						getAccDet().getNameOnAcc()
						.getFirst());
				outFile.printf("%-16s", bank.getAcct(temp).getAccDet().getNameOnAcc()
						.getLast());
				outFile.printf("%-17s", bank.getAcct(temp).getAccDet().getSocSec());
				outFile.printf("%-13s", bank.getAcct(temp).getAccNum());
				outFile.printf("%-14s", bank.getAcct(temp).getAccType());
				outFile.printf("$%9.2f \n", bank.getAcct(temp).getAccBal());
				outFile.println("\\----------------------------------------------"
						+ "----------------------------------/");
				outFile.flush();

			}
			else if( temp == -1)
			{//Error message for valid but not found
				outFile.println("Transaction Requested: Account Information");

				outFile.print("Error: No account with Social Security#:\""
						+ tempInput + "\" found.");
			}
			else if (temp ==  -2)
			{ //Error for invalid length
				outFile.println("Transaction Requested: Account Information");
				outFile.print("Error: Social Security "
						+ "numbers must be 9 integers long!\n");
			}
		}
		else 
		{//Error for invalid input. ie letters
			tempInput = kybd.next();
			outFile.println("Transaction Requested: Account Information");
			outFile.print("Error: \""+tempInput + "\" is an invalid entry."
					+ "\nSocial Security numbers"
					+ " must be 9 consecutive integers.\n");	
		}
		outFile.println();
		outFile.flush();
	}

	/*
	 * Method balance(): 
	 * Input: Bank object
	 * outFile - reference to output file kybd - reference to the "test cases" input
	 * file .
	 * 
	 * Process: Prompts for the requested account Calls findAcct() method from bank class
	 * to see if the account exists If the account exists, the balance is printed Otherwise,
	 * an error message is printed .
	 * 
	 * Output: If the account exists, the balance is
	 * printed Otherwise, an error message is printed
	 */

	public static void balance(Bank bank, 
			PrintWriter outFile, Scanner kybd) 
	{
		int requestedAccount;
		int index;
		String accLength; // Sets up new account as string to ensure validity
		System.out.println();
		System.out.println("Enter the account number: ");
		if (kybd.hasNextInt()) {
			accLength = kybd.next();
			if (accLength.length() != 6 || Integer.parseInt(accLength) < 100000) 
			{
				outFile.println("Transaction Requested: Balance");
				outFile.printf("Error: Account number entered invalid!" + 
						"\nAccount numbers must be a 6-digit integer "
						+ "\nbetween 100000 and 999999.\n");

			} else {
				requestedAccount = Integer.parseInt(accLength);
				// call findAcct to search if requestedAccount exists

				index = bank.findAcct( requestedAccount);
				if (index == -1) // invalid account
				{
					outFile.println("Transaction Requested: Balance Inquiry");
					outFile.println("Error: Account number " + requestedAccount 
							+ " does not exist.");
				} 
				else // valid account
				{
					outFile.println("Transaction Requested: Balance Inquiry");
					outFile.println("Account Number: " + requestedAccount);
					outFile.printf("Current Balance: $%.2f",  bank.getAcct(index).getAccBal());
					outFile.println();
				}
			}
		} 
		else 
		{ 
			// Message for invalid account number entered
			accLength = kybd.next();
			outFile.println("Transaction Requested: Balance");
			outFile.printf("Error: Account number entered invalid!"
					+ "\nAccount numbers must be a 6-digit integer "
					+ "\nbetween 100000 and 999999.\n");
		}
		outFile.println();
		outFile.flush(); // flush the output buffer

	}

	/*
	 * Method deposit(): 
	 * Input: Bank object
	 *  - reference to the "test cases" input file
	 * 
	 * Process: Prompts for the requested account Calls Bank object findacct() to see if the
	 * account exists If the account exists, prompts for the amount to deposit If
	 * the amount is valid, it makes the deposit and prints the new balance
	 * Otherwise, an error message is printed. 
	 * Output: For a valid deposit, the deposit transaction is printed 
	 * Otherwise, an error message is printed
	 */

	public static void deposit(Bank bank, 
			PrintWriter outFile, Scanner kybd) 
	{
		int requestedAccount;
		int index;
		double amountToDeposit;
		String accLength; // Sets up account as string to ensure validity

		System.out.println();
		System.out.print("Enter the account number: ");
		if (kybd.hasNextInt()) 
		{
			accLength = kybd.next();

			if (accLength.length() != 6 || Integer.parseInt(accLength) < 100000) 
			{
				outFile.println("Transaction Requested: Deposit");
				outFile.printf("Error: Account number entered invalid!"
						+ "\nAccount numbers must be a 6-digit integer "
						+ "\nbetween 100000 and 999999.\n");
			}
			// read-in the account number
			requestedAccount = Integer.parseInt(accLength); 

			// call findAcct bank method to search if requestedAccount exists
			index = bank.findAcct(requestedAccount);

			if (index == -1) // invalid account
			{
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Error: Account number " + 
						requestedAccount + " does not exist.");
			} 
			else // valid account
			{
				System.out.println("Enter amount to deposit: "); 
				if (kybd.hasNextDouble()) {
					amountToDeposit = kybd.nextDouble(); // read-in the amount to deposit

					if (amountToDeposit <= 0.00) {
						// invalid amount to deposit
						outFile.println("Transaction Requested: Deposit");
						outFile.println("Account Number: " + requestedAccount);
						outFile.printf("Error: $%.2f is an invalid "
								+ "amount", amountToDeposit);
						outFile.println();
					} 
					else 
					{
						outFile.println("Transaction Requested: Deposit");
						outFile.println("Account Number: " + requestedAccount);
						outFile.printf("Old Balance: $%.2f", bank.getAcct(index).getAccBal());
						outFile.println();
						outFile.println("Amount to Deposit: "
								+ "$" + amountToDeposit);
						// make the deposit
						boolean deposit = true;
						bank.setAcctInfo(index, amountToDeposit, deposit);
						outFile.printf("New Balance: $%.2f", bank.getAcct(index).getAccBal());
						outFile.println();
					}

				} 
				else 
				{ // invalid amount to deposit
					accLength = kybd.next();
					outFile.println("Transaction Requested: Deposit");
					outFile.println("Account Number: " + requestedAccount);
					outFile.printf("Error: Deposit entered invalid!"
							+ " Deposit must be a positive integer or double.");
					outFile.println();
				}
			}
		} 
		else 
		{ // Message for invalid account number entered
			accLength = kybd.next();
			outFile.println("Transaction Requested: Deposit");
			outFile.printf("Error: Account number entered invalid!"
					+ "\nAccount numbers must be a 6-digit integer "
					+ "\nbetween 100000 and 999999.\n");
		}
		outFile.println();
		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method withdrawal(): 
	 * Input: Account number followed by amount to withdraw
	 * 
	 * Process: Ensures account exists and removes amount selected, otherwise
	 * displays error message. 
	 * 
	 * Output: Lists old balance, then new balance successfully withdrawn.
	 */

	public static void withdrawal(Bank bank, 
			PrintWriter outFile, Scanner kybd) 
	{
		int requestedAccount;
		int index;
		double amountToWithdraw;
		String accLength; // Sets up new account as string to ensure validity
		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number

		if (kybd.hasNextInt()) 
		{ // Validates input
			accLength = kybd.next(); // Sets validated integer as next string for testing
			if (accLength.length() != 6 || Integer.parseInt(accLength) < 100000) 
			{
				outFile.println("Transaction Requested: Withdrawal");
				outFile.printf("Error: Account number entered invalid!"
						+ "\nAccount numbers must be a 6-digit integer "
						+ "\nbetween 100000 and 999999.\n");

			} 
			else 
			{
				// Calls findAcct to search if requestedAccount exists
				requestedAccount = Integer.parseInt(accLength);
				index = bank.findAcct(requestedAccount);
				if (index == -1) 
				{ // Signals invalid account 
					outFile.println("Transaction Requested: Withdrawal");
					outFile.println("Error: Account"
							+ " number " + requestedAccount + " does not exist");
				} 
				else 
				{ // Signals valid account
					// Prompts for withdrawal
					System.out.print("Enter amount to Withdrawal: "); 
					// Ensures correct input
					if (kybd.hasNextDouble()) {
						amountToWithdraw = kybd.nextDouble();

						// Criteria for invalid deposit request
						if (amountToWithdraw <= 0.00) {
							outFile.println("Transaction Requested: Withdrawal");
							outFile.println("Account Number: " + requestedAccount);
							outFile.printf("Error: $%.2f is an"
									+ " invalid amount",amountToWithdraw);
							outFile.println();

						} 
						else if (bank.getAcct(index).getAccBal() < amountToWithdraw) 
						{// User trying to withdraw more than they have
							outFile.println("Transaction Requested: Withdrawal");
							outFile.println("Account Number: " + requestedAccount);
							outFile.printf("Error: Insufficient funds!");
							outFile.println();

						}
						else 
						{ // Making successful withdrawal
							outFile.println("Transaction Requested: Withdrawal");
							outFile.println("Account Number: " + requestedAccount);
							outFile.printf("Old Balance: "
									+ "$%.2f", bank.getAcct(index).getAccBal());
							outFile.println();
							outFile.println("Amount to Withdrawal:"
									+ " $" + amountToWithdraw);
							boolean deposit = false;//Telling bank 
							bank.setAcctInfo(index, amountToWithdraw, deposit);

							outFile.printf("New Balance: "
									+ "$%.2f", bank.getAcct(index).getAccBal());
							outFile.println();
						}
					}
					else 
					{
						accLength = kybd.next();
						outFile.println("Transaction Requested: Withdrawal");
						outFile.println("Error: Withdrawal must be numbers!");
					}
				}
			}
		} 
		else
		{ // Message for invalid account number entered
			accLength = kybd.next();
			outFile.println("Transaction Requested: Withdrawal");
			outFile.printf("Error: Account number entered invalid!"
					+ "\nAccount numbers must be a 6-digit integer "
					+ "\nbetween 100000 and 999999.\n");
		}
		outFile.println();
		outFile.flush();

	}

	/*
	 * Method newAcct(): 
	 * Input:New Account number
	 *
	 * Process:Uses find account to check if account already exists. If account
	 * number is valid but taken, teller is notified. If account number is not
	 * valid, error message and instructions displayed. Once a valid new account
	 * number is entered, the social security number and account type is
	 * checked and assigned to the new account object being created.
	 *
	 * Output: Displays the successful creation of the new account with paired
	 * social security number.
	 *
	 */

	public static int newAcct(Bank bank, 
			PrintWriter outFile, Scanner kybd) 
	{
		int accountNew , accountNum,  index;
		double accountBal;
		String accLength;
		// Sets up new account as string to ensure validity
		char choice;
		String temp = null,
				socSec,
				first, 
				last, 
				type = null;
		System.out.println("/nEnter New Account Number:");

		// Checks read-in the account number
		if (kybd.hasNextInt()) { // Validates input
			accLength = kybd.next();

			if (accLength.length() != 6 || Integer.parseInt(accLength) < 100000) 
			{
				outFile.println("Transaction Requested: New Account");
				outFile.printf("Error: Account number entered invalid!"
						+ "\nAccount numbers must be a 6-digit integer "
						+ "\nbetween 100000 and 999999.\n");
				outFile.flush();
				return 1;
			} 
			else 
			{
				accountNew = Integer.parseInt(accLength); //Sets account number
				// Calls findAcct to search if requestedAccount exists
				//Should this be assigned to index?? If yes, do it for others
				index = bank.findAcct( accountNew);
				if (index != -1) // invalid: account in-use
				{
					outFile.println("Transaction Requested: Create New Account");
					outFile.println("Error: Account number " + accountNew + 
							" is already in use.\n");
					outFile.flush();
					return 2;
				}
				//Setting values for first and last name
				System.out.println("Enter your first name:");
				first = kybd.next();
				System.out.println("Enter your last name:");
				last = kybd.next();
				System.out.println("Enter Social Security number:");
				outFile.flush();
				//Ensures validity of social security number
				if(kybd.hasNextInt() ) {
					temp = kybd.next();
					if(temp.length()!= 9) {
						outFile.println("ERROR:Invalid entry."
								+ " Social Security number "+ temp +
								" must be 9 digits long\n");
						outFile.flush();
						return 3;
					}
				}
			}
			int numAccLoc = bank.getNumAcc();
			//Checks to see if account with valid social security number exists
			for(int i = 0; i < bank.getNumAcc(); i++) {
				if(bank.getAcct(i).getAccDet().getSocSec().equalsIgnoreCase(temp)) 
				{
					outFile.println("ERROR: Acccount with social security number: "+
							temp + " already exisits\n");
					outFile.flush();
					return 4;
				}
			}

			socSec = temp;//setting socSec to right account
			//Making new Bank account object
			//Call new account here?
			//Prompt for user to choose an account type
			System.out.println("Select an account type from following options:");
			System.out.println();
			System.out.println("\t****************************");
			System.out.println("\t    List of Choices         ");
			System.out.println("\t****************************");
			System.out.println("\t     C -- Checking");
			System.out.println("\t     S -- Savings");
			System.out.println("\t     D -- CD ");
			System.out.println();

			//Determines what user selects
			choice = kybd.next().charAt(0);
			switch (choice) {
			case 'c':
			case 'C':
				type = "Checkings";
				break;
			case 's':
			case 'S':
				type = "Savings";
				break;
			case 'D':
			case 'd':
				type = "CD";
				break;
			default:
				outFile.println("Error: " + choice + 
						" is an invalid selection -  try again");
				outFile.println();
				outFile.flush();
				break;
			}
			//prompts for initial deposit
			System.out.println("Enter your inital opening deposit: ");
			accountBal = (kybd.nextDouble());
			accountNum = accountNew;	

			BankAccount bankAcc = new BankAccount( first, last, socSec, accountNum, type, accountBal);
			//Calling open new account
			bank.openNewAccount(bankAcc);	
			outFile.println("Transaction Requested: Create New Account");
			outFile.printf("New "+ bank.getAcct(numAccLoc).getAccType() +
					" Account with account number \"" + accountNew 
					+ "\" \nwith social security number \"" + socSec+ "\" "
					+ "\nwas created and has a "
					+ "balance of $" );
			outFile.printf("%.2f \n", bank.getAcct(numAccLoc).getAccBal());

			outFile.flush();	
		}
		else 
		{ // Message for invalid account number entered
			accLength = kybd.next();
			outFile.println("Transaction Requested: New Account");
			outFile.printf("Error: Account number entered invalid!"
					+ "\nAccount numbers must be a 6-digit integer "
					+ "\nbetween 100000 and 999999.\n");
			outFile.flush();
		}
		outFile.println();
		outFile.flush();
		return 4;
	}

	/*
	 * Method deleteAcct(): 
	 * Input: Existing account number
	 *
	 * Process: Ensures account balance is at 0.00 and delete account. 
	 * Makes sure account info is valid, and that
	 * the balance of the account is at 0. 
	 * 
	 * Output: If account is invalid or not empty
	 * displays the amount needed to withdraw to complete the 
	 * transaction.
	 */

	public static void deleteAcct(Bank bank, 
			PrintWriter outFile, Scanner kybd) {
		int delAcct, index = 0;
		String delTemp, accLength; // Setting up accounts as strings to test validity
		System.out.println("Enter Account Number for deletion:");
		if (kybd.hasNextInt()) {
			accLength = kybd.next();
			delAcct = Integer.parseInt(accLength);

			delTemp = Integer.toString(delAcct);
			index = bank.findAcct(delAcct);
			if (index == -1) {
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Error: Account entered does not exist!");
			} 
			else if (Integer.parseInt(delTemp) <= 100000 || delTemp.length() != 6) 
			{
				outFile.println("Transaction Requested: Delete Account");
				outFile.printf("Error: Account number entered invalid!"
						+ "\nAccount numbers must be a 6-digit integer "
						+ "\nbetween 100000 and 999999.\n\n");
			} 
			else if (bank.getAcct(bank.findAcct(delAcct)).getAccBal() != 0.00) 
			{
				outFile.println("Transaction Requested: Delete Account");
				outFile.printf("Error: Account "+delTemp+" is not empty.\nRemove $");
				outFile.printf("%.2f %s", bank.getAcct(bank.findAcct(delAcct)).getAccBal(), 
						"from account before deleting.\n");
			} 
			else 
			{ 
				bank.deleteAcct(index);
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Successfully deleted account number: " + delAcct);
			}
		} 
		else 
		{ //Error message for invalid account entered
			accLength = kybd.next();
			outFile.println("Transaction Requested: New Account");
			outFile.printf("Error: Account number entered invalid!"
					+ "\nAccount numbers must be a 6-digit integer "
					+ "\nbetween 100000 and 999999.\n\n");
		}
		outFile.println();
		outFile.flush();

	}
	/* Method pause() */
	public static void pause(Scanner keyboard) {
		String tempstr;
		System.out.println();
		System.out.print("press ENTER to continue");
		tempstr = keyboard.nextLine(); // flush previous ENTER
		tempstr = keyboard.nextLine(); // wait for ENTER
	}
}
