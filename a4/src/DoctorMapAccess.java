//Dawson Wiebe drw529
import java.util.TreeMap;

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
