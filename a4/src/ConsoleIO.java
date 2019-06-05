//Dawson Wiebe drw529 11226441

import java.util.Scanner;

public class ConsoleIO implements InputOutputInterface {

    private Scanner scan;

    public ConsoleIO(Scanner scan) {
        this.scan = scan;
    }

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return this.scan.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt + ": ");
        int value = this.scan.nextInt();
        this.scan.nextLine(); //clears input buffer
        return value;
    }

    public int readChoice(String[] options) {
        int task;
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ".\t" + options[i]);
        }
        System.out.print("Task number: ");
        task = this.scan.nextInt();
        this.scan.nextLine(); //clear input buffer
        return task;
    }

    public void outputString(String outString) {
        System.out.println(outString);
    }
}
