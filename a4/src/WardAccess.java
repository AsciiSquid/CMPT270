//Dawson Wiebe drw529 11226441

/**
 * static Ward container for the HospitalSystem class
 */
public class WardAccess {
    private static Ward ward;

    private WardAccess() {}

    /**
     * Initializes the Ward class for use. !must be called successfully before using ward()!
     * @param name - name of Ward class
     * @param minBedLabel - minimum bed label number
     * @param maxBedLabel - maximum bed label number
     * @throws RuntimeException - when the Ward class has already be initialized
     */
    public static void initialize(String name, int minBedLabel, int maxBedLabel) throws RuntimeException {
        if (ward != null) {
            throw new RuntimeException("Ward already initialized");
        }
        ward = new Ward(name, minBedLabel, maxBedLabel);
    }

    /**
     * Access the contained Ward class
     * @return - the contained Ward
     * @throws RuntimeException - when initialize() has yet to be called
     */
    public static Ward ward() throws RuntimeException {
        if (ward == null) {
            throw new RuntimeException("Ward not initialized");
        }
        return ward;
    }
}
