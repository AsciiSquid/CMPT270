//Dawson Wiebe drw529 11226441

/**
 * A variant of the Doctor class
 * functionally identical to Doctor
 */
public class Surgeon extends Doctor{
    /**
     * Inherits the Doctor constructor
     * @param name - name of Surgeon class
     */
    public Surgeon(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "-Surgeon-" + super.toString();
    }

    public static void main(String[] args) {
        Surgeon sur = new Surgeon("Dr. Strange");
        System.out.println("Surgeon Dr. Strange is...\n" + sur);
    }
}
