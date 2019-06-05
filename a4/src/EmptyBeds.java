//Dawson Wiebe drw529 11226441

/**
 * Executes the displayEmptyBeds command for the HospitalSystem and stores the result
 */
public class EmptyBeds extends CommandStatus {
    /**
     * Executes the task and stores the result
     */
    public EmptyBeds() {
        Ward ward;
        try {
            ward = WardAccess.ward();
        } catch (RuntimeException e) {
            ward = null;
            super.errorMessage = e.getMessage();
            super.successful = false;
        }
        if (ward == null) {
            super.errorMessage = "wardAccess returned null without throwing an exception";
            super.successful = false;
        } else {
            System.out.println("Beds currently available:\n" + ward.availableBeds());
            super.successful = true;
        }
    }
}
