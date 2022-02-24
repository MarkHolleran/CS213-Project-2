package banktransactions;

//import java.text.DecimalFormat;

public abstract class Account {

    //abstract class
    //defines common data and operations for all account types

    //each account has a profile that uniquely identifies the account holder
    //this is the superclass of all account types
    //is an abstract class with 3 abstract methods

    //cannot add additional instance variables or change method signatures

    protected Profile holder;
    protected boolean closed;
    protected double balance;

    @Override
    public boolean equals(Object obj){
        Account acct = (Account) obj;
        return (this.closed == acct.closed) && (this.balance == acct.balance) && (this.holder.equals(acct.holder));
    }
    @Override
    public String toString(){

        return "";
    }

    }

    //these three are applicable to the 4 account types
    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)


}
