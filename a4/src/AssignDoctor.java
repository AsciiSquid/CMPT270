//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

/**
 * Handles the command for doctor-patient assignment
 */
public class AssignDoctor extends CommandStatus {
    /**
     * Executes the task and stores the result
     * @param healthNum - health number of a patient
     * @param docName - name of a doctor
     */
    public AssignDoctor(int healthNum, String docName) {
        TreeMap<Integer, Patient> patientDict = PatientMapAccess.dictionary();
        TreeMap<String, Doctor> doctorDict = DoctorMapAccess.dictionary();

        Patient p = patientDict.get(healthNum);
        Doctor d = doctorDict.get(docName);

        if (p == null) {
            super.errorMessage = "There is no patient with health number " + healthNum;
        } else if (d == null) {
            super.errorMessage = "There is no doctor with name " + docName;
        }
        super.successful = (p != null && d != null);
        if (super.successful) {
            p.addDoctor(d);
            d.addPatient(p);
        }
    }
}
