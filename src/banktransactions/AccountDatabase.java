

package banktransactions;

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
            if(accounts[i].equals(account)){

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

            if(accounts[i].equals(acct)){

                return true;

            }

        }

        return false;

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

    private boolean duplicateAccount(Account account){

            for(int i = 0; i < numAcct; i++){
                if(accounts[i].getProfile().equals(account.getProfile())
                    && account instanceof CollegeChecking
                    && accounts[i] instanceof Checking){
                    return true;
                }
                if(accounts[i].getProfile().equals(account.getProfile())
                        && account instanceof Checking
                        && accounts[i] instanceof CollegeChecking){
                    return true;
                }
            }


        return false;
    }



    public boolean open(Account account) {

        if(duplicateAccount(account)){
            return false;
        }

        if(findAcct(account) && !account.closed){
            return false;
        }

        if(findAcct(account) && account.closed){
            int index = find(account);
            accounts[index] = account;
            accounts[index].closed = false;
            numAcct++;
            return true;
        }

        if(find(account) == NOT_FOUND){

            if(numAcct == this.accounts.length){
                this.grow();
            }

            accounts[numAcct] = account;
            accounts[numAcct].closed = false;
            numAcct++;
            return true;
        }

        return false;
    }

    public boolean close(Account account) {

        if(find(account) != NOT_FOUND){
            int index = find(account);
            accounts[index].closed = true;
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

    public void printFeeAndInterest() {
    }


//have to do JUnit test for this
    /**
     * Testbed main for testing the open() & close()
     * methods in this class
     *
     * @param args Commandline arguments
     */
}