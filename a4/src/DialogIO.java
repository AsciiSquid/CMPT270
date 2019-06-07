//Dawson Wiebe drw529 11226441
import javax.swing.*;

public class DialogIO extends AbstractDialogIO {

    public DialogIO() {}

    /**
     * Prompts the user for text input
     * @param prompt the string to be displayed as a prompt
     * @return user input
     */
    public String readString(String prompt) {
        String selection = JOptionPane.showInputDialog(
                null,
                prompt,
                "Title",
                JOptionPane.QUESTION_MESSAGE
        );
        if (selection == null) {
            return readString(prompt);
        } else {
            return selection;
        }
    }

    /**
     * Prompts the user for numeric input
     * @param prompt the string to be displayed as a prompt
     * @return user input
     */
    public int readInt(String prompt) {
        String selection = JOptionPane.showInputDialog(
                null,
                prompt,
                "Input a number",
                JOptionPane.QUESTION_MESSAGE
        );
        int value;
        try {
            value = Integer.parseInt(selection);
            return value;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, selection + " is not a number");
            return readInt(prompt);
        }
    }

    /**
     * Displays a message to the user
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }

}
