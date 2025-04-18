package Entity;

import java.time.LocalDate;

public class BTOApplication {

    private String projectName;
    private String flatType; //flatType is NOT enumerate anymore. 
    private String applicationStatus;
    private LocalDate submissionDate;
    private Boolean withdrawalRequested;
    private String applicantName;
    private User applicant; //either Applicant or Officer object
    private Boolean withdrawalApproved; //for officer to approve the withdrawal request

    public BTOApplication() {
       
    }

    public BTOApplication(String projectName, String flatType, String applicationStatus, 
                          String submissionDate, Boolean withdrawalRequested, String applicantName, Boolean withdrawalApproved) {
        this.projectName = projectName;
        this.flatType = flatType;
        this.applicantName = applicantName;
        this.applicationStatus = applicationStatus; //enumerate
        this.withdrawalRequested = withdrawalRequested;
        this.applicantName = applicantName;
        this.withdrawalApproved = withdrawalApproved;
        this.submissionDate = LocalDate.parse(submissionDate);
    }
    
    public Boolean getWithdrawalApproved() {
        return withdrawalApproved;
    }

    public void setWithdrawalApproved(Boolean withdrawalApproved) {
        this.withdrawalApproved = withdrawalApproved;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = LocalDate.parse(submissionDate);
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

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}