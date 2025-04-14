package Entity;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {

    private boolean hasApplied;
    private List<Enquiry> enquiries;  //each applicant has its own enquiry list

    public Applicant(String name, String NRIC, String password, boolean visibility,
                     String marital_status, int age, boolean hasApplied) {
        super(name, NRIC, password, visibility, marital_status, age);
        this.hasApplied = hasApplied;
        this.enquiries = new ArrayList<>();
    }
    
    public boolean hasApplied() {
        return hasApplied;
    }
    
    public void setHasApplied(boolean hasApplied) {
        this.hasApplied = hasApplied;
    }
    
    // Getter for the enquiries list
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }
    
    // Add an enquiry to this applicant's list.
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }
}
