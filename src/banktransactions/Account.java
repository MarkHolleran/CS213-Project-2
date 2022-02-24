package banktransactions;

//import java.text.DecimalFormat;

import java.text.DecimalFormat;

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

        if(obj instanceof Account){
            Account acct = (Account) obj;
            return (this.closed == acct.closed) && (this.balance == acct.balance) && (this.holder.equals(acct.holder));

        }
         return false;
    }
    @Override
    public String toString(){
        DecimalFormat dformat = new DecimalFormat("#,##0.00");
        //Checking::April March 1/15/1987::Balance $0.00::CLOSED
        StringBuilder sb = new StringBuilder(holder.toString() + "::Balance $" + dformat.format(balance));

        if(closed){
            sb.append("::CLOSED");
        }

        return sb.toString();
    }

    public void withdraw(double amount){
        balance -= amount;
    }

    public void deposit(double amount){
        balance += amount;
    }

    //these three are applicable to the 4 account types
    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)


}
