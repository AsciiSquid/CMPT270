//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

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
