import java.util.Scanner;

/**
 * Dental Records Project - CSC120 Project 1
 *
 * Records the teeth of one Florida family: each member's name
 * and tooth-type strings for their uppers and lowers, then
 * offers a menu to print the record, extract a tooth, or
 * report root canals.
 *
 * @author Gracielle Boucher
 * @version 1.0 October 2026
 */

public class DentalRecords {
    public static final int MAX_FAMILY = 6;

    /**
     * Asks for the number of people in the family and validates
     * that it is between 1 and 6, re-asking until valid.
     *
     * @param keyboard the Scanner to read from
     * @return the validated family size (1 to 6)
     */

    public static int getFamilySize(Scanner keyboard) {
        int familySize;

        System.out.print("Please enter number of people in the family : ");
        familySize = keyboard.nextInt();

        while (familySize < 1 || familySize > MAX_FAMILY) {
            System.out.print("Invalid number of people, try again         : ");
            familySize = keyboard.nextInt();
        } //end while

        return familySize;
    } //end getFamilySize

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int familySize;

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");

        familySize = getFamilySize(keyboard);
    } //end of the main method

} //end of the DentalRecords class
