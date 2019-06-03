//Dawson Wiebe drw529 11226441
/**
 * A human person with a name and health card number
 */
public class Person {
    /** The Persons full name */
    private String name;
    /** Health card number of arbitrary digits */
    private int healthCardNumber;

    /**
     * Defines the persons name and number
     * @param name - String
     * @param healthCardNumber - int
     */
    public Person(String name, int healthCardNumber) {
        this.name = name;
        this.healthCardNumber = healthCardNumber;
    }
    /** Gets the name */
    public String getName() {
        return name;
    }
    /** Changes the current name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }
    /** Gets the health card number */
    public int getHealthNumber() {
        return healthCardNumber;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.healthCardNumber + ")";
    }

    /**
     * Runs test cases for the class
     * @param args - Unused
     */
    public static void main(String[] args) {
        Person testPerson = new Person("Test Name", 12345);
        if (testPerson.toString().equals("Test Name(12345)")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testPerson.toString());
        }
        if (testPerson.getName().equals("Test Name")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testPerson.getName());
        }
        String newName = "Sample Text";
        testPerson.setName(newName);
        if (testPerson.getName().equals(newName)) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testPerson.getName());
        }
        if (testPerson.getHealthNumber() == 12345) {
            //Success
        } else {
            System.out.println("FAIL\n Returns: " + testPerson.getHealthNumber());
        }
    }
}
