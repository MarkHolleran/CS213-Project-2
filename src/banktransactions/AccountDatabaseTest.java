/*
package banktransactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {

    @Test
    void open() {

        AccountDatabase database = new AccountDatabase();

        Date testDate = new Date("4/21/1999");
        Profile testProfile = new Profile("Mark", "Holleran", testDate);
        Account testSavings = new Savings (testProfile, 1000, 1);

        //Test 1: Entering a valid

        assertTrue (database.open(testSavings));

        //Test 2: Entering an already existing account into the database
        //using the same database from test 1

        assertFalse (database.open(testSavings));

        //Test 3: Entering Two accounts everything same except first name in Profile data
        //using the same database from test 1

        Profile testProfile3 = new Profile("Richard", "Holleran", testDate);
        Account testSavings3 = new Savings (testProfile3, 1000, 1);
        assertTrue(database.open(testSavings3));

        //Test 4: Entering two accounts everything same except date of birth
        //using database from test 4
        AccountDatabase database2 = new AccountDatabase();

        Date testDate4 = new Date("4/20/1968");
        Profile testProfile4 = new Profile("Mark", "Holleran", testDate4);
        Account testSavings4 = new Savings (testProfile4, 1000, 1);
        assertTrue(database2.open(testSavings4));

        //Test 5: Creating a Checking account when a savings account already exists for profile
        //using database from test 4
        Account testChecking5 = new Checking(testProfile, 2000);
        assertTrue(database2.open(testChecking5));

        //Test 6: Creating a Money market account when a savings and checking account already exist for profile
        //using database from test 4
        Account testMoneyMarket6 = new MoneyMarket(testProfile, 70749);
        assertTrue(database2.open(testMoneyMarket6));

        //Test 7: Creating a College checking account when a checking account exists
        //using database from test 4
        Account testCollegeChecking7 = new CollegeChecking(testProfile,6290, 0);
        assertFalse(database2.open(testCollegeChecking7));

        //Test 8: Creating a checking account when a College checking already exists
        //using database from test 4
        Date testDate8 = new Date("1/11/2011");
        Profile testProfile8 = new Profile("Greg", "Richardson", testDate8);

        Account collegeCheckingtest8 = new CollegeChecking(testProfile8, 3943, 2);
        database2.open(collegeCheckingtest8);
        Account checkingTest8 = new Checking(testProfile8, 8000);
        assertFalse(database2.open(checkingTest8));

        //Test 9: Creating a college checking account with a greater than 2 campus code
        Account collegeCheckingtest9 = new CollegeChecking(testProfile,2030, 3);
        assertFalse(database2.open(collegeCheckingtest9));

        //Test 10: reopening a previously closed account
        Date testDate10 = new Date("1/11/2011");
        Profile testProfile10 = new Profile("wendys", "coupon", testDate10);
        Account testChecking10 = new Checking(testProfile10, 10000);

        database2.open(testChecking10);
        database2.close(testChecking10);

        assertTrue(database2.open(testChecking10));

    }

    @Test
    void close() {

        //Test 1: Opening and closing an account
        AccountDatabase testDatabase1 = new AccountDatabase();
        Date testDate8 = new Date("1/11/2011");
        Profile testProfile1 = new Profile("Greg", "Richardson", testDate8);
        Checking testChecking1 = new Checking(testProfile1, 3);
        testDatabase1.open(testChecking1);
        assertTrue(testDatabase1.close((testChecking1)));

        //Test 2: Attempting to close an already closed account
        assertFalse(testDatabase1.close((testChecking1)));

        //Test 3: Attempting to close an account that doesn't exist
        Date testDate3 = new Date("3/5/1980");
        Profile testProfile3 = new Profile("will", "train", testDate8);
        MoneyMarket testMoneyMarket3 = new MoneyMarket(testProfile1, 13032);
        assertFalse(testDatabase1.close((testMoneyMarket3)));

    }
}
*/