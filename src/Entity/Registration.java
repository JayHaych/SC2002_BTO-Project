package Entity;

public class Registration 
{
    private String OfficerName;
    private String ProjectName;
    private String ApprovalStatus;

    public Registration(String projectName, String officerName, String approvalStatus) 
    {
        this.OfficerName = officerName;
        this.ProjectName = projectName;
        this.ApprovalStatus = approvalStatus;
    }

    public String getOfficerName() 
    {
        return OfficerName;
    }

    public void setOfficerName(String officerName) 
    {
        this.OfficerName = officerName;
    }

    public String getProjectName() 
    {
        return ProjectName;
    }

    public void setProjectName(String projectName) 
    {
        this.ProjectName = projectName;
    }

    public String getApprovalStatus() 
    {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) 
    {
        this.ApprovalStatus = approvalStatus;
    }


}
