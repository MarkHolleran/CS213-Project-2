package banktransactions;

public class Savings extends Account {

    public static final double NO_FEE = 0;
    public static final double MONTHLY_SAVINGS_FEE = 6;
    public static final double NONINTEREST_RATE_PERCENTAGE = 0.3/100;
    public static final double LOYALINTEREST_RATE_PERCENTAGE = 0.45/100;

    public static final double BALANCE_IF_WAIVED = 300;
    public static final String ACCOUNT_TYPE = "Savings";
    public int loyalCustomer;

    public Savings(Profile profile, double balance, int loyalCustomer ) {

        super.holder = profile;
        super.closed = false;
        super.deposit(balance);
        this.loyalCustomer = loyalCustomer;
    }

    @Override
    public String toString(){
        if (loyalCustomer == 1) {

            return super.toString() + "::Loyal";

        }else

            return super.toString();

    }

    public double monthlyInterest(){

        //doesn't protect against out of bounds ints

        if (loyalCustomer == 0) {

            return this.balance + this.balance * NONINTEREST_RATE_PERCENTAGE;

        }else {

            return this.balance + this.balance * LOYALINTEREST_RATE_PERCENTAGE;

        }

    }

    public double fee(){

        if(this.balance>=BALANCE_IF_WAIVED){

            return NO_FEE;

        }else{

            return MONTHLY_SAVINGS_FEE;

        }

    }

    public void withdraw(double amount){
        super.withdraw(amount);
    }

    public void deposit(double amount){
        super.deposit(amount);
    }

    public String getType(){

        return ACCOUNT_TYPE;

    }

    public static void main(String[] args){

        Date newdate = new Date("4/4/1680");

        Profile newprofile = new Profile("Mark","Holleran",newdate);

        Savings newsavingsnonloyal = new Savings(newprofile, 1000, 0);
        Savings newsavingsloyal = new Savings(newprofile, 1000, 1);

        Checking newchecking = new Checking(newprofile,800.67);
        CollegeChecking newcollege = new CollegeChecking(newprofile, 1023.32,0);


        System.out.println(newsavingsnonloyal.toString());
        System.out.println(newsavingsnonloyal.fee());
        System.out.println(newsavingsnonloyal.monthlyInterest());

        System.out.println(newsavingsloyal.toString());
        System.out.println(newsavingsloyal.fee());
        System.out.println(newsavingsloyal.monthlyInterest());


        System.out.println(newchecking.toString());
        System.out.println(newchecking.fee());

        System.out.println(newcollege.toString());
        System.out.println(newcollege.fee());

        System.out.println("---------------");
        MoneyMarket newmoney = new MoneyMarket(newprofile,1000);
        MoneyMarket newmoney2 = new MoneyMarket(newprofile,4000);

        System.out.println(newmoney.toString());
        System.out.println(newmoney.fee());
        System.out.println(newmoney.monthlyInterest());

        System.out.println(newmoney2.toString());
        System.out.println(newmoney2.fee());
        System.out.println(newmoney2.monthlyInterest());
        newmoney2.withdraw(100);
        newmoney2.withdraw(100);

        newmoney2.withdraw(100);

        newmoney2.withdraw(100);
        System.out.println(newmoney2.monthlyInterest() + " "+ newmoney2.withdrawCount);
        System.out.println(newmoney2.fee());
        System.out.println(newmoney2.toString());







    }



}
