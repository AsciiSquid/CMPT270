//Dawson Wiebe drw529 11226441
import javax.swing.*;

public class DialogIO extends AbstractDialogIO {

    public DialogIO() {}

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

    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }
}
