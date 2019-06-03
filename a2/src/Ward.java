//Dawson Wiebe drw529 11226441
/** Ward class to contain People
 * People objects are mapped to an array indexed by bed labels from firstBed to lastBed
 * Assign people using .assignPatient
 * Retrieve people with .getPatient
 * once assigned People cannot be removed or replaced
 */
public class Ward {
    /** Name of the ward */
    private String name;
    /** First bed labeled in the constructed list */
    private int firstBed;
    /** Array containing the Person classes in order of labled beds */
    private Person[] assignedPatients;

    /** Ward constructor
     * Creates ward class and instances array
     * @param name - String
     * @param firstBed - Starting point of labeled bed array, inclusive
     * @param lastBed - Ending point of labeled bed array, inclusive
     * @throws ArrayIndexOutOfBoundsException - When firstbed is greater than or equal to lastbed
     */
    public Ward(String name, int firstBed, int lastBed) throws ArrayIndexOutOfBoundsException {
        this.name = name;
        this.firstBed = firstBed;
        //Makes sure bed indexes are constructable
        if (firstBed >= lastBed) {
            throw new ArrayIndexOutOfBoundsException("first index greater then last");
        }
        this.assignedPatients = new Person[1+ lastBed - firstBed];
    }
    /** Gets the name */
    public String getName() {
        return name;
    }
    /** Gets the label number of the fist bed */
    public int getFirstBed() {
        return firstBed;
    }
    /** Gets the label number of the last bed */
    public int getLastBed() {
        return this.firstBed + this.assignedPatients.length -1;
    }
    /**
     * Determines if the provided index is within bounds of assignedPatients
     * @param index - int
     * @return true if index is valid, false otherwise
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.assignedPatients.length;
    }
    /**
     * Determins if the provided label is within bounds of assignedPatients
     * @param bedLabel - int between firstBed and lastBed
     * @return true if label is valid, false otherwise
     */
    private boolean isValidLabel(int bedLabel) {
        return isValidIndex(bedLabel - this.firstBed);
    }
    /**
     * Calculates the array index position relative to the bed label
     * @param bedLabel - int between firstBed and lastBed
     * @return int - relative index value
     * @throws ArrayIndexOutOfBoundsException - When provided label is outside the Ward bounds
     */
    public int getBedIndex(int bedLabel) throws ArrayIndexOutOfBoundsException {
        int index = bedLabel - this.firstBed;
        if (isValidIndex(index)) {
            return index;
        } else {
            throw new ArrayIndexOutOfBoundsException("invalid bed label");
        }
    }
    /**
     * Calculates the bed label position relative to the array index point
     * @param bedIndex
     * @return int - relative label value
     * @throws ArrayIndexOutOfBoundsException - When provided index is outside the Ward bounds
     */
    public int getBedLabel(int bedIndex) throws ArrayIndexOutOfBoundsException {
        int label = bedIndex + this.firstBed;
        if (isValidLabel(label)) {
            return label;
        } else {
            throw new ArrayIndexOutOfBoundsException("invalid bed index");
        }
    }
    /**
     * Determines if a Person is currently assigned to a label in the array
     * @param bedLabel - int between firstBed and lastBed
     * @return boolean - true if a Person is present, false otherwise
     */
    public boolean isOccupied(int bedLabel) {
        return this.assignedPatients[getBedIndex(bedLabel)] != null;
    }
    /**
     * Gets the Person object present at a label
     * @param bedLabel - int between firstBed and lastBed
     * @return Person / null if empty
     */
    public Person getPatient(int bedLabel) {
        if (isOccupied(bedLabel)) {
            return this.assignedPatients[getBedIndex(bedLabel)];
        } else {
            return null;
        }
    }
    /**
     * Assigns a Person to a labeled bed if it is empty
     * @param patient - Person class
     * @param bedLabel - int between firstBed and lastBed
     * @throws ArrayStoreException - When the location is already occupied
     */
    public void assignPatient(Person patient, int bedLabel) throws ArrayStoreException{
        if (isOccupied(bedLabel)) {
            throw new ArrayStoreException("bed already occupied");
        } else {
            this.assignedPatients[getBedIndex(bedLabel)] = patient;
        }
    }

    @Override
    public String toString() {
        String rtnString = this.name + ":{";
        for (int i=0; i < this.assignedPatients.length; i++) {
            int lbl = getBedLabel(i);
            rtnString += "[" + lbl + "]";
            if (isOccupied(lbl)) {
                rtnString += getPatient(lbl);
            }
            if (i+1 < this.assignedPatients.length) {
                rtnString += ", ";
            }
        }
        return rtnString + "}";
    }
    /**
     * Runs test cases for the class
     * @param args - Unused
     */
    public static void main(String[] args) {
        Ward testWard = new Ward("Test Ward", 10, 20);
        if (testWard.getName().equals("Test Ward")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testWard.getName());
        }
        if (testWard.getFirstBed() == 10) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testWard.getFirstBed());
        }
        if (testWard.getLastBed() == 20) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testWard.getLastBed());
        }
        if (testWard.toString().equals("Test Ward:{[10], [11], [12], [13], [14], [15], [16], [17], [18], [19], [20]}")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testWard);
        }

        // Arrays of valid and invalid labels for testing
        int[] validLabels = {10, 15, 20};
        int[] invalidLabels = {9, 21, 0, -15};
        //Tests valid labels
        for (int vlbl : validLabels) {
            int resultIndex;
            try {
                resultIndex = testWard.getBedIndex(vlbl);
                if (resultIndex != vlbl - testWard.getFirstBed()) {
                    System.out.println("FAIL: Returns '" + resultIndex + "'");
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("FAIL: Exception thrown with valid input '" + vlbl + "'");
            }
        }
        //Tests invalid labels
        for (int ilbl : invalidLabels) {
            int errorIndex;
            try {
                errorIndex = testWard.getBedIndex(ilbl);
                System.out.println("FAIL: Invalid input accepted, returns '" + errorIndex + "'");
            } catch (ArrayIndexOutOfBoundsException exception) {
                continue;
            }
        }

        //Arrays of valid and invalid indexes
        int[] validIndexes = {0, 10, 1, 5};
        int[] invalidIndexes = {-1, 11, 20};
        //Valid test
        for (int vidx : validIndexes) {
            int resultLabel;
            try {
                resultLabel = testWard.getBedLabel(vidx);
                if (resultLabel != vidx + testWard.getFirstBed()) {
                    System.out.println("FAIL: Returns '" + resultLabel + "'");
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("FAIL: Exception thrown with valid input '" + vidx + "'");
            }
        }
        //Invalid test
        for (int iidx : invalidIndexes) {
            int errorLabel;
            try {
                errorLabel = testWard.getBedLabel(iidx);
                System.out.println("FAIL: Invalid input accepted, returns '" + errorLabel + "'");
            } catch (ArrayIndexOutOfBoundsException exception) {
                continue;
            }
        }

        //Assigns people (test dummies) to the locations listed in validLabels
        for (int asnLbl : validLabels) {
            try {
                testWard.assignPatient(new Person("Test Dummy", 9999), asnLbl);
                if (!testWard.isOccupied(asnLbl)) {
                    System.out.println("FAIL: Error, failed assigning to " + asnLbl);
                }
            } catch (ArrayStoreException exception) {
                System.out.println("FAIL: Exception thrown while assigning to " + asnLbl);
            }
        }
        //Attempts assignment to invalid labels
        for (int badLbl : invalidLabels) {
            try {
                testWard.assignPatient(new Person("Crash Dummy", 666), badLbl);
                System.out.println("FAIL: Passed assignment at invalid location " + badLbl);
            } catch (ArrayIndexOutOfBoundsException exception) {
                continue;
            }
        }
        //Attempts reassignment over occupied labels
        for (int occLbl : validLabels) {
            try {
                testWard.assignPatient(new Person("Overbooked Dummy", 8675309), occLbl);
                System.out.println("FAIL: Passed reassignment at occupied location " + occLbl);
            } catch (ArrayStoreException exception) {
                continue;
            }
        }

        //Valid bed labels that point to an unassigned bed
        int[] emptyLabels = {12, 14, 18};
        //Test empty beds
        for (int emtLbl : emptyLabels) {
            Person getResult;
            try {
                getResult = testWard.getPatient(emtLbl);
                if (getResult != null) {
                    System.out.println("FAIL: Retrieved " + getResult + " at null position " + emtLbl);
                }
            } catch (Exception exception) {
                System.out.println("FAIL: Caught exception '" + exception + "' at position " + emtLbl);
            }
        }
        //Test loaded beds
        for (int lodLbl : validLabels) {
            Person getResult;
            try {
                getResult = testWard.getPatient(lodLbl);
                if (getResult == null) {
                    System.out.println("FAIL: Accessing label " + lodLbl + " returns null");
                }
            } catch (Exception exception) {
                System.out.println("FAIL: Caught exception '" + exception + "' at position " + lodLbl);
            }
        }
    }
}
