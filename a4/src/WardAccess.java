//Dawson Wiebe drw529 11226441
public class WardAccess {
    private static Ward ward;

    private WardAccess() {}

    public static void initialize(String name, int minBedLabel, int maxBedLabel) throws RuntimeException {
        if (ward != null) {
            throw new RuntimeException("Ward already initialized");
        }
        ward = new Ward(name, minBedLabel, maxBedLabel);
    }

    public static Ward ward() throws RuntimeException {
        if (ward == null) {
            throw new RuntimeException("Ward not initialized");
        }
        return ward;
    }
}
