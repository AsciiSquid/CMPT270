package commands;

import entities.Patient;
import entities.Ward;
import containers.PatientMapAccess;
import containers.WardAccess;

/**
 * Command to release a patient from his/her bed in the ward.
 */
public class ReleasePatient extends CommandStatus {
    /**
     * Release a patient from his/her bed in the ward.
     *
     * @param patientNum the health number of the patient to be released
     */
    public void releasePatient(int patientNum) {
        if (!PatientMapAccess.dictionary().containsKey(patientNum)) {
            successful = false;
            errorMessage =
                    "The patient must be already in the system.  There is"
                            + " no patient with the health number " + patientNum;
            return;
        }
        Patient p = PatientMapAccess.dictionary().get(patientNum);

        if (p.getBedLabel() == -1) {
            successful = false;
            errorMessage = "The patient must already have a bed.";
            return;
        }

        Ward ward = WardAccess.ward();
        int bedLabel = p.getBedLabel();
        if (ward.getPatient(bedLabel) != p) {
            successful = false;
            errorMessage =
                    "The patient is not in the bed stored" + " with the patient.  Bed " + bedLabel
                            + " has patient " + ward.getPatient(bedLabel);
            return;
        }

        ward.freeBed(bedLabel);
        p.setBedLabel(-1);
        successful = true;
    }
}

