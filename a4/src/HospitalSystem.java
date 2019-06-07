//Dawson Wiebe drw529 11226441
import javax.swing.*;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystem
{
    /**
     * The keyed dictionary of all patients.
     */
    private TreeMap<Integer, Patient> patients;

    /**
     * The keyed dictionary of all doctors.
     */
    private TreeMap<String, Doctor> doctors;

    /**
     * The ward to be handled.
     */
    private Ward ward;

    private static InputOutputInterface ioInterface;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relevant information
     */
    public HospitalSystem() {

        patients = PatientMapAccess.dictionary();
        doctors = DoctorMapAccess.dictionary();

        int interfaceOption = JOptionPane.showConfirmDialog(
                null,
                "Use dialog interface?\n"
                        + "(Select No to open terminal interface)",
                "System Interface",
                JOptionPane.YES_NO_CANCEL_OPTION
        );
        switch (interfaceOption) {
            case 0:
                ioInterface = new DialogIO();
                break;
            case 1:
                ioInterface = new ConsoleIO(new Scanner(System.in));
                break;
            default:
                return;
        }

        ioInterface.outputString("Initializing the system...");
        ioInterface.outputString("Getting Ward information...");

        String name = ioInterface.readString("Enter the name of the Ward");
        int firstBedNum = ioInterface.readInt("Enter the integer label of the first bed");
        int lastBedNum = ioInterface.readInt("Enter the integer label of the last bed");

        WardAccess.initialize(name, firstBedNum, lastBedNum);
        ward = WardAccess.ward();
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        ioInterface.outputString("Getting Patient information...");
        String name = ioInterface.readString("Enter the name of the patient");

        int healthNum = ioInterface.readInt("Enter the health number of the patient");

        if (patients.containsKey(healthNum))
        {
            throw new RuntimeException("Patient with the health number " + healthNum + " already exists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = patients.put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                patients.put(healthNum, result);  // put the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        ioInterface.outputString("Getting Doctor information...");
        String name = ioInterface.readString("Enter the name of the doctor");

        String response = ioInterface.readString("Is the doctor a surgeon? (yes or no)");
        boolean isSurgeon = response.charAt(0) == 'y' || response.charAt(0) == 'Y';

        new NewDoctor(name, isSurgeon);
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        ioInterface.outputString("Assigning a new Doctor-Patient Association...");
        ioInterface.outputString("Getting Patient information...");
        int healthNumber = ioInterface.readInt("Enter the health number of the patient");

        ioInterface.outputString("Getting Doctor information...");
        String name = ioInterface.readString("Enter the name of the doctor");

        new AssignDoctor(healthNumber, name);
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
         

        ioInterface.outputString("Assigning a Patient to a Bed...");
        ioInterface.outputString("Getting Patient information...");
        int healthNumber = ioInterface.readInt("Enter the health number of the patient");

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = ioInterface.readInt("Enter the bed number for the patient");
        if (bedNum < ward.getMinBedLabel() || bedNum > ward.getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ward.getMinBedLabel()
                    + " and " + ward.getMaxBedLabel());

        p.setBedLabel(bedNum);
        ward.assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
         

        ioInterface.outputString("Dropping a new Doctor-Patient Association...");
        ioInterface.outputString("Getting Patient information...");
        int healthNumber = ioInterface.readInt("Enter the health number of the patient");

        ioInterface.outputString("Getting Doctor information...");
        String name = ioInterface.readString("Enter the name of the doctor");

        new DropDoctor(healthNumber, name);
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
         ioInterface.outputString(this.toString());
    }

    /**
     * Return a string representation of the HospitalSystem
     * @return a string representation of the HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = patients.values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = doctors.values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + ward;
        return result;
    }

    /**
     * Display the empty beds for the ward.
     */
    public void displayEmptyBeds()
    {
        new EmptyBeds();
    }


    /**
     * Release a patient from the ward.
     */
    public void releasePatient()
    {
         

        ioInterface.outputString("Releasing a patient from the system...");
        ioInterface.outputString("Getting Patient information...");
        int healthNumber = ioInterface.readInt("Enter the health number of the patient");

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);
        //Clears any doctors assigned to patient
        for (Doctor d : this.doctors.values()) {
            if (p.hasDoctor(d.getName())) { d.removePatient(p.getHealthNumber()); }
        }
        //Frees any bed the patient is assigned to
        if (p.getBedLabel() != -1) {
            ward.freeBed(p.getBedLabel());
        }

        patients.remove(p.getHealthNumber());
        ioInterface.outputString(p.getName() + " has been released from the system.");
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        int task = -1;

        HospitalSystem sys = new HospitalSystem();

        String[] taskOptions = {
                "quit"
                ,"add a new patient"
                ,"add a new doctor"
                ,"assign a doctor to a patient"
                ,"display the empty beds of the ward"
                ,"assign a patient a bed"
                ,"release a patient"
                ,"drop doctor-patient association"
                ,"display current system state"
        };
        if (ioInterface != null) { //in the event the user selects 'cancel' on startup
            try {
                while (task != 1) {
                    task = ioInterface.readChoice(taskOptions);

                    if (task == 1)
                        sys.systemState();
                    else if (task == 2)
                        sys.addPatient();
                    else if (task == 3)
                        sys.addDoctor();
                    else if (task == 4)
                        sys.assignDoctorToPatient();
                    else if (task == 5)
                        sys.displayEmptyBeds();
                    else if (task == 6)
                        sys.assignBed();
                    else if (task == 7)
                        sys.releasePatient();
                    else if (task == 8)
                        sys.dropAssociation();
                    else if (task == 9)
                        sys.systemState();
                    else
                         ioInterface.outputString("Invalid option, try again.");
                }
            } catch (RuntimeException e) {
                 ioInterface.outputString(e.getMessage());
            }
        }
    }
}