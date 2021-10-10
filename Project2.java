import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Project2 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1){
            System.out.println("usage: java Bank Account filename");
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);

        SavingsAccount.setAnnualInterestRate(1.5);

        Bank bank = new Bank(args[0]); 

        char choice;
        boolean notDone = true; 

        do{
            choice = printMenuAndGetChoice(input);

            switch(choice){
                case 'Q': 
                    notDone = false; 
					          break;
                case 'L': 
                    lookupBalance(bank, input);
                    break;
                case 'P':
                    printInfo(bank, input);
                    break;
                case 'D':
                    deposit(bank, input);
                    break;
                case 'W':
                    withdraw(bank, input);
                    break;
                case 'A':
                    addAccount(bank, input);
                    break;
                case 'R':
                    removeAccount(bank, input);
                    break;
                default:
                    System.out.println("Error: Invalid choice.");
                    break;
            }

        }while(notDone);

        input.close();
    }

    public static char printMenuAndGetChoice(Scanner sc) {
        System.out.println();
        System.out.println("To lookup a balance: L");
        System.out.println("To print information about an account: P");
        System.out.println("To deposit money: D");
        System.out.println("To withdraw money: W");
        System.out.println("To add an account: A");
        System.out.println("To remove an account: R");
        System.out.println("To quit: Q");
        System.out.print("Enter a character (uppercase or lowercase): ");

        char choice = sc.next().toUpperCase().charAt(0);

        System.out.println();
        
        return choice;
    }

    public static void lookupBalance(Bank bank, Scanner input){
        System.out.println("Account number? ");
        int entry = input.nextInt();
        if(bank.getBalance(entry) != null){
            System.out.println("Balance for account number " + entry + " is " + bank.getBalance(entry)); 
        }
        else{
            System.out.println("Error: account number " + entry + " doesn't exist.");
        }

    }

    public static void printInfo(Bank bank, Scanner input) {
        System.out.println("Account number? ");
        int entry = input.nextInt();
        
        String accountInfo = bank.getAccountInfo(entry);

        if(accountInfo == null){
          System.out.println("Error: account number " + entry + " doesn't exist.");
        }
        else{
          System.out.println(accountInfo);
        }
    }

    public static void deposit(Bank bank, Scanner input){
        System.out.println("Account number? ");
        int entry = input.nextInt();
        if(bank.contains(entry)){
          System.out.println("Amount to deposit? $");
          MonetaryValue x = new MonetaryValue(input.nextDouble());

          bank.deposit(entry, x);
        }
        else{
          System.out.println("Error: account number " + entry + " doesn't exist.");
        }
    }

    public static void withdraw(Bank bank, Scanner input){
        System.out.println("Account number? ");
        int entry = input.nextInt();
        if(bank.contains(entry)){
          System.out.println("Amount to withdraw? $");
          MonetaryValue x = new MonetaryValue(input.nextDouble());

          bank.withdraw(entry, x);
        }
        
        else{
          System.out.println("Error: account number " + entry + " doesn't exist.");
        }

    }

    public static void addAccount(Bank bank, Scanner input){
        if(!(bank.isFull())){
          System.out.println("Account number? ");
          int accountNumber = input.nextInt();
          if(!(bank.contains(accountNumber))){
            System.out.println("First name? ");
            String first = input.next();
            System.out.println("Last name? ");
            String last = input.next();
            System.out.println("Account type (C for checking, S for savings)? ");
            char choice = input.next().toUpperCase().charAt(0);
            
            BankAccount cast;

            switch(choice){
              case 'S' :
                SavingsAccount newSAccount = new SavingsAccount(accountNumber, new Name(first, last),                                         new MonetaryValue(0.00));
                cast = (BankAccount)newSAccount;
                bank.add(cast);
                break;
              case 'C' :
                System.out.println("Overdraft limit? ");
                double overdraftLimit = input.nextDouble();
                CheckingAccount newCAccount = new CheckingAccount(accountNumber, new Name(first, last),                                        new MonetaryValue(0.00),                                                                     new MonetaryValue(overdraftLimit));
                cast = (BankAccount)newCAccount;
                bank.add(cast);
                break;
              default :
                System.out.println("Error: invalid account type.");
                break;
            }
          }
          else{
            System.out.println("Error: this account number already exists.");
          }
        }
        else{
          System.out.println("Error: bank is full.");
        }
    }   
    
    public static void removeAccount(Bank bank, Scanner input){
        System.out.println("Account number? ");
        int accountNumber = input.nextInt();
        bank.remove(accountNumber);
    }

}
