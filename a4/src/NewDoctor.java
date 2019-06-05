//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

public class NewDoctor extends CommandStatus{

    public NewDoctor(String name, boolean isSurgeon) {
        TreeMap<String, Doctor> dictionary = DoctorMapAccess.dictionary();
        if (dictionary.containsKey(name)) {
            super.successful = false;
            super.errorMessage = name + " is already in the system";
            return;
        }
        Doctor newDoctor;
        if (isSurgeon) {
            newDoctor = new Surgeon(name);
        } else {
            newDoctor = new Doctor(name);
        }
        dictionary.put(name, newDoctor);
        super.successful = true;
    }
}
