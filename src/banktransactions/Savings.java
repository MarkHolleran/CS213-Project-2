package banktransactions;

public class Savings extends Account {

    public static final double monthlyFee = 6;
    public static final double interestRatePercentage = 0.3/100;
    public static final double balanceIfWaived = 300;
    public static final String accountType = "SAVINGS";

    public double monthlyInterest(){
        return this.balance + this.balance*interestRatePercentage;
    }

    public double fee(){
        if(this.balance>=balanceIfWaived){
            return 0;
        }else{
            return monthlyFee;
        }
    }

    public String getType(){
        return accountType;
    }

}
