//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

/**
 * Static holder for the HospitalSystem doctors map
 */
public class DoctorMapAccess {
    private static TreeMap<String, Doctor> dictionary;

    private DoctorMapAccess() {}

    public static TreeMap<String, Doctor> dictionary() {
        if (dictionary == null) {
            dictionary = new TreeMap<String, Doctor>();
        }
        return dictionary;
    }
}
