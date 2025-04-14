package Entity;

import java.util.ArrayList;
import java.util.List;

public class BTOProject {
    private String projectName;
    private String neighbourhood;
    private int numberOfTwoRoom;
    private int numberOfThreeRoom;
    private String openingDate;       
    private String closingDate;                
    private String HDBManagerInCharge;
    private int availableOfficerSlot;
    private boolean isVisible; // project visibility (on/off)
    private List<Enquiry> enquiries; // To store enquiries
    private List<FlatBooking> flatBookings; // To store flat bookings for receipt and report generation

    public BTOProject(String projectName, String neighbourhood, int numberOfTwoRoom,
                      int numberOfThreeRoom, String openingDate, String closingDate,
                      String HDBManagerInCharge, int availableOfficerSlot, boolean isVisible) {
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.numberOfTwoRoom = numberOfTwoRoom;
        this.numberOfThreeRoom = numberOfThreeRoom;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.HDBManagerInCharge = HDBManagerInCharge;
        this.availableOfficerSlot = availableOfficerSlot;
        this.isVisible = isVisible;
        this.enquiries = new ArrayList<>();
        this.flatBookings = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getNumberOfTwoRoom() {
        return numberOfTwoRoom;
    }
    public void setNumberOfTwoRoom(int numberOfTwoRoom) {
        this.numberOfTwoRoom = numberOfTwoRoom;
    }

    public int getNumberOfThreeRoom() {
        return numberOfThreeRoom;
    }
    public void setNumberOfThreeRoom(int numberOfThreeRoom) {
        this.numberOfThreeRoom = numberOfThreeRoom;
    }

    public String getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getHDBManagerInCharge() {
        return HDBManagerInCharge;
    }
    public void setHDBManagerInCharge(String HDBManagerInCharge) {
        this.HDBManagerInCharge = HDBManagerInCharge;
    }

    public int getAvailableOfficerSlot() {
        return availableOfficerSlot;
    }
    public void setAvailableOfficerSlot(int availableOfficerSlot) {
        this.availableOfficerSlot = availableOfficerSlot;
    }

    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }
    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public List<FlatBooking> getFlatBookings() {
        return flatBookings;
    }

    public void setFlatBookings(List<FlatBooking> flatBookings) {
        this.flatBookings = flatBookings;
    }
    
    //does this belong here????
    public void addFlatBooking(FlatBooking booking) {
        this.flatBookings.add(booking);
    }
}
