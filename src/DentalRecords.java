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

    /** Maximum number of people in the family. */
    public static final int MAX_FAMILY = 6;

    /** Maximum number of teeth in one layer (uppers or lowers). */
    public static final int MAX_TEETH = 8;

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

    /**
     * Prints a prompt padded with spaces to 44 characters so the colons
     * of all prompts line up, matching the sample run.
     *
     * @param prompt the prompt text without the colon
     */
    public static void printPrompt(String prompt) {
        while (prompt.length() < 44) {
            prompt = prompt + " ";
        } //end while
        System.out.print(prompt + ": ");
    } //end printPrompt

    /**
     * Checks whether every character in a tooth-type string is I, B, or M.
     *
     * @param teeth the string to check
     * @return true if all characters are valid tooth types
     */
    public static boolean isValidTeeth(String teeth) {
        int index;

        for (index = 0; index < teeth.length(); index++) {
            if (teeth.charAt(index) != 'I' && teeth.charAt(index) != 'B' &&
            teeth.charAt(index) != 'M') {
                return false;
            } //end if
        } //end for
        return true;
    } //end isValidTeeth

    /**
     * Reads one tooth-type string (uppers or lowers), validating that
     * every character is I, B, or M and that there are at most 8 teeth,
     * re-asking until valid.
     *
     * @param keyboard the Scanner to read from
     * @param prompt the prompt text for this layer
     * @return the validated tooth type string in upper case
     */
    public static String getToothRow(Scanner keyboard, String prompt) {
        String teeth;

        printPrompt(prompt);
        teeth = keyboard.next().toUpperCase();

        while (!isValidTeeth(teeth) || teeth.length() > MAX_TEETH) {
            if (!isValidTeeth(teeth)) {
                printPrompt("Invalid teeth types, try again");
            } else {
                printPrompt("Too many teeth, try again");
            } //end if
            teeth = keyboard.next().toUpperCase();
        } //end while
        return teeth;
    } //end getToothRow

    /**
     * For each family member, gets their name and their uppers and lowers
     * tooth-type strings, and stores them in the arrays.
     *
     * @param keyboard the Scanner to read from
     * @param names the 1D array of family member names
     * @param teeth the 3D array of tooth type
     */
    public static void getFamilyInfo(Scanner keyboard, String[] names, char[][][] teeth) {
        int member;
        int index;
        String row;

        for (member = 0; member < names.length; member++){
            printPrompt("Please enter the name for family member " + (member + 1));
            names[member] = keyboard.next();

            row = getToothRow(keyboard, "Please enter the uppers for " + names[member]);
            for (index = 0; index < row.length(); index++){
                teeth[member][0][index] = row.charAt(index);
            } //end for

            row = getToothRow(keyboard, "Please enter the lowers for " + names[member]);
            for (index = 0; index < row.length(); index++){
                teeth[member][1][index] = row.charAt(index);
            } //end for
        } //end for
    } //end getFamilyInfo

    /**
     * Prints the menu prompt, validates that the choice is P, E, R, or X
     * (upper or lower case), and re-asks until valid.
     *
     * @param keyboard the Scanner to read from
     * @return the uppercase menu choice
     */
    public static char getMenuChoice(Scanner keyboard) {
        String answer;
        char option;

        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it          : ");
        answer = keyboard.next();
        option = Character.toUpperCase(answer.charAt(0));

        while (option != 'P' && option != 'E' && option != 'R' && option != 'X') {
            System.out.print("Invalid menu option, try again              : ");
            answer = keyboard.next();
            option = Character.toUpperCase(answer.charAt(0));
        } //end while
        return option;
    } //end getMenuChoice

    /**
     * Program entry point: collects the family's dental records, then
     * runs the menu loop until the user chooses to exit.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int familySize;
        char option;
        String [] names;
        char[][][] teeth;

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");

        familySize = getFamilySize(keyboard);
        names = new String[familySize];
        teeth = new char[familySize][2][MAX_TEETH];
        getFamilyInfo(keyboard, names, teeth);

        System.out.println();
        do {
            option = getMenuChoice(keyboard);
        } while (option != 'X');

        System.out.println();
        System.out.println("Exiting the Floridian Tooth Records :-)");
    } //end of the main method

} //end of the DentalRecords class
