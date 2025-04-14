package Entity;

public class FlatBooking {
    private String bookingID;
    private String projectName;
    private String flatType;
    private String unitNumber;
    private boolean bookingStatus;
    private CurrentUser applicant;

    public FlatBooking(String bookingID, String projectName, String flatType, String unitNumber, boolean bookingStatus, CurrentUser applicant) {
        this.bookingID = bookingID;
        this.projectName = projectName;
        this.flatType = flatType;
        this.unitNumber = unitNumber;
        this.bookingStatus = bookingStatus;
        this.applicant = applicant;
    }

    public String getBookingID(){
        return bookingID;
    }

    public void setBookingID(String bookingID){
        this.bookingID = bookingID;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getFlatType(){
        return flatType;
    }

    public void setFlatType(String flatType){
        this.flatType = flatType;
    }

    public String getUnitNumber(){
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber){
        this.unitNumber = unitNumber;
    }

    public boolean getBookingStatus(){
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public CurrentUser getApplicant() {
        return applicant;
    }

    public void setApplicant(CurrentUser applicant) {
        this.applicant = applicant;
    }
}
