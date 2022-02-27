package banktransactions;

import java.util.Scanner;
import java.util.StringTokenizer;
//and nessesary java exception classes???

public class BankTeller {

    public static final int OPEN_ACCT_ARGS_MIN = 5;
    public static final int OPEN_ACCT_ARGS_MAX = 6;
    public static final int OPEN_CHECKING_ARGS = 4;
    public static final int OPEN_C_CHECKING_ARGS = 5;
    public static final int OPEN_SAVINGS_ARGS = 5;
    public static final int OPEN_M_MARKET_ARGS = 4;
    public static final int LOYAL_SAVINGS = 0;
    public static final int NON_LOYAL_SAVINGS = 1;
    public static final int INVALID_DEPOSIT = -1;
    public static final int CAMPUS_CODE_MIN = 0;
    public static final int CAMPUS_CODE_MAX = 2;
    public static final int DEPOSIT_OR_WITHDRAW_NUM_ARGUMENTS = 5;
    public static final int CLOSE_ACCOUNT_NUM_ARGUMENTS = 4;

    private double validDeposit(String deposit){
        double balance;
        try{
            balance = Double.parseDouble(deposit);
            if(balance <= 0){
                System.out.println("Initial deposit cannot be 0 or negative.");
                return INVALID_DEPOSIT;
            }else{
                return balance;
            }
        }catch(Exception e) {
            System.out.println("Not a valid amount.");
            return INVALID_DEPOSIT;
        }
    }


    private void tryCommandO(StringTokenizer segmentedInput, AccountDatabase database){
        if (segmentedInput.countTokens() == OPEN_ACCT_ARGS_MIN || segmentedInput.countTokens() == OPEN_ACCT_ARGS_MAX) {

            try {
                switch (segmentedInput.nextToken()){

                    case "C":
                        if (segmentedInput.countTokens() == OPEN_CHECKING_ARGS){
                            executeCommandCaseC(segmentedInput, database);
                        }else{
                            System.out.println("Missing data for opening an account.");
                        }
                        break;
                    case "CC":
                        if (segmentedInput.countTokens() == OPEN_C_CHECKING_ARGS){
                            executeCommandCaseCC(segmentedInput, database);
                        }else{
                            System.out.println("Missing data for opening an account.");
                        }
                        break;
                    case "S":
                        if (segmentedInput.countTokens() == OPEN_SAVINGS_ARGS){
                            executeCommandCaseS(segmentedInput, database);
                        }else{
                            System.out.println("Missing data for opening an account.");
                        }
                        break;
                    case "MM":
                        if (segmentedInput.countTokens() == OPEN_M_MARKET_ARGS){
                            executeCommandCaseMM(segmentedInput, database);
                        }else{
                            System.out.println("Missing data for opening an account.");
                        }
                        break;
                    default :
                        System.out.println("Invalid Command!");

                }
            } catch (Exception e) {
                System.out.println("Invalid Command!");
            }

        } else {
            System.out.println("Invalid Command!");
        }
    }
    /*
    private void executeCommandCaseC(StringTokenizer segmentedInput, AccountDatabase database){
        //john doe dob
        String fName = segmentedInput.nextToken();
        String lName = segmentedInput.nextToken();
        String dob = segmentedInput.nextToken();
        double emptyBalance = 0;
        Date date = new Date(dob);

        if(!date.isValid()){
            System.out.println("Date of birth invalid.");
            return;
        }

        Profile profile = new Profile(fName, lName, date);
        Checking checking = new Checking(profile, emptyBalance);
        if(!database.open(checking)){
            System.out.println(profile.toString()+ " same account(type) is in the database.");
            return;
        }


    }
    */
    private void executeCommandCaseC(StringTokenizer segmentedInput, AccountDatabase database){
        //john doe dob 100
        String fName = segmentedInput.nextToken();
        String lName = segmentedInput.nextToken();
        String dob = segmentedInput.nextToken();
        String deposit = segmentedInput.nextToken();

        Date date = new Date(dob);

        if(!date.isValid()){
            System.out.println("Date of birth invalid.");
            return;
        }
        if(validDeposit(deposit)!=INVALID_DEPOSIT){
            Profile profile = new Profile(fName, lName, date);
            Checking checking = new Checking(profile, validDeposit(deposit));
            if(!database.open(checking)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }

        }
    }
    private void executeCommandCaseCC(StringTokenizer segmentedInput, AccountDatabase database){
        //john doe dob 100
        String fName = segmentedInput.nextToken();
        String lName = segmentedInput.nextToken();
        String dob = segmentedInput.nextToken();
        String deposit = segmentedInput.nextToken();
        String campus = segmentedInput.nextToken();
        int campusCode;

        try{
            campusCode = Integer.parseInt(campus);
            if(campusCode < CAMPUS_CODE_MIN && campusCode > CAMPUS_CODE_MAX){
                System.out.println("Invalid campus code.");
                return;
            }
        }catch(Exception e){
            System.out.println("Invalid campus code.");
            return;
        }

        Date date = new Date(dob);

        if(!date.isValid()){
            System.out.println("Date of birth invalid.");
            return;
        }
        if(validDeposit(deposit)!=INVALID_DEPOSIT){
            Profile profile = new Profile(fName, lName, date);
            CollegeChecking checking = new CollegeChecking(profile, validDeposit(deposit), campusCode);
            if(!database.open(checking)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }

        }
    }
    private void executeCommandCaseS(StringTokenizer segmentedInput, AccountDatabase database){
        //john doe dob 100
        String fName = segmentedInput.nextToken();
        String lName = segmentedInput.nextToken();
        String dob = segmentedInput.nextToken();
        String deposit = segmentedInput.nextToken();
        String loyalty = segmentedInput.nextToken();
        int loyaltyCode;

        try{
            loyaltyCode = Integer.parseInt(loyalty);
            if(!(loyaltyCode == NON_LOYAL_SAVINGS || loyaltyCode == LOYAL_SAVINGS)){
                System.out.println("Invalid loyalty code.");
                return;
            }
        }catch(Exception e){
            System.out.println("Invalid loyalty code.");
            return;
        }

        Date date = new Date(dob);

        if(!date.isValid()){
            System.out.println("Date of birth invalid.");
            return;
        }
        if(validDeposit(deposit)!=INVALID_DEPOSIT){
            Profile profile = new Profile(fName, lName, date);
            Savings savings = new Savings(profile, validDeposit(deposit), loyaltyCode);
            if(!database.open(savings)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }

        }
    }

    private void executeCommandCaseMM(StringTokenizer segmentedInput, AccountDatabase database){
        //john doe dob 100
        String fName = segmentedInput.nextToken();
        String lName = segmentedInput.nextToken();
        String dob = segmentedInput.nextToken();
        String deposit = segmentedInput.nextToken();

        Date date = new Date(dob);

        if(!date.isValid()){
            System.out.println("Date of birth invalid.");
            return;
        }
        if(validDeposit(deposit)!=INVALID_DEPOSIT){
            Profile profile = new Profile(fName, lName, date);
            MoneyMarket moneyMarket = new MoneyMarket(profile, validDeposit(deposit));
            if(validDeposit(deposit)<2500){
                System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                return;
            }
            if(!database.open(moneyMarket)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }

        }
    }

    private void tryCommandC(StringTokenizer segmentedInput, AccountDatabase database){

    }

    private void tryCommandD(StringTokenizer segmentedInput, AccountDatabase database){

    }

    private void tryCommandW(StringTokenizer segmentedInput, AccountDatabase database){

    }


    private void parseCommands(StringTokenizer segmentedInput, AccountDatabase database){

        switch (segmentedInput.nextToken()){

            case "O":
                tryCommandO(segmentedInput, database);


                //O command: Opens account

                //C regular checking
                //25$ monthly fee if balance is < 1000$
                //annual interest rate of .1%
                //EX: O C John Doe 2/19/1989
                //CC College checking
                // 0$ monthly fee
                //annual interest rate of .25%
                //for rutgers students only.. have to provide campus code 0=NB 1=newark 2=camden
                //if person has a checking account.. cannot open college checking cus they can only hav 1 checking acc
                //EX: O C Jane Doe 10/1/1995 599.99 0
                //S Savings
                //6$ monthly fee if balance is <300$
                //annual interest rate of .3%
                //loyal customer acc gets .45% interest rate total
                //non loyal customer = 0 loyal customer = 1
                //Money Market
                //10$ monthly fee if balance is <2500
                //requires inital deposit of at least 2500
                //are set to loyal customer accounts by default?????
                //if balance falls below 2500 the loyal customer account part goes away
                //anual interst of .8% if non loyal customer and .95% if loyal
                //fee cannot be waved if # of withdraws is greater than 3

                //make sure dob is valid
                //0 or neg initial deposits aren't allowed
                //O command can also reopen closed accounts
                //set the account to open again and update acc w new info provided



                break;

            case "C":
                tryCommandC(segmentedInput, database);

                //C command: Closes Account
                //set account balance to 0
                //set loyal account to false
                //doesnt reset campus code
                //closed accounts can be reopened later w new deposit or campus code where applicable
                //EX: C CC John Doe 2/19/1989

                break;



            case "D":
                tryCommandD(segmentedInput, database);

                //D command: deposit
                //deposit money into existing account
                //reject deposit if ammount is 0 or negative
                //EX: D MM Roy Brooks 10/31/1979 100.99


                break;
            case "W":
                tryCommandW(segmentedInput, database);

                //W Command: withdraw
                //withdraw money from existing account
                //make sure there is enough money in account to withdraw entered amount
                //EX: W CC John Doe 2/19/1989

                break;
            case "P":

                executeCommandP(database);

                //P Command: print
                //display all accounts in the database in current order in array
                //add 'closed' for the accounts that are closed
                //account balances should be displayed with 2 decimal places

                break;
            case "PT":
                executeCommandPT(database);

                //PT Command: print (ordered by account type)
                //display all accounts in datebase in order of account type
                //add 'closed' for accounts that are closed

                break;
            case "PI":
                executeCommandPI(database);

                //PI Command: print
                //display all accounts in the database with calculated fees and monthly interest based on current balance
                //fees & interest should be displayed with 2 decimal places

                break;
            case "UB":
                executeCommandUB(database);

                //UB Command: update balances
                //update balances for all accounts with calculated fes and monthly interest
                //display all accounts in the database with updated balances


                break;
            case "Q":
                break;
            default :
                System.out.println("Invalid Command!");

        }





    }



    private void executeCommandD(StringTokenizer segmentedInput, AccountDatabase database){
        //c john doe dob 100
    }

    private void executeCommandW(StringTokenizer segmentedInput, AccountDatabase database){
        //c john doe dob 100
    }
    private void executeCommandP(AccountDatabase database){
    }
    private void executeCommandPT(AccountDatabase database){
    }
    private void executeCommandPI(AccountDatabase database){
    }
    private void executeCommandUB(AccountDatabase database){
    }


    public void run(){

        //define a run() method that handles the transactions
        //keep this method under 35 lines
        //handle all exceptions and invalid data before it calls the method in AccountDatabse class to complete tranasctions

        //could get inputmismatchexceptions, numberformatexceptions, nosuchelementexception, invalid (dates, campus codes, deposits, withdraws)

        Scanner input = new Scanner(System.in);

        AccountDatabase database = new AccountDatabase();
        System.out.println("Bank Teller is running");
        String commandInput = input.nextLine();

        while (!("Q").equals(commandInput)){

            if(!("").equals(commandInput)) {
                StringTokenizer segmentedInput = new StringTokenizer(commandInput, " ");
                parseCommands(segmentedInput, database);
            }
            commandInput = input.nextLine();

        }

        System.out.println("Bank Teller is terminated");
        System.exit(0);

    }








}