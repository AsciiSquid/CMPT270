package containers;

import java.util.*;

import entities.Doctor;

/**
 * A singleton class to access the dictionary containing the doctors.
 */
public class DoctorMapAccess {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private DoctorMapAccess() {}

    /** The dictionary for doctors. */
    private static TreeMap<String, Doctor> dictionary;

    /**
     * Return the dictionary that maps names to doctors.
     *
     * @return the dictionary that maps names to doctors
     */
    public static TreeMap<String, Doctor> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new TreeMap<String, Doctor>();
        }
        return dictionary;
    }
}
