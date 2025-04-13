package Entity;

public class HDBManager extends User {
    private BTOProject currentProject;

    public HDBManager(String name, String NRIC, String password,
                      boolean visibility, String marital_status, int age,
                      BTOProject currentProject) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.currentProject = currentProject;
    }

    public BTOProject getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(BTOProject currentProject) {
        this.currentProject = currentProject;
    }
}
