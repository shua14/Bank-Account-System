import java.util.*;

public class CheckingAccount extends BankAccount {
    private MonetaryValue overdraftLimit;

    public CheckingAccount() {
        super(0, new Name(), new MonetaryValue());
        this.overdraftLimit = new MonetaryValue();
    }

    public CheckingAccount(int accountNumber, Name name, MonetaryValue balance, MonetaryValue overdraftLimit) {
        super(accountNumber, name, balance);
        this.overdraftLimit = overdraftLimit;
    }
    
    public CheckingAccount(CheckingAccount other) {
        super(other);
        this.overdraftLimit = other.overdraftLimit;  
    }

    @Override
    public boolean equals(Object a){
        if(a instanceof CheckingAccount){
            CheckingAccount other = (CheckingAccount)a;
            return super.equals(other) && this.overdraftLimit.equals(other.overdraftLimit);
        }
        
        return false;
    }

    @Override
    public String toString(){
        return super.toString() + " " + overdraftLimit;
    }

    public MonetaryValue availableAmount() {
        return new MonetaryValue(getBalance().doubleValue() + overdraftLimit.doubleValue());
    }   

    @Override
    public boolean withdraw(MonetaryValue a){
        if((!(a.isNegative())) && a.doubleValue() <= balance.doubleValue() + overdraftLimit.doubleValue()){
            balance.subtract(a);
            return true;
        }
        
        return false;
    }

    public static CheckingAccount read(Scanner sc){
        if(!(sc.hasNext())){
            return null;
        }       
       
        return new CheckingAccount(sc.nextInt(), new Name(sc.next(), sc.next()), new MonetaryValue(sc.nextDouble()), new MonetaryValue(sc.nextDouble()));   
    }

}
