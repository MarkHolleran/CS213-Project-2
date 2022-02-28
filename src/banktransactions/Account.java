package banktransactions;


import java.text.DecimalFormat;

/**
 * Class that represents an abstract Account Object
 *
 * An Account object contains a Profile object,
 * a boolean representing a closed or open account,
 * and a double representing the current balance.
 * @author Mark Holleran, Abhitej Bokka
 */
public abstract class Account {

    //cannot add additional instance variables or change method signatures

    protected Profile holder;
    protected boolean closed;
    protected double balance;


    /**
     *
     * @param obj Instance of Account to compare against another Account object
     * @return True if Account Profile and Type are same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Account) {

            Account acct = (Account) obj;

            return (this.holder.equals(acct.holder) && this.getType().equals(acct.getType()));
            //took out (this.closed == acct.closed) for open() in acctDB
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

    public Profile getProfile(){
        return holder;
    }

    public abstract double monthlyInterest(); //return monthly interest
    public abstract double fee(); //return monthly fee
    public abstract String getType(); //return account type (class name)

}
