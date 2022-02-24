package banktransactions;

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
        //i think this compares two accounts

        boolean f = true;
        return f;

    }


    @Override
    public String toString(){

        return getType() + "::" + holder.toString() + "::" + "Balance " + "$" + balance;

    }

    public void withdraw(double amount){

       balance = balance - amount;

    }
    public void deposit(double amount){

       DecimalFormat twoDeciFormat = new DecimalFormat("#.##");
       double depositAmount = Double.parseDouble(twoDeciFormat.format(amount));
       balance = Double.parseDouble(twoDeciFormat.format(balance + depositAmount));

        System.out.println(balance);
        //how to display in 2 decimal format

    }

    //these three are applicable to the 4 account types
    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)


}
