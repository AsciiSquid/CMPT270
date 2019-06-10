package containers;

import java.util.*;

import entities.Patient;

/**
 * A singleton class to access the dictionary containing the patients.
 */
public class PatientMapAccess {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private PatientMapAccess() {}

    /** The dictionary for patients. */
    private static TreeMap<Integer, Patient> dictionary;

    /**
     * Return the dictionary that maps health numbers to patients.
     *
     * @return the dictionary that maps health numbers to patients
     */
    public static TreeMap<Integer, Patient> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new TreeMap<Integer, Patient>();
        }
        return dictionary;
    }
}
