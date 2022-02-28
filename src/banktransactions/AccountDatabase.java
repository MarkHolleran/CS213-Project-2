

package banktransactions;

import java.text.DecimalFormat;

public class AccountDatabase {

    //array based container
    //that holds a list of acounts with different types

    //initial capacity of the array is 4
    //will automatically grow capacity by 4 if array is full
    //size never shrinks


    //cannot add additional instance variables or change method signatures

    //can add additional methods but all the publci ones must take a single parameter or no paremeters

    public static final int NOT_FOUND = -1;

    private Account[] accounts;
    private int numAcct;

    public AccountDatabase(){
        this.accounts = new Account[4];
        this.numAcct = 0;

    }

    public int getNumAcct(){
        return this.numAcct;
    }

    private int find(Account account) {
        for(int i = 0; i<numAcct; i++){
            if(accounts[i].equals(account) && accounts[i].getType().equals(account.getType())){

                return i;

            }
        }
        return NOT_FOUND;
    }

    public int cancellation(Account acct) {
        Profile profile = acct.getProfile();
        String type = acct.getType();

        for(int i = 0; i<numAcct; i++){
            if(accounts[i].getProfile().equals(profile) && type.equals(accounts[i].getType())){
                return i;
            }
        }
        return NOT_FOUND;
    }

    public boolean findAcct(Account acct){
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].equals(acct) && accounts[i].getType().equals(acct.getType())){
                return true;
            }
        }
        return false;
    }

    public Account findAccount(Account acct){
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].equals(acct) && accounts[i].getType().equals(acct.getType())){
                return accounts[i];
            }
        }
        return null;
    }

    public boolean alreadyClosed(int index){
        if(accounts[index].closed){
            return true;
        }
        return false;
    }

    public boolean findProfile(Profile profile){

        for(int i = 0; i < numAcct; i++){

            if(accounts[i].getProfile().equals(profile)){

                return true;

            }

        }

        return false;

    }


    /**
     * Increases array length of Account array by 4
     * once the array is full
     */
    private void grow() {

        int resizedLength = accounts.length+4;

        Account[] resizedArray = new Account[resizedLength];

        for(int i = 0; i < numAcct; i++){

            resizedArray[i] = accounts[i];

        }
        accounts = resizedArray;

    }

    public boolean duplicateAccount(Account account){

            for(int i = 0; i < numAcct; i++){
                if(accounts[i].getProfile().equals(account.getProfile())
                    && account instanceof CollegeChecking
                    && accounts[i] instanceof Checking && (!(accounts[i] instanceof CollegeChecking))){
                    return true;
                }
                if(accounts[i].getProfile().equals(account.getProfile())
                        && account instanceof Checking
                        && accounts[i] instanceof CollegeChecking
                        && (!(account instanceof CollegeChecking))){
                    return true;
                }
            }


        return false;
    }



    public boolean open(Account account) {

        if(duplicateAccount(account)){
            return false;
        }

        if(find(account) == NOT_FOUND){

            if(numAcct >= this.accounts.length){
                this.grow();
            }

            accounts[numAcct] = account;
            accounts[numAcct].closed = false;
            numAcct++;
            return true;
        }

        if(findAcct(account) && !account.closed){
            return false;
        }

        if(findAcct(account) && account.closed){

            if(numAcct >= this.accounts.length){
                this.grow();
            }

            int index = find(account);
            accounts[index] = account;
            accounts[index].closed = false;
            return true;
        }


        return false;
    }

    public boolean close(Account account) {

        if(find(account) != NOT_FOUND){
            int index = find(account);
            accounts[index].closed = true;
            if(accounts[index] instanceof MoneyMarket){
                ((MoneyMarket) accounts[index]).withdrawCount = 0;
            }
            accounts[index].balance = 0;
            return true;
        }

        return false;
    }

    public void deposit(Account account) {
        if(find(account) != NOT_FOUND){
            int index = find(account);
            accounts[index].deposit(account.balance);

        }
        return;
    }

    public boolean withdraw(Account account) {
        if(find(account) != NOT_FOUND){
            int index = find(account);
            if(account.balance > accounts[index].balance){
                return false;
            }else{
                accounts[index].withdraw(account.balance);
                return true;
            }
        }
        return false;
    }//return false if insufficient fund

    public void print() {
        for(int i = 0; i<numAcct; i++){
            System.out.println(this.accounts[i].toString());
        }
    }

    public void printByAccountType() {
        int n = numAcct;

        for(int i = 0; i < n; ++i){
            Account key = accounts[i];
            int j = i - 1;

            while(j>=0 && accounts[j].getType().compareTo(key.getType()) > 0 ){
                accounts[j + 1] = accounts[j];
                j = j - 1;
            }
            accounts[j + 1] = key;
        }
        for(int i = 0; i<numAcct; i++){
            StringBuilder sb = new StringBuilder(this.accounts[i].toString());
            System.out.println(sb);
        }

        System.out.println("*end of list.");
        System.out.println();
    }

    public void calculate(){
        for(int i = 0; i < numAcct; i++){
            if(!accounts[i].closed){
                accounts[i].balance = accounts[i].balance + accounts[i].monthlyInterest() - accounts[i].fee();
            }
        }
    }


    public void printFeeAndInterest() {

        for(int i = 0; i < numAcct; i++){
            DecimalFormat dformat = new DecimalFormat("#,##0.00");
            DecimalFormat dformat2 = new DecimalFormat("#,##0.00");
            StringBuilder sb = new StringBuilder(accounts[i].toString());
            sb.append("::fee $" + dformat.format(accounts[i].fee())
                + "::monthly interest $" + dformat2.format(accounts[i].monthlyInterest()));
            System.out.println(sb);
        }
        System.out.println("*end of list.");
        System.out.println();
    }


//have to do JUnit test for this
    /**
     * Testbed main for testing the open() & close()
     * methods in this class
     *
     * @param args Commandline arguments
     */
}