package banktransactions;

public class Savings extends Account {

    public static final double NO_FEE = 0;
    public static final double MONTHLY_SAVINGS_FEE = 6;
    public static final double INTEREST_RATE_PERCENTAGE = 0.3/100;
    public static final double BALANCE_IF_WAIVED = 300;
    public static final String ACCOUNT_TYPE = "SAVINGS";

    public double monthlyInterest(){
        return this.balance + this.balance*INTEREST_RATE_PERCENTAGE;
    }

    public double fee(){
        if(this.balance>=BALANCE_IF_WAIVED){
            return NO_FEE;
        }else{
            return MONTHLY_SAVINGS_FEE;
        }
    }

    public String getType(){
        return ACCOUNT_TYPE;
    }
}
