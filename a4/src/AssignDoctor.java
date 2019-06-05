//Dawson Wiebe drw529 11226441
import java.util.TreeMap;

public class AssignDoctor extends CommandStatus {
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
