package banktransactions;

/**
 * Class that represents a MoneyMarket account
 *
 *
 * @author Mark Holleran, Abhitej Bokka
 */

public class MoneyMarket extends Savings {

    public final double NO_FEE = 0;
    public final double MONTHLY_FEE = 10;
    public final double NONLOYAL_INTEREST_RATE_PERCENTAGE = 0.8/100;
    public final double LOYAL_INTEREST_RATE_PERCENTAGE = 0.95/100;
    public int withdrawCount = 0;
    public int MAX_WITHDRAW_LIMIT = 3;

    public final double BALANCE_IF_WAIVED = 2500;
    public static final String ACCOUNT_TYPE = "Money Market Savings";
    public int loyalCustomer = 1;
    //1 by default ... other constructor for if not loyal
    //extends the Savings class
    //includes specific data and operaitons to a money market account

    /**
     *
     * @param profile
     * @param balance
     */
    public MoneyMarket(Profile profile, double balance){
        //moneymarket account is loyal by default

        super(profile,balance,1);
        //does this count as a magic number?
        super.closed = false;

    }

    @Override
    public double monthlyInterest() {


        //if balance falls below 2500 then no longer loyal and interest goes to .80
        //if balance is above 2500 then customer remains loyal and interest stays at .95

        if (this.balance > BALANCE_IF_WAIVED) {
            //if balance is greater than threshold
            return this.balance +this.balance*LOYALINTEREST_RATE_PERCENTAGE;

        }else {
                //means balance is less than the threshold
            loyalCustomer = 0;
            //loyalcustomer revoked

            return this.balance + this.balance*NONLOYAL_INTEREST_RATE_PERCENTAGE;

            //not sure if this will return the interest rates for this one

            //return this.balance + this.balance*INTEREST_RATE_PERCENTAGE;

        }

    }


    public void withdraw(double amount){
    
        super.withdraw(amount);

        withdrawCount++;

        if (this.withdrawCount > MAX_WITHDRAW_LIMIT){

            loyalCustomer = 0;

        }

    }

    public void deposit (double amount){

        super.deposit(amount);

    }

    public String getType(){

        return super.getType();

    }

    @Override
    public double fee(){

        if (this.balance >=BALANCE_IF_WAIVED && withdrawCount <= 3){

            return NO_FEE;

        }else {
            //(this.balance >= BALANCE_IF_WAIVED && withdrawCount > 3)
            return MONTHLY_FEE;

        }

    }


    public static void main(String[] args){

    }


}
//By default, it is a loyal customer account

//• If the balance falls below $2500, then it is not a
//loyal customer account anymore

//• A loyal customer account gets additional interest
//rate 0.15%; that is, annual interest rate will be
//0.95%.
//• Fee cannot be waived if the number of
//withdrawals exceeds 3 times