//Dawson Wiebe drw529 11226441
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

    protected static InputOutputInterface ioInterface;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relevant information
     */
    public HospitalSystem() {

        patients = PatientMapAccess.dictionary();
        doctors = DoctorMapAccess.dictionary();

        // get the ward information
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Initializing the system...");
        System.out.println("Getting Ward information...");
        System.out.print("Enter the name of the Ward: ");
        String name = consoleIn.nextLine();
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = consoleIn.nextInt();
        consoleIn.nextLine();

        System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = consoleIn.nextInt();
        consoleIn.nextLine();

        WardAccess.initialize(name, firstBedNum, lastBedNum);
        ward = WardAccess.ward();
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the patient: ");
        String name = consoleIn.nextLine();

        System.out.print("Enter the health number of the patient: ");
        int healthNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
        if (patients.containsKey(healthNum))
        {
            throw new RuntimeException("Patient with the health number " + healthNum + " already exsists");
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
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        boolean isSurgeon = response.charAt(0) == 'y' || response.charAt(0) == 'Y';

        new NewDoctor(name, isSurgeon);
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        new AssignDoctor(healthNumber, name);
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a Patient to a Bed...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
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
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Dropping a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        new DropDoctor(healthNumber, name);
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        System.out.println(this.toString());
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
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Releasing a patient from the system...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

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
        System.out.println(p.getName() + " has been released from the system.");
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSystem sys = new HospitalSystem();
        //TEMP: to test consoleIO class
        ioInterface = new ConsoleIO(consoleIn);

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
        try{
            while(task != 1) {
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
                    System.out.println("Invalid option, try again.");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            consoleIn.close();
        }
    }
}