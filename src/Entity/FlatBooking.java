package Entity;

public class FlatBooking {
    private String bookingID;
    private String projectName;
    private String flatType;
    private String unitNumber;
    private String bookingStatus;
    private User applicant;
    private String applicantName;

    public FlatBooking(String bookingID, String projectName, String flatType, String unitNumber, String bookingStatus, String applicantName) {
        this.bookingID = bookingID;
        this.projectName = projectName;
        this.flatType = flatType;
        this.unitNumber = unitNumber;
        this.bookingStatus = bookingStatus;
        this.applicantName = applicantName;
    }


    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public String getBookingStatus(){
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
}

