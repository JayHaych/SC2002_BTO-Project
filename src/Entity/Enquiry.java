/* enquiry needs CurrentUser cus each user can view, edit, delete only their enquiries
 * enquiry needs projectname cus hdb manager can reply only the project they r managing. but can view all.
 */

package Entity;

public class Enquiry {
    private String details;
    private String projectName;
    private User user;
    private String userName; 
    private String reply;


    public Enquiry(String details, String projectName, String userName, String reply) { //enquiry constructor for enquiry_list
        this.details = details;
        this.projectName = projectName;
        this.userName = userName;
        this.reply = reply;
    } //needs to be edited

    public Enquiry(String details, String projectName, User user) { //enquiry constructor for NEW enquiry by applicant
        this.details = details;
        this.projectName = projectName;
        this.user = user;
        this.userName = user.getName();
        this.reply = "No replies yet.";
    }

    public String getUserName() {
        return userName;
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
        this.userName = UserName;
    }

    public String getReply(){
        return reply;
    }

    public void setReply(String reply){
        this.reply = reply;
    }
}

