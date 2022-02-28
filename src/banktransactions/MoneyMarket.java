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
    public final double MM_ADDITIONAL_INTEREST_RATE_PERCENTAGE = 0.8/100;
    public final double MM_LOYAL_INTEREST_RATE_PERCENTAGE = 0.15/100;
    public static final double MONTHS_IN_A_YEAR = 12;
    public static final int DEFAULT_LOYALTY = 1;
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

        super(profile, balance, DEFAULT_LOYALTY);

        if(balance >= BALANCE_IF_WAIVED){
            loyalCustomer = 1;
        }else{
            loyalCustomer = 0;
        }
       
        super.closed = false;

    }

    /**
     *
     * @param obj Instance of Account to compare against another Account object
     * @return True if Account Profile and Type are same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MoneyMarket) {
            MoneyMarket acct = (MoneyMarket) obj;
            return this.holder.equals(acct.holder);
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());



        if(super.toString().contains("::Loyal") && loyalCustomer == 0){
            sb.delete(sb.length()-7,sb.length());

        }else{
            return super.toString() + "::withdrawl: " + withdrawCount;
        }

        sb.append("::withdrawl: " + withdrawCount);
        return sb.toString();


    }

    @Override
    public double monthlyInterest() {

        double newTotal = this.balance * MM_ADDITIONAL_INTEREST_RATE_PERCENTAGE/MONTHS_IN_A_YEAR;

        if(loyalCustomer == 1){
            newTotal += this.balance * MM_LOYAL_INTEREST_RATE_PERCENTAGE/MONTHS_IN_A_YEAR;
        }

        if(newTotal >= BALANCE_IF_WAIVED){
            loyalCustomer = 1;
        }

        return newTotal;
    }


    public void withdraw(double amount){
    
        super.withdraw(amount);

        withdrawCount++;

        if (this.balance < BALANCE_IF_WAIVED){

            loyalCustomer = 0;

        }

    }

    public void deposit (double amount){

        super.deposit(amount);

    }

    public String getType(){

        return ACCOUNT_TYPE;

    }

    @Override
    public double fee(){

        if (withdrawCount < MAX_WITHDRAW_LIMIT && balance >= 2500){
        // wording is confusing here
            return NO_FEE;

        }else {
            //(this.balance >= BALANCE_IF_WAIVED && withdrawCount > 3)
            return MONTHLY_FEE;

        }

    }

    /*
    public static void main(String[] args){

        Date date = new Date("8/10/2002");
        Profile profile = new Profile("Abhitej","Bokka",date);
        MoneyMarket mm = new MoneyMarket(profile,3200);

        System.out.println(mm.getType());
        System.out.println(mm.loyalCustomer);
    }
    */

}
//By default, it is a loyal customer account

//• If the balance falls below $2500, then it is not a
//loyal customer account anymore

//• A loyal customer account gets additional interest
//rate 0.15%; that is, annual interest rate will be
//0.95%.
//• Fee cannot be waived if the number of
//withdrawals exceeds 3 times