package Entity;

public class BTOApplication {

    private String applicationID;
    private String projectName;
    private String flatType;
    private String applicationStatus;
    private String submissionDate;
    private Boolean withdrawalRequested;
    private String applicantName;
    private User applicant; 

    public BTOApplication() {
       
    }

    public BTOApplication(String applicationID,String projectName, String flatType, String applicationStatus, 
                          String submissionDate, Boolean withdrawalRequested, String applicantName) {
        this.projectName = projectName;
        this.applicationID = applicationID;
        // Convert the integer flat type to a descriptive string.
        this.flatType = flatType;
        this.applicantName = applicantName;
        this.submissionDate = submissionDate;
        // Set the initial application status to "Pending".
        this.applicationStatus = applicationStatus;
        this.withdrawalRequested = withdrawalRequested;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    
    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Boolean getWithdrawalRequested() {
        return withdrawalRequested;
    }

    public void setWithdrawalRequested(Boolean withdrawalRequested) {
        this.withdrawalRequested = withdrawalRequested;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
}