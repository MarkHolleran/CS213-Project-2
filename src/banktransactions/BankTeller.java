package banktransactions;

import java.util.Scanner;
import java.util.StringTokenizer;
//and nessesary java exception classes???

public class BankTeller {

    public static final int DEPOSIT_OR_WITHDRAW_NUM_ARGUMENTS = 5;
    public static final int CLOSE_ACCOUNT_NUM_ARGUMENTS = 4;



    private void parseCommands(StringTokenizer segmentedInput, AccountDatabase database){

        switch (segmenetedInput.nextToken()){

            case "O":
                tryCommandO(segmenetedInput, database);


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

                case "C"
                    tryCommandC(segmentedInput, database);

                    //C command: Closes Account
                    //set account balance to 0
                    //set loyal account to false
                    //doesnt reset campus code
                    //closed accounts can be reopened later w new deposit or campus code where applicable
                    //EX: C CC John Doe 2/19/1989

                    break;



            case "D"
                tryCommandD(segmenetedInput, database);

                //D command: deposit
                //deposit money into existing account
                //reject deposit if ammount is 0 or negative
                //EX: D MM Roy Brooks 10/31/1979 100.99


                break;
            case: "W"
                tryCommandW(segmenetedInput, database);

                //W Command: withdraw
                //withdraw money from existing account
                //make sure there is enough money in account to withdraw entered amount
                //EX: W CC John Doe 2/19/1989

                    break;
            case: "P"

                executeCommandP(database);

                //P Command: print
                //display all accounts in the database in current order in array
                //add 'closed' for the accounts that are closed
                //account balances should be displayed with 2 decimal places

                break;
            case: "PT"
                executeCommandPT(database);

                //PT Command: print (ordered by account type)
                //display all accounts in datebase in order of account type
                //add 'closed' for accounts that are closed

                break;
            case: "PI"
                executeCommandPI(database);

                //PI Command: print
                //display all accounts in the database with calculated fees and monthly interest based on current balance
                //fees & interest should be displayed with 2 decimal places

                break;
            case: "UB"
                executeCommandUB(database);

                //UB Command: update balances
                //update balances for all accounts with calculated fes and monthly interest
                //display all accounts in the database with updated balances


                break;
            case: "Q"
                break;
            default :
                System.out.println("Invalid Command!");

        }


    }

    private void executeCommandD(StringTokenizer segmentedInput ,AccountDatabase database){
        //deposit
        //c john doe dob 100




    }

    private void executeCommandW(StringTokenizer segmentedInput ,AccountDatabase database){
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

            StringTokenizer segmentedInput = new StringTokenizer(commandInput," ");
            parseCommands(segmentedInput, database);

            commandInput = input.nextLine();

        }

        System.out.println("Bank Teller is terminated");
        System.exit(0);

    }








}