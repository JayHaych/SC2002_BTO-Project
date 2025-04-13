package Entity; 

public class Enquiry {

    private String details;

    // No-argument constructor
    public Enquiry() {
    }

    // Constructor with details
    public Enquiry(String details) {
        this.details = details;
    }

    // Getter and setter
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
