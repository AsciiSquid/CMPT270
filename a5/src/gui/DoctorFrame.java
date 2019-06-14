//Dawson Wiebe drw529 11226441
package gui;

import javax.swing.*;

import entities.Doctor;
import containers.DoctorMapAccess;
import entities.Surgeon;

public class DoctorFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 350;
    public static final int DEFAULT_HEIGHT = 400;

    public DoctorFrame(String name) {
        Doctor doctor = DoctorMapAccess.dictionary().get(name);
        if (doctor != null) {
            setTitle(doctor.getName() + " (" + (doctor instanceof Surgeon ? "Surgeon":"Doctor") + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        } else
            throw new RuntimeException("Invalid doctor name: " + name);
    }

}
