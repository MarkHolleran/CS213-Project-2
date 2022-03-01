
package banktransactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest {

    @Test
    void testMonthlyInterest() {

        Date testDate = new Date("4/21/1999");
        Profile testProfile = new Profile("Mark","Holleran", testDate);
        //these two have been constructed and will be used in all test cases

        //Test 1: Interest as a loyal customer (balance over 2500)
        MoneyMarket test1 = new MoneyMarket(testProfile, 10000);
        assertEquals(10095.0 , test1.monthlyInterest());

        //Test 2: Interest as a non loyal customer (balance less than 2500)
        MoneyMarket test2 = new MoneyMarket(testProfile, 1000);
        assertEquals(1008.0 , test2.monthlyInterest());

        //Test 3: Interest as a non loyal customer (with balance of 0)
        MoneyMarket test3 = new MoneyMarket(testProfile, 0);
        assertEquals(0 , test2.monthlyInterest());

        //Test 4: Interest as a loyal customer (balance at 2500)
        MoneyMarket test4 = new MoneyMarket(testProfile, 2500);
        assertEquals(2523.75 , test1.monthlyInterest());
        

    }

}

