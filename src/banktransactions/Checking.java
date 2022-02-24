package banktransactions;

public class Checking extends Account {

    public static final double monthlyFee = 25;
    public static final double interestRatePercentage = 0.1/100;
    public static final double balanceIfWaived = 1000;
    public static final String accountType = "CHECKING";

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
