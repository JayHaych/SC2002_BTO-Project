/*this entity class is to let the controllers know who is currently using the application
 * it will be used for visibility checks (if this applicant can view the project or not)
 * as such, it will not include setter methods
 */

 package Entity;

 public class CurrentUser {
     private String name;
     private String NRIC;
     private int age;
     private String maritalStatus; // corrected spelling from "martialStatus"
     private String password;
 
     // Default constructor
     public CurrentUser() {
     }
 
     // Parameterized constructor
     public CurrentUser(String name, String NRIC, int age, String maritalStatus, String password) {
         this.name = name;
         this.NRIC = NRIC;
         this.age = age;
         this.maritalStatus = maritalStatus;
         this.password = password;
     }
 
     // Getters
     public String getName() {
         return name;
     }
 
     public String getNRIC() {
         return NRIC;
     }
 
     public int getAge() {
         return age;
     }
 
     public String getMaritalStatus() {
         return maritalStatus;
     }
 
     public String getPassword() {
         return password;
     }
 
 }

