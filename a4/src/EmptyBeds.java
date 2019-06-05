public class EmptyBeds extends CommandStatus {
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
