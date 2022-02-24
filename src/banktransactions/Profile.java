package banktransactions;

/**
 * Class that stores date of birth, first name, and last name
 * The Profile object stores the first and last name as a String object
 * and the date of birth as a Date object
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class Profile {

    private String fname;
    private String lname;
    private Date dob;
    //says we can't add instance variables
    //says nothing about changing the type so changed from String dob to Date dob

    //should also define equals and tostring methods
    //cannot add other instance variables


    /**
     * Method that constructs a Profile Object
     * Takes in first name, last name, and date of birth
     * and constructs a Profile object
     *
     * @param fname first name of account holder as String
     * @param lname last name of account holder as String
     * @param dob date of birth of account holder as Date object
     */
    public Profile (String fname, String lname, Date dob){

        this.fname = fname;
        this.lname = lname;
        this.dob = dob;

    }

    /**
     * Method used for comparing two Profile objects
     * First name, last name, and date of birth are compared
     * between the two Profile objects.
     *
     * @param compareProfile Profile object for comparison with another Profile object
     * @return true if Profile objects are the same, false otherwise
     */
    public boolean equals(Profile compareProfile){

        return (this.fname.equals(compareProfile.fname) && this.lname.equals(compareProfile.lname) && this.dob.compareTo(compareProfile.dob) == 0);

    }

    /**
     * Prints a Profile object in String form
     * by returning fname, lname, and dob
     * separated by a space as a String
     *
     * @return String representation of Profile object
     */
    public String toString(){

        return this.fname + " " + this.lname + " " + this.dob.toString();

    }

    public static void main(String[] args){

        Date test1 = new Date("1/4/1996");
        Date test2 = new Date("1/4/1996");

        Profile testProfile1 = new Profile("Mark","Holleran", test1);
        Profile testProfile2 = new Profile("Mark", "Holleran", test2);

        System.out.println(testProfile1.toString());
        System.out.println(testProfile2.toString());

        System.out.println(testProfile1.equals(testProfile2));
        
    }
}
