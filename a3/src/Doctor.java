//Dawson Wiebe drw529 11226441
import java.util.HashMap;

/**
 * Information on doctors and their patients
 * !All doctors must have a unique name!
 */
public class Doctor extends BasicDoctor {
    /**
     * Map of patients currently in the doctors care
     */
    protected HashMap<Integer, Patient> patients;

    /**
     * Constructor, inherits from BasicDoctor
     * @param name - Doctors name !must be unique!
     */
    public Doctor(String name){
        super(name);
        this.patients = new HashMap<>();
    }

    /**
     * Adds a patient to the doctors map
     * @param p - Unique Patient class
     */
    public void addPatient(Patient p) {
        this.patients.putIfAbsent(p.getHealthNumber(), p);
    }

    /**
     * Removes a patient from the doctors map
     * @param healthNum
     */
    public void removePatient(int healthNum) {
        this.patients.remove(healthNum);
    }

    /**
     * Checks if a patient is already in the doctors map
     * @param healthNum - Patient health number
     * @return if the patient is already in the map
     */
    public boolean hasPatient(int healthNum) {
        return this.patients.containsKey(healthNum);
    }

    @Override
    public String toString() {
        String str = "-Doctor-" + super.toString();
        str += "Patients:\n";
        for (Patient pat : this.patients.values()) {
            str += "\t" + pat.getName() + "\n";
        }
        return str;
    }

    /**
     * Generates a hash code using the name string
     * @return - hash code
     */
    public int hashCode() {
        int hash=0;
        int increment=0;
        for (char c : super.getName().toCharArray()) {
            hash += (int) c * 10 ^ increment++;
        }
        return hash;
    }

    public boolean equals(Object obj) {
        return obj instanceof Doctor && this.hashCode() == obj.hashCode();
    }

    /**
     * Tests methods of the Doctor class
     * @param args - unused
     */
    public static void main(String[] args) {
        Doctor doc = new Doctor("Dr. Debug");
        System.out.println("Doctor Dr. Debug is...\n" + doc);
        Patient p = new Patient("Patient 0", 0);
        doc.addPatient(p);
        if (! doc.hasPatient(p.getHealthNumber())) {
            System.out.println("addPatient or hasPatient: cannot find " + p.getName() + " after assignment");
        }
        doc.removePatient(p.getHealthNumber());
        if (doc.hasPatient(p.getHealthNumber())) {
            System.out.println("removePatient or hasPatient: found " + p.getName() + " after removal");
        }
    }
}
