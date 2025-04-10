package Entity;

public class HDBManager extends User {

    // Constructor
    public HDBManager(String name, String NRIC, String password, 
                      boolean visibility, String marital_status, int age) {
        super(name, NRIC, password, visibility, marital_status, age);
    }
    
    // You can include any entity-specific helper methods if needed, but avoid complex logic.
    
    @Override
    public String toString() {
        return "HDBManager{" +
                "name='" + getName() + '\'' +
                ", NRIC='" + getNRIC() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", visibility=" + isVisible() +
                ", marital_status='" + getMaritalStatus() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}