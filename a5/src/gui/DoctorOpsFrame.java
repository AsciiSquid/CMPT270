package gui;

import javax.swing.JFrame;

public class DoctorOpsFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    public DoctorOpsFrame() {
        setTitle("Doctor Operations");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        DoctorOpsPanel panel = new DoctorOpsPanel();
        add(panel);
    }
}
