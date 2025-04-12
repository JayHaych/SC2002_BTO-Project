package Entity;

public class HDBOfficer extends Applicant{

    private BTOProject newProject;

    public HDBOfficer(String name, String NRIC, String password, boolean visibility, String marital_status, int age, BTOProject newProject) {
        super(name, NRIC, password, visibility, marital_status, age, visibility);
        this.newProject = newProject;
    }

    public BTOProject getCurrentProject() {
        return newProject;
    }

    public void setCurrentProject(BTOProject newProject) {
        this.newProject = newProject;
    }

   
}
