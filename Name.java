import java.util.Scanner;

public class Name {
    private String first;
    private String last;

    public Name(){
        this("", "");
    }

    public Name(String first, String last){
        this.first = first;
        this.last = last;
    }

    public Name(Name name){
        this.first = name.first;
        this.last = name.last;
    }

    @Override
    public boolean equals(Object a){
        if(a instanceof Name){
            Name other = (Name)a;
            return this.first.equals(other.first) && this.last.equals(other.last);
        }
        
        return false;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }

    public static Name read(Scanner sc){
        if(!(sc.hasNext())){
            return null;
        }       
        else{
            return new Name(sc.next(), sc.next());
        }
    }

}
