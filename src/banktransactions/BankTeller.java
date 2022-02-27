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
    public static final int CLOSE_ACCT_ARGS_MIN = 4;
    public static final int NOT_FOUND = -1;
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

    private double validOtherDeposit(String deposit){
        double balance;
        try{
            balance = Double.parseDouble(deposit);
            if(balance <= 0){
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return INVALID_DEPOSIT;
            }else{
                return balance;
            }
        }catch(Exception e) {
            System.out.println("Not a valid amount.");
            return INVALID_DEPOSIT;
        }
    }

    private double validWithdraw(String deposit){
        double balance;
        try{
            balance = Double.parseDouble(deposit);
            if(balance <= 0){
                System.out.println("Withdraw - amount cannot be 0 or negative.");
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
                        System.out.println("Missing data for opening an account.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Command!");
            }

        } else {
            System.out.println("Missing data for opening an account.");
        }
    }

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
            boolean attempt = false;
            if(checking.closed){
                attempt = true;
            }
            if(!database.open(checking)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }
            if(database.findAcct(checking) && attempt){
                System.out.println("Account reopened.");
            }else{
                System.out.println("Account opened.");
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
            if(campusCode < CAMPUS_CODE_MIN || campusCode > CAMPUS_CODE_MAX){
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
            boolean attempt = false;
            if(checking.closed){
                attempt = true;
            }
            if(!database.open(checking)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }
            if(database.findAcct(checking) && attempt){
                System.out.println("Account reopened.");
            }else{
                System.out.println("Account opened.");
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
            boolean attempt = false;
            if(savings.closed){
                attempt = true;
            }
            if(!database.open(savings)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }
            if(database.findAcct(savings) && attempt){
                System.out.println("Account reopened.");
            }else{
                System.out.println("Account opened.");
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
            boolean attempt = false;
            if(moneyMarket.closed){
                attempt = true;
            }
            if(validDeposit(deposit)<2500){
                System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                return;
            }
            if(!database.open(moneyMarket)){
                System.out.println(profile.toString()+ " same account(type) is in the database.");
                return;
            }
            if(database.findAcct(moneyMarket) && attempt){
                System.out.println("Account reopened.");
            }else{
                System.out.println("Account opened.");
            }

        }
    }

    // DONE WITH OPEN

    private String getAccountType(String cmd){
        if(cmd.equals("C")){
            return "Checking";
        }
        if(cmd.equals("CC")){
            return "College Checking";
        }
        if(cmd.equals("S")){
            return "Savings";
        }
        if(cmd.equals("MM")){
            return "Money Market Savings";
        }

        return "Crash";

    }

    private void tryCommandC(StringTokenizer segmentedInput, AccountDatabase database){
        if (segmentedInput.countTokens() == CLOSE_ACCT_ARGS_MIN) {
            try {

                String accountType = segmentedInput.nextToken();
                String fName = segmentedInput.nextToken();
                String lName = segmentedInput.nextToken();
                String dob = segmentedInput.nextToken();
                Date date = new Date(dob);
                Profile profile = new Profile(fName, lName, date);
                Profile profile2 = new Profile("fName", lName, date);
                String type = getAccountType(accountType);

                if(database.cancellation(profile, type) != NOT_FOUND){
                    int index = database.cancellation(profile, type);



                    if(database.alreadyClosed(index)){
                        Checking acct = new Checking(profile2, 10000);
                        database.close(acct);
                        System.out.println("Account is closed already.");
                    }else{
                        System.out.println("Account is closed");
                    }
                }


            } catch (Exception e) {
                System.out.println("Missing data for closing an account.");
            }
        } else {
            System.out.println("Missing data for closing an account.");
        }
    }

    private Account createAccount(Profile profile, String type, double balance){

        if(type.equals("C")){
            return new Checking(profile,balance);
        }
        if(type.equals("CC")){
            return new CollegeChecking(profile,balance,0);
        }
        if(type.equals("S")){
            return new Savings(profile,balance,0);
        }
        if(type.equals("MM")){
            return new MoneyMarket(profile,balance);
        }

        return null;
    }

    private void tryCommandD(StringTokenizer segmentedInput, AccountDatabase database){
        if (segmentedInput.countTokens() == DEPOSIT_OR_WITHDRAW_NUM_ARGUMENTS) {
            try {
                String accountType = segmentedInput.nextToken();
                String fName = segmentedInput.nextToken();
                String lName = segmentedInput.nextToken();
                String dob = segmentedInput.nextToken();
                String deposit = segmentedInput.nextToken();
                Date date = new Date(dob);
                String type = getAccountType(accountType);
                Profile profile = new Profile(fName, lName, date);

                if(!database.findProfile(profile)){
                    System.out.println(profile.toString() + " " + type + " is not in the database.");
                    return;
                }

                if(validOtherDeposit(deposit) != INVALID_DEPOSIT){
                    Account acct = createAccount(profile, accountType, validOtherDeposit(deposit));
                    database.deposit(acct);
                }


            } catch (Exception e) {
                System.out.println("Missing data for closing an account.");
            }
        } else {
            System.out.println("Missing data for closing an account.");
        }
    }

    private void tryCommandW(StringTokenizer segmentedInput, AccountDatabase database){
        if (segmentedInput.countTokens() == DEPOSIT_OR_WITHDRAW_NUM_ARGUMENTS) {
            try {
                String accountType = segmentedInput.nextToken();
                String fName = segmentedInput.nextToken();
                String lName = segmentedInput.nextToken();
                String dob = segmentedInput.nextToken();
                String deposit = segmentedInput.nextToken();
                Date date = new Date(dob);
                String type = getAccountType(accountType);
                Profile profile = new Profile(fName, lName, date);

                if(!database.findProfile(profile)){
                    System.out.println(profile.toString() + " " + type + " is not in the database.");
                    return;
                }

                if(validWithdraw(deposit) != INVALID_DEPOSIT){
                    Account acct = createAccount(profile, accountType, validWithdraw(deposit));
                    if(database.withdraw(acct)){
                        System.out.println("Withdraw - balance updated.");
                    }else{
                        System.out.println("Withdraw - insufficient fund.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Missing data for closing an account.");
            }
        } else {
            System.out.println("Missing data for closing an account.");
        }
    }


    private void parseCommands(StringTokenizer segmentedInput, AccountDatabase database){

        switch (segmentedInput.nextToken()){

            case "O":
                tryCommandO(segmentedInput, database);
                break;
            case "C":
                tryCommandC(segmentedInput, database);
                break;
            case "D":
                tryCommandD(segmentedInput, database);
                break;
            case "W":
                tryCommandW(segmentedInput, database);
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

    private void executeCommandP(AccountDatabase database){
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts in the database*");
        database.print();
        System.out.println("*end of list*");
        System.out.println();
    }
    private void executeCommandPT(AccountDatabase database){
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts by account type.");
        database.printByAccountType();
    }
    private void executeCommandPI(AccountDatabase database){
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts by account type.");
        database.printByAccountType();
    }
    private void executeCommandUB(AccountDatabase database){
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
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