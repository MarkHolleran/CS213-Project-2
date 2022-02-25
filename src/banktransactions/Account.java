package banktransactions;


import java.text.DecimalFormat;

public abstract class Account {

    //cannot add additional instance variables or change method signatures

    protected Profile holder;
    protected boolean closed;
    protected double balance;

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Account) {

            Account acct = (Account) obj;

            return (this.closed == acct.closed) && (this.balance == acct.balance) && (this.holder.equals(acct.holder));

        }

        return false;
    }

    @Override
    public String toString() {

        DecimalFormat dformat = new DecimalFormat("#,##0.00");


        StringBuilder sb = new StringBuilder(getType() + "::" + holder.toString() + "::Balance $" + dformat.format(balance));

        if (closed) {

            sb.append("::CLOSED");

        }

        return sb.toString();
    }

    public void withdraw(double amount) {

        balance -= amount;
    }

    public void deposit(double amount) {

        balance += amount;

    }

    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)

}
