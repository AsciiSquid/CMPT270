//Dawson Wiebe drw529 11226441
/**
 * A person with a doctorate
 */
public class BasicDoctor extends Person {
    /**
     * Inherits Person constructor
     * @param name - String
     * @param healthCardNumber - int
     */
    public BasicDoctor(String name, int healthCardNumber) {
        super(name, healthCardNumber);
    }
    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }
    /**
     * Runs test cases for the class
     * @param args - Unused
     */
    public static void main(String[] args) {
        Person testDoctor = new BasicDoctor("Test Doctor", 12345);
        if (testDoctor.toString().equals("Dr. Test Doctor(12345)")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testDoctor.toString());
        }
        if (testDoctor.getName().equals("Test Doctor")) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testDoctor.getName());
        }
        String newName = "Sample Text";
        testDoctor.setName(newName);
        if (testDoctor.getName().equals(newName)) {
            //Success
        } else {
            System.out.println("FAIL\nReturns: " + testDoctor.getName());
        }
    }
}
