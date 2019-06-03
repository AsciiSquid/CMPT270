//Dawson Wiebe drw529 11226441
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hospital System to manage Doctor and Patient classes within a Ward
 */
public class HospitalSystem {
    /**
     * Assigned Ward container for management
     */
    private Ward ward;
    /**
     * Map of all Patient classes for use
     */
    private HashMap<Integer, Patient> patients;
    /**
     * Map of all Doctor classes for use
     */
    private HashMap<String, Doctor> doctors;

    /**
     * Initializes the HospitalSystem class and its Ward container
     * @param wardName - Name for Ward class
     * @param firstBed - firstBed for Ward class
     * @param lastBed - lastBed for Ward class
     */
    public HospitalSystem(String wardName, int firstBed, int lastBed) {
        ward = new Ward(wardName, firstBed, lastBed);
        patients = new HashMap<>();
        doctors = new HashMap<>();
    }

    /**
     * Creates a new Patient class for use in the system
     * @param name - Patient class name
     * @param healthNumber - Patient class number !must be unique!
     */
    public void addPatient(String name, int healthNumber) {
        Patient newPatient = new Patient(name, healthNumber);
        patients.put(healthNumber, newPatient);
    }

    /**
     * Creates a new Doctor class for use in the system
     * @param name - Name of Doctor class !must be unique!
     */
    public void addDoctor(String name) {
        Doctor newDoctor = new Doctor(name);
        doctors.put(name, newDoctor);
    }

    /**
     * Assigns a Doctor class a patient, and vice-versa
     * @param docName - Name of Doctor class present in the instance
     * @param pntNumber - Number of Patient class present in the instance
     * @throws Exception - When the assignment is already present
     */
    public void assignDoctorToPatient(String docName, int pntNumber) throws Exception {
        Doctor doc = doctors.get(docName);
        Patient pnt = patients.get(pntNumber);
        if (!doc.hasPatient(pntNumber)) {
            doc.addPatient(pnt);
        } else {
            throw new Exception(doc.getName() + "is already assigned to " + pnt.getName());
        }
        if (!pnt.hasDoctor(docName)) {
            pnt.addDoctor(doc);
        } else {
            //Something has gone terribly wrong if this exception is thrown.
            throw new Exception(pnt.getName() + " had " + doc.getName() + " without being assigned!");
        }
    }

    /**
     * Assigns a Patient to a bed within the Ward
     * @param pntNumber - Patient class health number
     * @param bedLabel - bedLabel index
     * @throws Exception - when the specified bedLabel is already occupied or out of range
     */
    public void assignPatient(int pntNumber, int bedLabel) throws Exception {
        Patient pnt = patients.get(pntNumber);
        if (!ward.isValidLabel(bedLabel)) {
            throw new Exception("label " + bedLabel + " not found in ward");
        }
        if (ward.isOccupied(bedLabel)) {
            throw new Exception("Bed " + bedLabel + " is already occupied by " + ward.getPatient(bedLabel));
        } else {
            ward.assignPatientToBed(pnt, bedLabel);
            pnt.setBedLabel(bedLabel);
        }
    }

    /**
     * Prints a list of all beds available for assignment
     */
    public void displayEmptyBeds() {
        ArrayList<Integer> freeBeds = new ArrayList<>(ward.availableBeds());
        System.out.println("Available beds:\n" + freeBeds);
    }

    /**
     * Drops the association between a Doctor and Patient class
     * @param docName - Name of Doctor class present in the instance
     * @param pntNumber - Number of Patient class present in the instance
     * @throws Exception - when the association between classes is one-sided
     */
    public void dropAssociation(String docName, int pntNumber) throws Exception {
        Doctor doc = doctors.get(docName);
        Patient pnt = patients.get(pntNumber);
        if (doc.hasPatient(pntNumber) && pnt.hasDoctor(docName)) {
            doc.removePatient(pntNumber);
            pnt.removeDoctor(docName);
        } else {
            throw new Exception("one-way association between " + docName + " and " + pnt.getName());
        }
    }

    /**
     * Removes all associations from a patient and removes it from the patients map
     * @param pntNumber - Number of Patient class present in the instance
     */
    public void releasePatient(int pntNumber) {
        Patient pnt = patients.get(pntNumber);
        if (pnt.getBedLabel() != -1) {
            ward.freeBed(pnt.getBedLabel());
        }
        //Removes all doctor associations with the patient
        for (String docName : pnt.doctors.keySet()) {
            try {dropAssociation(docName, pntNumber);}
            catch(Exception e) {
                System.out.println("strange association found with " + docName);
            }
        }
        patients.remove(pntNumber);
    }

    /**
     * Prints all information of the instance to the console
     */
    public void systemState() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String outStr = "=HOSPITAL SYSTEM=\n";
        outStr += ward.toString();
        outStr += "Patients on record:\n";
        for (Patient p : patients.values()) {
            outStr += "\t" + p.getName() + " [" + p.getHealthNumber() + "]\n";
        }
        outStr += "Doctors on record:\n";
        for (Doctor d : doctors.values()) {
            outStr += d.toString();
        }
        return outStr;
    }

    /**
     * Attempts to collect a Patient health number from user input
     * @param scan - initialized Scanner class
     * @param sys - initialized HospitalSystem class
     * @return a Patient class health number valid for sys
     * @throws Exception - when user input is not valid for use
     */
    private static int promptHealthNumber(Scanner scan, HospitalSystem sys) throws Exception {
        int healthNum;
        System.out.print("Patient:");
        healthNum = scan.nextInt();
        if (sys.patients.containsKey(healthNum)) {
            scan.nextLine();//Dumps hanging input
            return healthNum;
        } else {
            throw new Exception("patient number " + healthNum + " not found");
        }
    }

    /**
     * Attempts to collect a Doctor name from user input
     * @param scan - initialized Scanner class
     * @param sys - initialized HospitalSystem class
     * @return a Doctor class name valid for sys
     * @throws Exception - when user input is not valid for use
     */
    private static String promptDoctorName(Scanner scan, HospitalSystem sys) throws Exception {
        String docName;
        System.out.print("Doctor:");
        docName = scan.nextLine();
        if (sys.doctors.containsKey(docName)) {
            return docName;
        } else {
            throw new Exception("doctor " + docName + " not found");
        }
    }

    /**
     * Generates a console input interface to interact with a HospitalSystem class
     * @param args - unused
     */
    public static void main(String[] args) {
        Scanner cmdLine = new Scanner(System.in);

        System.out.print("Ward name:");
        String wardName = cmdLine.nextLine();
        System.out.print("First bed label:");
        int firstBed = cmdLine.nextInt();
        System.out.print("Last bed label:");
        int lastBed = cmdLine.nextInt();
        HospitalSystem HSystem = new HospitalSystem(wardName, firstBed, lastBed);
        System.out.println("-System Initialized-");
        boolean running = true;
        while (running) {
            System.out.print(">");
            int command = cmdLine.nextInt();
            cmdLine.nextLine(); //Dump hanging input
            //Variables to contain requested command arguments.
            String doctorName;
            String patientName;
            int patientNumber;
            int bedNumber;
            //Catches any Exceptions to be printed for the interface
            try {
                switch (command) {
                    //Exits the loop, ending the program
                    case 1:
                        cmdLine.close();
                        running = false;
                        break;
                    //Prints the status of the Hospital System
                    case 9:
                        HSystem.systemState();
                        break;
                    //Adds a new patient to the system
                    case 2:
                        System.out.print("Name:");
                        patientName = cmdLine.nextLine();
                        System.out.print("Health Number:");
                        patientNumber = cmdLine.nextInt();
                        HSystem.addPatient(patientName, patientNumber);
                        break;
                    //Adds a new doctor to the system
                    case 3:
                        System.out.print("Name:");
                        doctorName = cmdLine.nextLine();
                        HSystem.addDoctor(doctorName);
                        break;
                    //Assigns a doctor to a patient
                    case 4:
                        doctorName = promptDoctorName(cmdLine, HSystem);
                        patientNumber = promptHealthNumber(cmdLine, HSystem);
                        HSystem.assignDoctorToPatient(doctorName, patientNumber);
                        break;
                    //Assigns a patient to a bed in the ward
                    case 6:
                        patientNumber = promptHealthNumber(cmdLine, HSystem);
                        System.out.print("Bed:");
                        bedNumber = cmdLine.nextInt();
                        HSystem.assignPatient(patientNumber, bedNumber);
                        break;
                    //Displays all vacant beds
                    case 5:
                        HSystem.displayEmptyBeds();
                        break;
                    //Releases a patient from the system
                    case 7:
                        patientNumber = promptHealthNumber(cmdLine, HSystem);
                        HSystem.releasePatient(patientNumber);
                        break;
                    //Drops a doctor-patient relationship
                    case 8:
                        doctorName = promptDoctorName(cmdLine, HSystem);
                        patientNumber = promptHealthNumber(cmdLine, HSystem);
                        HSystem.dropAssociation(doctorName, patientNumber);
                        break;
                    default:
                        System.out.println("Command " + command + " not found");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        HSystem.systemState();
    }
}
