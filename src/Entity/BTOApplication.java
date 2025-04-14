package Entity;

import java.util.Date;
import java.util.UUID;

public class BTOApplication {

    private String applicationID;
    private String projectName;
    private String flatType;
    private String applicationStatus;
    private Date submissionDate;
    private Boolean withdrawalRequested;
    private CurrentUser applicant; 

    public BTOApplication() {
       
    }

    public BTOApplication(String projectName, CurrentUser user, int flatType) {
        this.applicationID = UUID.randomUUID().toString();
        this.projectName = projectName;
        
        // Convert the integer flat type to a descriptive string.
        if (flatType == 2) {
            this.flatType = "2-Room";
        } else {
            this.flatType = "3-Room";
        }
        
        this.applicant = user;
        this.submissionDate = new Date();
        // Set the initial application status to "Pending".
        this.applicationStatus = "Pending";
        this.withdrawalRequested = false;
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

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Boolean getWithdrawalRequested() {
        return withdrawalRequested;
    }

    public void setWithdrawalRequested(Boolean withdrawalRequested) {
        this.withdrawalRequested = withdrawalRequested;
    }

    public CurrentUser getApplicant() {
        return applicant;
    }

    public void setApplicant(CurrentUser applicant) {
        this.applicant = applicant;
    }
}
