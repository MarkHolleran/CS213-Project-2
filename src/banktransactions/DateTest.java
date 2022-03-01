
package banktransactions;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @org.junit.jupiter.api.Test
    void isValid() {

        //Test 1: Month is > 12
        Date test1 = new Date("13/13/2022");
        assertFalse(test1.isValid());

        //Test 2: Month is < 1
        Date test2 = new Date("0/13/2022");
        assertFalse(test2.isValid());

        //Test 3: Day is over max day for a month with a max of 31 days
        Date test3 = new Date("12/32/2022");
        assertFalse(test3.isValid());

        //Test 4: Day is over max day for a month with a max of 30 days  (september )
        Date test4 = new Date("4/31/2022");
        assertFalse(test4.isValid());

        //Test 5: day is less than 1
        Date test5 = new Date("5/0/2022");
        assertFalse(test5.isValid());

        //Test 6: year is above max year (2022)
        Date test6 = new Date("4/12/2024");
        assertFalse(test6.isValid());

        //Test 7: No input for date (default constructor for today's date)
        Date test7 = new Date();
        assertTrue(test7.isValid());

        //Test 8: 29 days for february on a leapyear
        Date test8 = new Date("2/29/2020");
        assertTrue(test8.isValid());

        //Test 9: 28 days in february when non leapyear
        Date test9 = new Date("2/29/2021");
        assertFalse(test9.isValid());

        //Test 10: Testing regular day
        Date test10 = new Date("2/20/2021");
        assertTrue(test10.isValid());

    }

}

