package banktransactions;

public class Savings extends Account {

    public static final double NO_FEE = 0;
    public static final double MONTHLY_SAVINGS_FEE = 6;
    public static final double INTEREST_RATE_PERCENTAGE = 0.3/100;
    public static final double BALANCE_IF_WAIVED = 300;
    public static final String ACCOUNT_TYPE = "Savings";
    public int loyalCustomer;

    public Savings(Profile profile, boolean closed, double balance, int loyalCustomer ) {

        super.holder = profile;
        super.closed = closed;
        super.deposit(balance);
        this.loyalCustomer = loyalCustomer;
    }

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

    public static void main(String[] args){

        Date newdate = new Date("4/4/1680");

        Profile newprofile = new Profile("Mark","Holleran",newdate);

        Savings newsavings = new Savings(newprofile, false, 10033.303, 1);
        Checking newchecking = new Checking(newprofile, false, 10033.303);


        System.out.println(newsavings.toString());
        System.out.println(newchecking.toString());


    }



}
