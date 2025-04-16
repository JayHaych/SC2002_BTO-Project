package Entity;

public class Receipt {
    private String applicantName;
    private String applicantNRIC;
    private int applicantAge;
    private String applicantMaritalStatus;
    private String flatType;
    private String projectName;

    public Receipt(FlatBooking booking) {

        User applicant = booking.getApplicant();
        this.applicantName = applicant.getName();
        this.applicantNRIC = applicant.getNRIC();
        this.applicantAge = applicant.getAge();
        this.applicantMaritalStatus = applicant.getMaritalStatus();
        this.flatType = booking.getFlatType();
        this.projectName = booking.getProjectName();
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    public void setApplicantNRIC(String applicantNRIC) {
        this.applicantNRIC = applicantNRIC;
    }

    public int getApplicantAge() {
        return applicantAge;
    }

    public void setApplicantAge(int applicantAge) {
        this.applicantAge = applicantAge;
    }

    public String getApplicantMaritalStatus() {
        return applicantMaritalStatus;
    }

    public void setApplicantMaritalStatus(String applicantMaritalStatus) {
        this.applicantMaritalStatus = applicantMaritalStatus;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
