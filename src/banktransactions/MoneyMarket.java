package banktransactions;

public class MoneyMarket extends Savings {

    public final double NO_FEE = 0;
    public final double MONTHLY_FEE = 10;
    public final double NONLOYAL_INTEREST_RATE_PERCENTAGE = 0.8/100;
    public final double LOYAL_INTEREST_RATE_PERCENTAGE = 0.95/100;
    public int withdrawCount = 0;

    public final double BALANCE_IF_WAIVED = 2500;
    public static final String ACCOUNT_TYPE = "Money Market Savings";
    public int loyalCustomer = 1;
    //1 by default ... other constructor for if not loyal
    //extends the Savings class
    //includes specific data and operaitons to a money market account


    public MoneyMarket(Profile profile, double balance, int loyalCustomer ){


        super(profile, balance, loyalCustomer);

        super.holder = profile;
        super.closed = false;

        super.deposit(balance);

    }



    public double monthlyInterest(){


        //if balance falls below 2500 then no longer loyal and interest goes to .80
        //if balance is above 2500 then customer remains loyal and interest stays at .95

        if (this.balance > BALANCE_IF_WAIVED ){

            return (LOYAL_INTEREST_RATE_PERCENTAGE);
        }

        if (this.balance < BALANCE_IF_WAIVED ){
            //if falls under required balance

            loyalCustomer = 0;

            return NONLOYAL_INTEREST_RATE_PERCENTAGE;

        return super.monthlyInterest();
        //not sure if this will return the interest rates for this one

        //return this.balance + this.balance*INTEREST_RATE_PERCENTAGE;

    }


    public void deposit(double amount){
    
        balance -= amount;

        withdrawCount++;

        if (this.withdrawCount > 3){

            loyalCustomer = 0;

        }


    }

    public String getType(){

        return ACCOUNT_TYPE;

    }

    @Override
    public double fee(){

        if (this.balance >=BALANCE_IF_WAIVED && withdrawCount <= 3){

            return NO_FEE;

        }
        if (this.balance >= BALANCE_IF_WAIVED && withdrawCount > 3){

            return MONTHLY_FEE;


        }


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