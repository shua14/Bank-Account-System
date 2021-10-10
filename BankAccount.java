public class BankAccount {
    private int accountNumber;
    private Name name;
    protected MonetaryValue balance;


    public BankAccount(){
        this(0, new Name(), new MonetaryValue());
    }

    public BankAccount(int accountNumber, Name name, MonetaryValue balance){
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public BankAccount(BankAccount other){
        this.accountNumber = other.accountNumber;
        this.name = other.name;
        this.balance = other.balance;
    }

    @Override
    public boolean equals(Object a){
        if(a instanceof BankAccount){
            BankAccount other = (BankAccount)a;
            return this.accountNumber == other.accountNumber && this.name.equals(other.name) && this.balance.equals(other.balance);
        }
        
        return false;
    }

    @Override
    public String toString(){
        return accountNumber + " " + name + " " + balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public MonetaryValue getBalance() {
        return this.balance;
    }

    public boolean deposit(MonetaryValue a){
        if(!(a.isNegative())){
            balance.add(a);
            return true;
        }
        
        return false; 
    }

    public boolean withdraw(MonetaryValue a){
        if((!(a.isNegative())) && a.doubleValue() <= balance.doubleValue()){
            balance.subtract(a);
            return true;
        }
    
        return false;
    }

}
