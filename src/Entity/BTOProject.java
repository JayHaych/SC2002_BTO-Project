package Entity;

import java.util.ArrayList;
import java.util.List;

public class BTOProject {
    private String projectName;
    private String neighbourhood;
    private int numberOfTwoRoom;
    private int sellingPriceOfTwoRoom;
    private int numberOfThreeRoom;
    private int sellingPriceOfThreeRoom;
    private String openingDate;       
    private String closingDate;                
    private String HDBManagerInChargeName;
    private HDBManager hdbManagerInCharge; 
    private int availableOfficerSlot;
    private ArrayList<HDBOfficer> OfficersInCharge; // To store HDB officers assigned to the project
    private ArrayList<String> OfficerInChargeNames; // To store names of HDB officers assigned to the project
    private boolean isVisible; // project visibility (on/off)
    private List<Enquiry> enquiries; // To store enquiries
    private List<FlatBooking> flatBookings; // To store flat bookings for receipt and report generation


    public BTOProject(String projectName, String neighbourhood, int numberOfTwoRoom, int sellingPriceOfTwoRoom,
                      int numberOfThreeRoom, int sellingPriceOfThreeRoom, String openingDate, String closingDate,
                      String HDBManagerInChargeName, int availableOfficerSlot, String[] OfficerInChargeNames, boolean isVisible) {
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.numberOfTwoRoom = numberOfTwoRoom;
        this.sellingPriceOfTwoRoom = sellingPriceOfTwoRoom;
        this.numberOfThreeRoom = numberOfThreeRoom;
        this.sellingPriceOfThreeRoom = sellingPriceOfThreeRoom;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.HDBManagerInChargeName = HDBManagerInChargeName;
        this.availableOfficerSlot = availableOfficerSlot;
        this.OfficersInCharge = new ArrayList<>();
        this.OfficerInChargeNames = new ArrayList<>();
        for (String officerName : OfficerInChargeNames) 
        {
            this.OfficerInChargeNames.add(officerName);
        }
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

    public String getHDBManagerInChargeName() {
        return HDBManagerInChargeName;
    }
    public void setHDBManagerInChargeName(String HDBManagerInChargeName) {
        this.HDBManagerInChargeName = HDBManagerInChargeName;
    }

    public HDBManager getHDBManagerInCharge() {
        return hdbManagerInCharge;
    }
    public void setHDBManagerInCharge(HDBManager hdbManagerInCharge) {
        this.hdbManagerInCharge = hdbManagerInCharge;
    }

    public int getSellingPriceOfTwoRoom() {
        return sellingPriceOfTwoRoom;
    }
    public void setSellingPriceOfTwoRoom(int sellingPriceOfTwoRoom) {
        this.sellingPriceOfTwoRoom = sellingPriceOfTwoRoom;
    }
    
    public int getSellingPriceOfThreeRoom() {
        return sellingPriceOfThreeRoom;
    }
    public void setSellingPriceOfThreeRoom(int sellingPriceOfThreeRoom) {
        this.sellingPriceOfThreeRoom = sellingPriceOfThreeRoom;
    }

    public int getAvailableOfficerSlot() {
        return availableOfficerSlot;
    }
    public void setAvailableOfficerSlot(int availableOfficerSlot) {
        this.availableOfficerSlot = availableOfficerSlot;
    }

    public ArrayList<HDBOfficer> getOfficersInCharge() {
        return OfficersInCharge;
    }
    public void setOfficersInCharge(ArrayList<HDBOfficer> OfficersInCharge) {
        this.OfficersInCharge = OfficersInCharge;
    }
    public void addOfficerInCharge(HDBOfficer officer) {
        this.OfficersInCharge.add(officer);
    }
    public void removeOfficerInCharge(HDBOfficer officer) {
        this.OfficersInCharge.remove(officer);
    }
    public ArrayList<String> getOfficerInChargeNames() {
        return OfficerInChargeNames;
    }
    public void setOfficerInChargeNames(ArrayList<String> OfficerInChargeNames) {
        this.OfficerInChargeNames = OfficerInChargeNames;
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
