//Dawson Wiebe drw529 11226441
package gui;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 300;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 180;

    public MenuFrame() {
        setTitle("Hospital System");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MenuPanel panel = new MenuPanel();
        add(panel);
    }
}
