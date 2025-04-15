package Entity;

public class HDBOfficer extends Applicant{

    private BTOProject newProject;
    private String BTOprojectName;

    public HDBOfficer(String name, String NRIC, String password, boolean visibility, 
                      String marital_status, int age, boolean hasApplied, String BTOprojectName) 
    {
        super(name, NRIC, password, visibility, marital_status, age, hasApplied);
        this.BTOprojectName = BTOprojectName;
    }

    public BTOProject getCurrentProject() {
        return newProject;
    }

    public void setCurrentProject(BTOProject newProject) {
        this.newProject = newProject;
    }

    public String getBTOprojectName() {
        return BTOprojectName;
    }
    
    public void setBTOprojectName(String BTOprojectName) {
        this.BTOprojectName = BTOprojectName;
    }
    
   
}
