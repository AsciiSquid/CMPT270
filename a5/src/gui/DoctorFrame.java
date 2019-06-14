//Dawson Wiebe drw529 11226441
package gui;

import javax.swing.*;

import entities.Doctor;
import containers.DoctorMapAccess;
import entities.Surgeon;

/**
 * Window frame to hold the DoctorPanel
 */
public class DoctorFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Generates the doctor window based on the provided Doctor class
     * @param name Name of the doctor to retrieve
     */
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
