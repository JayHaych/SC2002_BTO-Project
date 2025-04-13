package Entity;

public class BTOProject {
    private String projectName;
    private String neighbourhood;
    private int numberOfTwoRoom;
    private int numberOfThreeRoom;
    private String openingDate;       
    private String closingDate;                
    private String HDBManagerInCharge;
    private int availableOfficerSlot;

    public BTOProject(){

    }

    public BTOProject(String projectName, String neighbourhood, int numberOfTwoRoom,
                      int numberOfThreeRoom, String openingDate, String closingDate,
                      String HDBManagerInCharge, int availableOfficerSlot) {
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.numberOfTwoRoom = numberOfTwoRoom;
        this.numberOfThreeRoom = numberOfThreeRoom;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.HDBManagerInCharge = HDBManagerInCharge;
        this.availableOfficerSlot = availableOfficerSlot;
    }

    // Getter and Setter for projectName
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getter and Setter for neighbourhood
    public String getNeighbourhood() {
        return neighbourhood;
    }
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    // Getter and Setter for numberOfTwoRoom
    public int getNumberOfTwoRoom() {
        return numberOfTwoRoom;
    }
    public void setNumberOfTwoRoom(int numberOfTwoRoom) {
        this.numberOfTwoRoom = numberOfTwoRoom;
    }

    // Getter and Setter for numberOfThreeRoom
    public int getNumberOfThreeRoom() {
        return numberOfThreeRoom;
    }
    public void setNumberOfThreeRoom(int numberOfThreeRoom) {
        this.numberOfThreeRoom = numberOfThreeRoom;
    }

    // Getter and Setter for openingDate
    public String getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    // Getter and Setter for closingDate
    public String getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    // Getter and Setter for HDBManagerInCharge
    public String getHDBManagerInCharge() {
        return HDBManagerInCharge;
    }
    public void setHDBManagerInCharge(String HDBManagerInCharge) {
        this.HDBManagerInCharge = HDBManagerInCharge;
    }

    // Getter and Setter for availableOfficerSlot
    public int getAvailableOfficerSlot() {
        return availableOfficerSlot;
    }
    public void setAvailableOfficerSlot(int availableOfficerSlot) {
        this.availableOfficerSlot = availableOfficerSlot;
    }
}
