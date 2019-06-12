package gui;

import javax.swing.JFrame;

public class DoctorAddFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 350;
    public static final int DEFAULT_HEIGHT = 200;

    public DoctorAddFrame() {
        setTitle("Doctor Addition");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        DoctorAddPanel panel = new DoctorAddPanel();
        add(panel);
    }
}
