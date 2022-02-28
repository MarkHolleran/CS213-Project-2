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
            ///*
            if(database.findAcct(checking)){

                if(database.findAccount(checking).closed){

                    checking.closed = true;
                    database.open(checking);
                    System.out.println("Account reopened.");
                }else{
                    checking.closed = false;
                    System.out.println(profile.toString()+ " same account(type) is in the database.");
                }
            }else{
                try{
                    if(database.duplicateAccount(checking)){
                        System.out.println(profile.toString()+ " same account(type) is in the database.");
                        return;
                    }
                }catch(Exception e){

                }
                database.open(checking);
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
            if(database.findAcct(checking)){

                if(database.findAccount(checking).closed){

                    checking.closed = true;
                    database.open(checking);
                    System.out.println("Account reopened.");
                }else{
                    checking.closed = false;
                    System.out.println(profile.toString()+ " same account(type) is in the database.");
                }
            }else{
                try{
                    if(database.duplicateAccount(checking)){
                        System.out.println(profile.toString()+ " same account(type) is in the database.");
                        return;
                    }
                }catch(Exception e){

                }
                database.open(checking);
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
            MoneyMarket checking = new MoneyMarket(profile, validDeposit(deposit));

            if(validDeposit(deposit)<2500){
                System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                return;
            }
            //I can write
            /////////////////////////////////////////////////////
            if(database.findAcct(checking) && database.findAccount(checking).getType().equals(checking.getType())){

                if(database.findAccount(checking).closed){

                    checking.closed = true;
                    database.open(checking);
                    System.out.println("Account reopened.");
                }else{
                    checking.closed = false;
                    System.out.println(profile.toString()+ " same account(type) is in the database.");
                }
            }else{
                try{
                    if(database.duplicateAccount(checking)){
                        System.out.println(profile.toString()+ " same account(type) is in the database.");
                        return;
                    }
                }catch(Exception e){

                }
                database.open(checking);
                System.out.println("Account opened.");

            }
            //




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
            return "Money Market";
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

                Account acct = createAccount(profile, accountType, 0);

                if(database.cancellation(acct) != NOT_FOUND){
                    int index = database.cancellation(acct);

                    if(database.alreadyClosed(index)){
                        database.close(acct);
                        System.out.println("Account is closed already.");
                    }else{
                        database.close(acct);
                        System.out.println("Account closed.");
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

                Account accot = createAccount(profile, accountType,0);


                if(!database.findAcct(accot)){
                    System.out.println(profile.toString() + " " + type + " is not in the database.");
                    return;
                    //!database.findProfile(profile) ||
                }

                if(validOtherDeposit(deposit) != INVALID_DEPOSIT){
                    Account acct = createAccount(profile, accountType, validOtherDeposit(deposit));
                    database.deposit(acct);
                    System.out.println("Deposit - balance updated.");
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

                Account accot = createAccount(profile, accountType,0);

                if(!database.findAcct(accot)){
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
                break;
            case "PT":
                executeCommandPT(database);
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
        //create method
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts with fee and monthly interest");
        database.printFeeAndInterest();
    }
    private void executeCommandUB(AccountDatabase database){
        //create method
        if(database.getNumAcct()==0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts with updated balance");
        database.calculate();
        database.print();
        System.out.println("*end of list.");
        System.out.println();
    }


    public void run(){

        Scanner input = new Scanner(System.in);

        AccountDatabase database = new AccountDatabase();
        System.out.println("Bank Teller is running");
        String commandInput = input.nextLine();

        while (!("Q").equals(commandInput)){

            if(!("").equals(commandInput)) {
                if(commandInput.contains("\t")){
                    commandInput = commandInput.replace("\t","");
                }
                StringTokenizer segmentedInput = new StringTokenizer(commandInput, " ");
                parseCommands(segmentedInput, database);
            }
            commandInput = input.nextLine();

        }

        System.out.println("Bank Teller is terminated. ");
        System.exit(0);

    }

}