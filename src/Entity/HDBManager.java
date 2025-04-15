package Entity;

public class HDBManager extends User {
    private BTOProject currentProject;
    private String BTOprojectname;

    public HDBManager(String name, String NRIC, String password,
                      boolean visibility, String marital_status, int age,
                      String BTOprojectname) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.BTOprojectname = BTOprojectname;
    }

    public BTOProject getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(BTOProject currentProject) {
        this.currentProject = currentProject;
    }

    public String getBTOprojectname() {
        return BTOprojectname;
    }

    public void setBTOprojectname(String BTOprojectname) {
        this.BTOprojectname = BTOprojectname;
    }
}

