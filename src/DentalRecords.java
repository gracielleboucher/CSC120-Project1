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
     * @param familySize the Scanner to read from
     * @param teeth the 1D array of family member names
     * @param teeth the 3D array of tooth type
     */
    public static void reportRootCanals(int familySize, char[][][] teeth){

    }

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
     * Finds the array index of a family member by name, ignoring case.
     *
     * @param names the 1D array of family nember names
     * @param answer the name to look for
     * @return the index of the member, or -1 if not found
     */
    public static int findMember(String[] names, String answer) {
        int index;

        for(index =0; index < names.length; index++) {
            if (names[index].equalsIgnoreCase(answer)) {
                return index;
            } //end if
        } //end for
        return -1;
    }  //end findMember

    /**
     * Extracts a tooth: gets the family member, tooth layer, and tooth number,
     * validates each, checks the tooth is not already missing, and
     * converts that tooth to type M in the array.
     *
     * @param keyboard the Scanner to read from
     * @param names the 1D array of family members names
     * @param teeth the 3D array of tooth types
     */
    public static void extractTooth(Scanner keyboard, String[] names, char[][][] teeth) {
        String answer;
        int member;
        int layer;
        char layerAnswer;
        int toothNumber;

        printPrompt("Which family member");
        member = findMember(names, keyboard.next());
        while (member < 0) {
            printPrompt("Invalid family member, try again");
            member = findMember(names, keyboard.next());
        } //end while

        printPrompt("Which tooth layer (U)pper or (L)ower");
        layerAnswer = Character.toUpperCase(keyboard.next().charAt(0));
        while (layerAnswer != 'U' && layerAnswer != 'L') {
            printPrompt("Invalid layer, try again");
            layerAnswer = Character.toUpperCase(keyboard.next().charAt(0));
        } //end while
        if (layerAnswer == 'U') {
            layer = 0;
        } else {
            layer = 1;
        } //end if

        printPrompt("Which tooth number");
        toothNumber = keyboard.nextInt();

        while (toothNumber < 1 || toothNumber > MAX_TEETH) {
            printPrompt("Invalid tooth number, try again");
            toothNumber = keyboard.nextInt();
        } //end while


        while (teeth[member][layer][toothNumber - 1] == 0 || teeth[member][layer][toothNumber - 1] == 'M') {
            if (teeth[member][layer][toothNumber - 1] == 'M') {
                printPrompt("Missing tooth, try again");
            } else {
                printPrompt("Invalid tooth number, try again");
            } //end if
            toothNumber = keyboard.nextInt();
        } //end while

        teeth[member][layer][toothNumber - 1] = 'M';

        System.out.println();
    } //end extractTooth

    /**
     * Reports the family's root canal indices: adds up the total number
     * of I, B, and M teeth in the family, then finds and displays both roots of Ix^2 + Bx - M
     * using those totals.
     *
     * @param teeth the 3D array of tooth types
     */

    /**
     * Prints each family member's name followed by their uppers and
     * lowers tooth types, numbered per tooth.
     *
     * @param names the 1D array of family members
     * @param teeth the 3D array of tooth types
     */
    public static void printRecord(String[] names, char[][][] teeth) {
        int member;
        int index;

        System.out.println();

        for(member = 0; member < names.length; member++) {
            System.out.println(names[member] + ":");

            System.out.print(" Uppers: ");
            index = 0;
            while (index < MAX_TEETH && teeth[member][0][index] != 0){
                System.out.printf("%2d:%c  ", index + 1, teeth[member][0][index]);
                index = index + 1;
            } //end while
            System.out.println();

            System.out.print(" Lowers: ");
            index = 0;
            while (index < MAX_TEETH && teeth[member][1][index] != 0) {
                System.out.printf("%2d:%c  ", index + 1, teeth[member][1][index]);
                index = index + 1;
            } //end while
            System.out.println();

            System.out.println();
        } //end for
    } //end printRecord

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
            if (option == 'P') {
                printRecord(names, teeth);
            } else if (option == 'E') {
                extractTooth(keyboard, names, teeth);
            } else if (option == 'R') {
                reportRootCanals(familySize, teeth);
            } //end if
        } while (option != 'X') ;

        System.out.println();
        System.out.println("Exiting the Floridian Tooth Records :-)");
    } //end of the main method

} //end of the DentalRecords class
