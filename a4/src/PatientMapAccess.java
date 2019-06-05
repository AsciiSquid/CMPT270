//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

/**
 * static Patient map container for the HospitalSystem class
 */
public class PatientMapAccess {
    private static TreeMap<Integer, Patient> dictionary;

    private PatientMapAccess() {}

    public static TreeMap<Integer, Patient> dictionary() {
        if (dictionary == null) {
            dictionary = new TreeMap<Integer, Patient>();
        }
        return dictionary;
    }
}
