import java.util.*;
import java.io.*;

public class Bank {
    private static int CAPACITY = 100000;
    private BankAccount[] accounts;
    private int numAccounts; 


    public Bank() {
        accounts = new BankAccount[CAPACITY];
        numAccounts = 0;
    }

    public Bank(String a) throws FileNotFoundException {
        Scanner input = new Scanner(new File(a));

        accounts = new BankAccount[CAPACITY];

        while(input.hasNext()){
            if(input.next().equals("C")){
                accounts[numAccounts] = CheckingAccount.read(input);
            }

            else{
                accounts[numAccounts] = SavingsAccount.read(input);
            }
            numAccounts++;
        }
    
        input.close();
    }

    @Override
    public String toString(){
        String info = null;
        
        for(int i = 0; i < numAccounts; i++){
            if(accounts[i] instanceof CheckingAccount){
                CheckingAccount xAccount = (CheckingAccount)accounts[i];
                info += xAccount.toString() + " ";
            }
            else{
                SavingsAccount yAccount = (SavingsAccount)accounts[i];
                info += yAccount.toString() + " ";
            }
         
        }
        
        return info;
        
    }

    private int indexOf(int accountNumber) {
        for(int i = 0; i < numAccounts; i++){
            if(accounts[i].getAccountNumber() == accountNumber){
                return i;
            }
        }
        
        return -1;
    } 

    public boolean contains(int accountNumber){
        return indexOf(accountNumber) != -1; 
    }

    public boolean isFull(){
        return numAccounts == CAPACITY; 
    }

    public MonetaryValue getBalance(int accountNumber){
        
        if(contains(accountNumber)){
            System.out.println();
            return accounts[indexOf(accountNumber)].getBalance();  
        }

        return null; 
    }

    public String getAccountInfo(int accountNumber){
        if(contains(accountNumber)){
            if(accounts[indexOf(accountNumber)] instanceof SavingsAccount){
                return ((SavingsAccount)accounts[indexOf(accountNumber)]).toString();
            }

            else{
                return ((CheckingAccount)accounts[indexOf(accountNumber)]).toString() + " " + 0.0;
            }
        }
        return null; 
    }

    public boolean deposit(int accountNumber, MonetaryValue x){
        if(contains(accountNumber)){
            if(x.doubleValue() >= 0.0){
                accounts[indexOf(accountNumber)].deposit(x);
                System.out.println("Deposit was successful. Balance for account number " + accountNumber + " is now " + getBalance(accountNumber));
                return true;
            }
            System.out.println("Error: cannot deposit negative amount of money.");
            return false; 
        }
        System.out.println("Error: account number " + accountNumber +" doesn't exist.");
        return false;
    }

    public boolean withdraw(int accountNumber, MonetaryValue x){
        if(contains(accountNumber)){
            if(x.doubleValue() >= 0.0){

                MonetaryValue availableAmount = new MonetaryValue(accounts[indexOf(accountNumber)].getBalance());

                if(accounts[indexOf(accountNumber)].withdraw(x)){
                  System.out.println("Withdrawal was successful. Balance for account number " + accountNumber + " is now " + getBalance(accountNumber));
                  return true;
                }
                else if(x.doubleValue() >= availableAmount.doubleValue()){
                  System.out.println("Error: insufficient funds.");
                  return false;
                }
            }
            System.out.println("Error: cannot withdraw negative amount of money."); 
            return false; 
        }
        System.out.println("Error: account number " + accountNumber + " doesn't exist.");
        return false;
    }

    public boolean add(BankAccount account){
        if(contains(account.getAccountNumber())){
            System.out.println("Error: this account number already exists.");
            return false; 
        }

        if(numAccounts == CAPACITY){
            System.out.println("Error: bank is full.");
            return false;
        }
        
        accounts[numAccounts] = account;
        numAccounts++;
        System.out.println("Account successfully added.");
        return true;
    }

    public boolean remove(int accountNumber){
        if(!(contains(accountNumber))){
            System.out.println("Error: this account number doesn't exist.");
            return false; 
        }
        
        for(int i = accountNumber; i < numAccounts - 1; i++){
            accounts[i] = accounts[i + 1];
        }
        numAccounts--;
        System.out.println("Account successfully removed.");
        return true;
    }

}
