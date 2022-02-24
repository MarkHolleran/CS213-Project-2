
package banktransactions;

public class Savings extends Account {
    //account dfines
    //profile holder
    //boolean closed
    //double balance

    //equals method
    //tostring method

    //withdraw
    //deposit
    //return monthly interest
    //return monthly fee
    //return account type (class name)

    protected int loyalCustomer;
    //protected int monthlyFee;
    protected int monthlyFeeAvoidanceThreshold = 300;

    //protected double annualInterestRate;
    //loyality is either 0 or 1



    public Savings(Profile profile, boolean closed, double balance, int loyalCustomer ){

        //first last dob init deposit loyal or not

        super.holder = profile;
        super.closed = closed;
        super.deposit(balance);
        this.loyalCustomer = loyalCustomer;




    }
    @Override
    public String getType(){

        return "Savings";

    }
    @Override
    public double fee() {

        return 6;
        //does this could as a magic number?

    }

    public double monthlyInterest(){

        return .3;


    }

    @Override
    public String toString(){

        if (loyalCustomer == 0){

            return super.toString();

        }else   {

            return super.toString() + "::" + "loyal";
        }

    }

    public static void main(String[] args){

        Date newdate = new Date("4/4/1680");

        Profile newprofile = new Profile("Mark","Holleran",newdate);

        Savings newsavings = new Savings(newprofile, false, 100.3, 1);

            System.out.println(newsavings.toString());

            System.out.println(newsavings.getType());

    }

}
