//Dawson Wiebe drw529 11226441
import java.util.Scanner;

public class ConsoleIO implements InputOutputInterface {

    /**
     * Scanner object used to communicate with the console
     */
    private static Scanner scan;

    /**
     * Constructs the console scanner for IO
     * @param s pre-initialized Scanner
     */
    public ConsoleIO(Scanner s) {
        if (s == null) {
            scan = new Scanner(System.in);
        } else {
            scan = s;
        }
    }

    /**
     * Prompts the user for text input
     * @param prompt the string to be displayed as a prompt
     * @return user input
     */
    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    /**
     * Prompts the user for numeric input
     * @param prompt the string to be displayed as a prompt
     * @return user input
     */
    public int readInt(String prompt) {
        System.out.print(prompt + ": ");
        int value = scan.nextInt();
        scan.nextLine(); //clears input buffer
        return value;
    }

    /**
     * Allows the user to select a series of option via dropdown menu
     * @param options an array with the options that are presented to the user
     * @return the selected option
     */
    public int readChoice(String[] options) {
        int task;
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ".\t" + options[i]);
        }
        System.out.print("Task number: ");
        task = scan.nextInt();
        scan.nextLine(); //clear input buffer
        return task;
    }

    /**
     * Displays a message to the user
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        System.out.println(outString);
    }

}
