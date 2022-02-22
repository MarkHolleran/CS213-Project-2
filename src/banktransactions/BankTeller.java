package banktransactions;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BankTeller {

    //opening a new account
    //closing an existing account
    //depositing money to an existing account
    //withdraw money from an existing account
    //print account details


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

    //C command: Closes Account
        //set account balance to 0
        //set loyal account to false
        //doesnt reset campus code
        //closed accounts can be reopened later w new deposit or campus code where applicable
        //EX: C CC John Doe 2/19/1989

    //D command: deposit
        //deposit money into existing account
        //reject deposit if ammount is 0 or negative
        //EX: D MM Roy Brooks 10/31/1979 100.99

    //W Command: withdraw
        //withdraw money from existing account
        //make sure there is enough money in account to withdraw entered amount
        //EX: W CC John Doe 2/19/1989
}
    //P Command: print
        //display all accounts in the database in current order in array
        //add 'closed' for the accounts that are closed
        //account balances should be displayed with 2 decimal places

    //PT Command: print (ordered by account type)
        //display all accounts in datebase in order of account type
        //add 'closed' for accounts that are closed

    //PI Command: print
        //display all accounts in the database with calculated fees and monthly interest based on current balance
        //fees & interest should be displayed with 2 decimal places

    //UB Command: update balances
        //update balances for all accounts with calculated fes and monthly interest
        //display all accounts in the database with updated balances

    //Q Command: quit
        //stop program and display 'Bank Teller is terminated.'


