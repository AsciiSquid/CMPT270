package gui;

import javax.swing.*;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        setTitle("Hospital System");
        MenuPanel panel = new MenuPanel();
        add(panel);
    }
}
