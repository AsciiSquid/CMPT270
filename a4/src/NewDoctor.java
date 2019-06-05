//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

/**
 * Executes the newDoctor command for the HospitalSystem and stores the result
 */
public class NewDoctor extends CommandStatus{

    /**
     * Executes the task and stores the result
     * @param name - name of the Doctor
     * @param isSurgeon - determines if the new doctor is part of the Surgeon subclass
     */
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
