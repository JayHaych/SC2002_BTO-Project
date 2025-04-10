package Entity;

public class Applicant extends User {

    private boolean hasApplied;
    
    /**
     * Constructor to initialize both the User fields and the Applicant field.
     *
     * @param name           
     * @param NRIC           
     * @param password       
     * @param visibility     visibility status
     * @param marital_status 
     * @param age            
     * @param hasApplied    
     */

    // Applicant is a User
    public Applicant(String name, String NRIC, String password, boolean visibility,
                     String marital_status, int age, boolean hasApplied) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.hasApplied = hasApplied;
    }
    
    // Getter for hasApplied
    public boolean hasApplied() {
        return hasApplied;
    }
    
    // Setter for hasApplied
    public void setHasApplied(boolean hasApplied) {
        this.hasApplied = hasApplied;
    }

    // Optionally, override the toString method to include Applicant details
    @Override
    public String toString() {
        return "Applicant{" +
                "name='" + getName() + '\'' +
                ", NRIC='" + getNRIC() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", visibility=" + isVisible() +
                ", marital_status='" + getMaritalStatus() + '\'' +
                ", age=" + getAge() +
                ", hasApplied=" + hasApplied +
                '}';
    }
}
