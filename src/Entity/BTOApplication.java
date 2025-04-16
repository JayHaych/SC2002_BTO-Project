package Entity;

public class BTOApplication {

    private String projectName;
    private String flatType; //flatType is NOT enumerate anymore. 
    private String applicationStatus;
    private String submissionDate;
    private Boolean withdrawalRequested;
    private String applicantName;
    private User applicant; //either Applicant or Officer object

    public BTOApplication() {
       
    }

    public BTOApplication(String projectName, String flatType, User user) {
        this.projectName = projectName;
        this.flatType = flatType;
        this.applicant = user;
        applicantName = user.getName();
        applicationStatus = "Pending"; //enumerate
        submissionDate = "test";
        withdrawalRequested = false;
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

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}