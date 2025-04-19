package Entity;

public class Applicant extends User {

    private String appliedProject;

    public Applicant(String name, String NRIC, String password, boolean visibility,
                     String marital_status, int age, String appliedProject) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.appliedProject = appliedProject;
    }

    public String getAppliedProject(){
        return appliedProject;
    }

    public void setAppliedProject(String projectName){
        appliedProject = projectName;
    }
}