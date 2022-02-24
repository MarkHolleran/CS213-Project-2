package banktransactions;

public class Checking extends Account {

    public static final double NO_FEE = 0;
    public static final double MONTHLY_CHECKING_FEE = 25;
    public static final double INTEREST_RATE_PERCENTAGE = 0.1/100;
    public static final double BALANCE_IF_WAIVED = 1000;
    public static final String ACCOUNT_TYPE = "CHECKING";

    public double monthlyInterest(){
        return this.balance + this.balance*INTEREST_RATE_PERCENTAGE;
    }

    public double fee(){
        if(this.balance>=BALANCE_IF_WAIVED){
            return NO_FEE;
        }else{
            return MONTHLY_CHECKING_FEE;
        }
    }
 //
    public String getType(){
        return ACCOUNT_TYPE;
    }
}

