//Dawson Wiebe drw529 11226441
import java.util.HashMap;

/**
 * Holds information for record patients
 * !Each patient must have a unique health card number!
 */
public class Patient extends Person {
    /**
     * Stores the bedlabel a patient occupies when contained within a Ward
     */
    private int bedLabel;
    /**
     * A map of Doctor classes assigned to the Patient
     */
    protected HashMap<String, Doctor> doctors;

    /**
     * Creates the Patient class
     * @param name - Patient name
     * @param number - Patient health card number !must be unique!
     */
    public Patient(String name, int number) {
        super(name, number);
        this.bedLabel = -1;
        this.doctors = new HashMap<>();
    }

    /**
     * Gets the bed label the Person is occupying
     * @return a bedLabel index, or -1 if unassigned
     */
    public int getBedLabel() {
        return bedLabel;
    }

    /**
     * Sets the occupying bed label
     * @param bedLabel - bedLabel index or -1 if unoccupied
     */
    public void setBedLabel(int bedLabel) {
        this.bedLabel = bedLabel;
    }

    /**
     * Adds a doctor to the map
     * @param d - Doctor class
     */
    public void addDoctor(Doctor d) {
        this.doctors.put(d.getName(), d);
    }

    /**
     * Removes a doctor from the map
     * @param name - Name of Doctor class
     */
    public void removeDoctor(String name) {
        this.doctors.remove(name);
    }

    /**
     * Checks if a doctor is present within the map
     * @param name - Name of the Doctor class
     * @return if the doctor is present in the map
     */
    public boolean hasDoctor(String name) {
        return this.doctors.containsKey(name);
    }

    @Override
    public String toString() {
        String str = super.toString();
        if (this.bedLabel == -1) {
            str += "Bed: " + this.bedLabel + "\n";
        }
        str += "Doctors:\n";
        for (Doctor doct : this.doctors.values()) {
            str += "\t" + doct.getName() + "\n";
        }
        return str;
    }

    /**
     * Generates the class hash code using the health card number
     * @return - hash code
     */
    public int hashCode() {
        return super.getHealthNumber();
    }

    public boolean equals(Object obj) {
        return obj instanceof Patient && this.hashCode() == obj.hashCode();
    }

    /**
     * Tests the methods of the Patient class
     * @param args - unused
     */
    public static void main(String[] args) {
        Patient pat = new Patient("Patty", 64);
        System.out.println("Patient Patty is...\n" + pat);
        int testLabel = -1;
        if (pat.getBedLabel() != testLabel) {
            System.out.println("getBedLabel: returns " + pat.getBedLabel());
        }
        testLabel = 1000101;
        pat.setBedLabel(testLabel);
        if (pat.getBedLabel() != testLabel) {
            System.out.println("setBedLabel: receives " + pat.getBedLabel() + ", assigned to " + testLabel);
        }
        Doctor doc = new Doctor("Dr. Debug");
        pat.addDoctor(doc);
        if (! pat.hasDoctor(doc.getName())) {
            System.out.println("addDoctor or hasDoctor: could not find " + doc.getName() + " when added");
        }
        pat.removeDoctor(doc.getName());
        if (pat.hasDoctor(doc.getName())) {
            System.out.println("removeDoctor or hasDoctor: found " + doc.getName() + " after removal");
        }
    }
}
