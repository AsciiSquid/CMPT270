//Dawson Wiebe drw529 11226441

import java.util.Scanner;

public class ConsoleIO implements InputOutputInterface {

    private static Scanner scan;

    public ConsoleIO(Scanner s) {
        if (s == null) {
            scan = new Scanner(System.in);
        } else {
            scan = s;
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt + ": ");
        int value = scan.nextInt();
        scan.nextLine(); //clears input buffer
        return value;
    }

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

    public void outputString(String outString) {
        System.out.println(outString);
    }

}
