import java.util.Scanner;

public class MonetaryValue {
    private int cents;

    public MonetaryValue(){
        this(0.00);
    }

    public MonetaryValue(double dollars){
        cents = (int)(dollars * 100);
    }

    public MonetaryValue(MonetaryValue other){
        this.cents = other.cents;    
    }

    @Override
    public boolean equals(Object a){
        if(a instanceof MonetaryValue){
            MonetaryValue other = (MonetaryValue)a;
            return this.cents == other.cents;
        }
        
        return false;
    }

    @Override
    public String toString(){
        return String.format("%.2f", (double)(cents) / 100);
    }

    public boolean isLessThan(MonetaryValue a){
        return this.cents < a.cents;
    }

    public boolean isGreaterThan(MonetaryValue a){
        return this.cents > a.cents;
    }

    public boolean isNegative(){
        return this.cents < 0;
    }    

    public double doubleValue(){
        return (double)(cents / 100);
    }

    public boolean add(MonetaryValue a){
        if(!(isNegative())){
            this.cents += a.cents;
            return true;
        }

        return false;
    }

    public boolean subtract(MonetaryValue a){
        if(!(isNegative())){
            this.cents -= a.cents;
            return true;
        }

        return false;
    }

    public static MonetaryValue read(Scanner sc){
        if(!(sc.hasNext())){
            return null;
        }       
     
        return new MonetaryValue(sc.nextDouble());    
    }

}


