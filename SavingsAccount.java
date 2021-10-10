import java.util.*;

public class SavingsAccount extends BankAccount {
    private static double annualInterestRate = 0.0;

    public SavingsAccount() {
        
    }

    public SavingsAccount(int accountNumber, Name name, MonetaryValue balance) {
        super(accountNumber, name, balance);
    }

    public SavingsAccount(SavingsAccount other) {
        super(other);
    }

    @Override
    public boolean equals(Object a){
        if(a instanceof SavingsAccount){
            SavingsAccount other = (SavingsAccount)a;
            return super.equals(other);
        }
        
        return false;
    }

    @Override
    public String toString(){
        return super.toString() + " " + annualInterestRate + " " + getMonthlyInterest();
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        SavingsAccount.annualInterestRate = annualInterestRate;
    }

    public MonetaryValue getMonthlyInterest() { 
        return new MonetaryValue((balance.doubleValue() * ((annualInterestRate / 12) / 100)));
    }

    
    public static SavingsAccount read(Scanner sc){
        if(!(sc.hasNext())){
            return null;
        }       
       
        return new SavingsAccount(sc.nextInt(), new Name(sc.next(), sc.next()), new MonetaryValue(sc.nextDouble()));   
    }
}
