package Entity;

public class FlatBooking {
    private String bookingID;
    private String projectID;
    private String flatType;
    private String unitNumber;
    private String bookingDate;
    private String officeID;
    private boolean bookingStatus;

    public FlatBooking(String bookingID, String projectID, String flatType, String unitNumber, String bookingDate, String officeID, boolean bookingStatus){
        this.bookingID = bookingID;
        this.projectID = projectID;
        this.flatType = flatType;
        this.unitNumber = unitNumber;
        this.bookingDate = bookingDate;
        this.officeID = officeID;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingID(){
        return bookingID;
    }

    public void setBookingID(String bookingID){
        this.bookingID = bookingID;
    }

    public String getProjectID(){
        return projectID;
    }

    public void setProjectID(String projectID){
        this.projectID = projectID;
    }

    public String getFlateType(){
        return flatType;
    }

    public void setFlatType(String flatType){
        this.flatType = flatType;
    }

    public String getUnitNumber(){
        return unitNumber;
    }

    public void setUnitNumber(){
        this.unitNumber = unitNumber;
    }

    public String getBookingDate(){
        return bookingDate;
    }

    public void setBookingDate(String bookingDate){
        this.bookingDate = bookingDate;
    }

    public String getOfficeID(){
        return officeID;
    }

    public void setOfficeID(String officeID){
        this.officeID = officeID;
    }

    public boolean getBookingStatus(){
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus){
        this.bookingStatus = bookingStatus;
    }
}