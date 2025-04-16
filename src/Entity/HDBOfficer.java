package Entity;

public class HDBOfficer extends Applicant{

    private BTOProject newProject;
    private String BTOprojectName; //project the officer is a manager for.

    public HDBOfficer(String name, String NRIC, String password, boolean visibility, 
                      String marital_status, int age, String appliedProject, String BTOprojectName) 
    {
        super(name, NRIC, password, visibility, marital_status, age, appliedProject);
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
