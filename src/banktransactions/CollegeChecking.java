package banktransactions;

public class CollegeChecking extends Checking {

    //public final double NO_FEE = 0;
    public final double MONTHLY_FEE = 0;
    public final double ADDITONAL_INTEREST_RATE_PERCENTAGE = .15/100;
    public static final double MONTHS_IN_A_YEAR = 12;
    public final int CAMPUS_ZERO = 0;
    public final int CAMPUS_ONE = 1;
    public final int CAMPUS_TWO = 2;
    //0 fees always
    //.25 interest rate

    public final String ACCOUNT_TYPE = "College Checking";
    private int campusCode;

    //Provided to Rutgers’s students only. Must provide a
    //valid campus code to qualify:
    //0 – New Brunswick
    //1 – Newark
    //2 – Camden

//College Checking::Chris Young 9/20/2001::Balance $500.00::NEW_BRUNSWICK

    public CollegeChecking(Profile profile, double balance, int campusCode){

        super(profile, balance);

        this.campusCode = campusCode;

    }

    public double monthlyInterest(){

        return super.monthlyInterest() + this.balance * ADDITONAL_INTEREST_RATE_PERCENTAGE/MONTHS_IN_A_YEAR;

    }

    public void deposit(double amount){

       super.deposit(amount);

    }

    public void withdraw(double amount){

        super.withdraw(amount);

    }

    public String getType(){

        return ACCOUNT_TYPE;

    }

    public double fee() {

        return MONTHLY_FEE;

    }

    @Override
    public String toString(){
        //0 else if 1 otherwise 0
        //this doesn't defend against out of bounds ints

        if (campusCode == CAMPUS_ZERO) {

            return super.toString() + "::" + "NEW_BRUNSWICK";

        }else if (campusCode == CAMPUS_ONE){

            return super.toString() + "::" + "NEWARK";

        }else

            return super.toString() + "::" + "CAMDEN";

    }

}
