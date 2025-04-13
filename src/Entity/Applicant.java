package Entity;

public class Applicant extends User {

    private boolean hasApplied;
    
    public Applicant(String name, String NRIC, String password, boolean visibility,
                     String marital_status, int age, boolean hasApplied) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.hasApplied = hasApplied;
    }
    
    public boolean hasApplied() {
        return hasApplied;
    }
    
    public void setHasApplied(boolean hasApplied) {
        this.hasApplied = hasApplied;
    }
}
