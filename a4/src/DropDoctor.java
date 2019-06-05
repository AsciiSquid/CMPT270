//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

public class DropDoctor extends CommandStatus {
    public DropDoctor(int healthNum, String docName) {
        TreeMap<Integer, Patient> patientDict = PatientMapAccess.dictionary();
        TreeMap<String, Doctor> doctorDict = DoctorMapAccess.dictionary();

        Patient p = patientDict.get(healthNum);
        Doctor d = doctorDict.get(docName);

        if (p == null) {
            super.errorMessage = "There is no patient with health number " + healthNum;
            return;
        } else if (d == null) {
            super.errorMessage = "There is no doctor with name " + docName;
            return;
        }

        if (!d.hasPatient(healthNum)) {
            super.errorMessage = "This doctor is not associated with this patient.";
            return;
        } else if (!p.hasDoctor(docName)) {
            super.errorMessage = "This doctor and this patient are incorrectly "
                    + "associated.  The doctor has the patient, "
                    + "but the patient does not have the doctor";
            return;
        }
        super.successful = (d.hasPatient(healthNum) && p.hasDoctor(docName));
        if (super.successful) {
            p.removeDoctor(docName);
            d.removePatient(healthNum);
        }
    }
}
