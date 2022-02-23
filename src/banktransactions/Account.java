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

        boolean a;
        return a;


    }
    @Override
    public String toString(){

        String a = new String();
        return a;
    }

    public void withdraw(double amount){

       balance = balance - amount;

    }
    public void deposit(double amount){

       // DecimalFormat twoDeciFormat = new DecimalFormat("#.##");
        //  balance = Integer.parseInt(twoDeciFormat.format(balance + amount));
        //how to display in 2 decimal format

        balance = balance + amount;

    }

    //these three are applicable to the 4 account types
    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)


}
