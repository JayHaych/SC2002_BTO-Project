/* enquiry needs CurrentUser cus each user can view, edit, delete only their enquiries
 * enquiry needs projectname cus hdb manager can reply only the project they r managing. but can view all.
 */

package Entity;

public class Enquiry {
    private String details;
    private String projectName;
    private User user;
    private String UserName; 


    public Enquiry(String details, String projectName, String Username) {
        this.details = details;
        this.projectName = projectName;
        this.UserName = Username;
    }

    public String getUserName() {
        return UserName;
    }

    public String getDetails() {
        return details;
    }

    public String getProjectName() {
        return projectName;
    }

    public User getUser() {
        return user;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
}

