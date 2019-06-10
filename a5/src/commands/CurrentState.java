package commands;

import containers.PatientMapAccess;
import containers.DoctorMapAccess;
import containers.WardAccess;

import java.util.Collection;
import entities.Patient;
import entities.Doctor;

/**
 * Command to obtain a String representation of the current state of the system. The current state
 * is placed in the curState field.
 */
public class CurrentState extends CommandStatus {
    /**
     * A string containing the current state of the system.
     */
    private String curState;

    /**
     * Obtain the current state of the system and place the string in curState.
     */
    public void findCurState() {
        curState = "\nThe patients in the system are \n";
        Collection<Patient> patients = PatientMapAccess.dictionary().values();
        for (Patient p : patients)
            curState = curState + p;
        curState = curState + "\nThe doctors in the system are \n";
        Collection<Doctor> doctors = DoctorMapAccess.dictionary().values();
        for (Doctor d : doctors)
            curState = curState + d;
        curState = curState + "\nThe ward is " + WardAccess.ward();
        successful = true;
    }

    /**
     * Return a string containing the state of the system.
     *
     * @precond wasSuccessful()
     * @return a string containing the state of the system
     */
    public String getCurState() {
        if (!wasSuccessful())
            throw new RuntimeException("The method findCurState() must be "
                    + "invoked before this method");
        return curState;
    }
}
