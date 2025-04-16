package Controller;

import java.util.*;
import Entity.*;
import Interface.ViewEnquiryInterface;

public class ApplicantController implements ViewEnquiryInterface {

    private static List<Enquiry> enquiries = new ArrayList<>(); //all enquiries are stored here
    private static List<BTOApplication> applicationList = new ArrayList<>(); //all applications are stored here
    private static List<BTOProject> projectList = new ArrayList<>(); 

    public static void submitEnquiry(User user) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter your enquiry:");
        String details = sc.nextLine();
        System.out.println("Enter the project name: ");
        String projectName = sc.nextLine();

        Enquiry enquiry = new Enquiry(details, projectName, user);
        
        boolean projectFound = false;
        for (BTOProject project : projectList) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                project.getEnquiries().add(enquiry);
                projectFound = true;
                break;
            }
        }
        
        if (!projectFound) {
            System.out.println("No project found with the name '" + projectName + "'. Enquiry not saved.");
            sc.close();
            return;
        }
        
        enquiries.add(enquiry);
        System.out.println("Enquiry submitted successfully.");
        sc.close();
    }
    

    public static void viewEnquiry(User user) {
        boolean found = false;
        int count = 1;
        System.out.println("Your Enquiries:");
        
        // only show enquiries that belong to the applicant
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getUser().equals(user)) {
                System.out.println(count + ". " + enquiry.getDetails());
                count++;
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No enquiries to display.");
        }
    }
    

    public static void editEnquiry(User user) {
        //new list of user enquiries
        List<Enquiry> userEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getUser().equals(user)) {
                userEnquiries.add(enquiry);
            }
        }
        
        if (userEnquiries.isEmpty()) {
            System.out.println("No enquiries available to edit.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Your Enquiries:");
        for (int i = 0; i < userEnquiries.size(); i++) {
            System.out.println((i + 1) + ". " + userEnquiries.get(i).getDetails());
        }
        
        System.out.println("Enter the enquiry number you wish to edit:");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if (choice > 0 && choice <= userEnquiries.size()) {
            System.out.println("Enter the new details for the enquiry:");
            String newDetails = sc.nextLine();
            
            userEnquiries.get(choice - 1).setDetails(newDetails);
            System.out.println("Enquiry updated successfully.");
        } else {
            System.out.println("Invalid number.");
        }
        sc.close();
    }
    

    public static void deleteEnquiry(User user) {
        List<Enquiry> userEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getUser().equals(user)) {
                userEnquiries.add(enquiry);
            }
        }
    
        if (userEnquiries.isEmpty()) {
            System.out.println("No enquiries available to delete.");
            return;
        }
        
        // Display user enquiries.
        System.out.println("Your Enquiries:");
        for (int i = 0; i < userEnquiries.size(); i++) {
            System.out.println((i + 1) + ". " + userEnquiries.get(i).getDetails());
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the enquiry number you wish to delete:");
        int choice = sc.nextInt();
        
        if (choice > 0 && choice <= userEnquiries.size()) {
            Enquiry enquiryToDelete = userEnquiries.get(choice - 1);
            enquiries.remove(enquiryToDelete);
            System.out.println("Enquiry deleted successfully.");
        } else {
            System.out.println("Invalid enquiry number.");
        }
        
        sc.close();
    }
    

    public static void apply(Applicant user) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the project name you would like to apply for:");
        String projectName = sc.nextLine();
        int flatType = 0;
        
        // Check eligibility for married applicants (age >= 21)
        if (user.getMaritalStatus() == "Married" && user.getAge() >= 21) {
            System.out.println("Enter the flat type you want to apply for (enter 2 for 2-room, 3 for 3-room):");
            flatType = sc.nextInt();
            sc.nextLine();
            
            if (flatType != 2 && flatType != 3) {
                System.out.println("Invalid flat type selected.");
                sc.close();
                return;
            }
        }
        // Check eligibility for single applicants (age >= 35)
        else if (user.getMaritalStatus() == "Single" && user.getAge() >= 35) {
            System.out.println("As a single applicant, you can only apply for a 2-room flat. Proceeding to Apply for 2 room flat.");
            flatType = 2;
        }
        // Applicant is not eligible
        else {
            System.out.println("You are not eligible to apply for a BTO application based on your marital status or age.");
            sc.close();
            return;
        }
        
        // Create application with the given project name, user and flat type.
        BTOApplication application = new BTOApplication(projectName, flatType, user);
        applicationList.add(application);

        user.setAppliedProject(projectName); //set applied project attribute to applicant
        
        System.out.println("Application submitted successfully.");
        sc.close();
    }
    

    public static void viewProjects() {
        User currentUser = LocalData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        BTOProject_List projectList = LocalData.getBTOProjectList();
        boolean foundProject = false;
    
        System.out.println("Available Projects Based on Your Eligibility:");
        for (int i = 0; i < projectList.getCount(); i++) {
            BTOProject project = projectList.getBTOProject(i);
    
            if (!project.isVisible()) {
                continue;
            }
            
            // Married applicants (age >= 21): Show both 2-room and 3-room flats.
            if (currentUser.getMaritalStatus().equalsIgnoreCase("Married") && currentUser.getAge() >= 21) {
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Neighbourhood: " + project.getNeighbourhood());
                System.out.println("2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("3-Room Units Available: " + project.getNumberOfThreeRoom());
                System.out.println("Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                System.out.println("------------------------------------------");
                foundProject = true;
            }
            // Single applicants (age >= 35): Only show projects with available 2-room flats.
            else if (currentUser.getMaritalStatus().equalsIgnoreCase("Single") && currentUser.getAge() >= 35) {
                // Check if the project offers at least one 2-room unit (Type 1 is assumed to be 2-Room).
                if (project.getNumberOfTwoRoom() > 0) {
                    System.out.println("Project Name: " + project.getProjectName());
                    System.out.println("Neighbourhood: " + project.getNeighbourhood());
                    System.out.println("2-Room Units Available: " + project.getNumberOfTwoRoom());
                    System.out.println("Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                    System.out.println("------------------------------------------");
                    foundProject = true;
                }
            }
        }
    
        if (!foundProject) {
            System.out.println("No projects available based on your eligibility criteria.");
        }
    }

    public static void viewAppliedProject() {
        User currentUser = LocalData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        
        // Since only an Applicant is supposed to invoke this method,
        // we directly cast the current user to Applicant.
        Applicant applicant = (Applicant) currentUser;
        // Get the project name that the applicant has applied for.
        String appliedProjectName = applicant.getAppliedProject();
        
        if (appliedProjectName == null || appliedProjectName.trim().isEmpty()) {
            System.out.println("You have not applied for any project yet.");
            return;
        }
        
        // Retrieve the project list from LocalData.
        BTOProject_List projectList = LocalData.getBTOProjectList();
        boolean projectFound = false;
        
        // Iterate through the project list to find the matching project.
        for (int i = 0; i < projectList.getCount(); i++) {
            BTOProject project = projectList.getBTOProject(i);
            if (project.getProjectName().equalsIgnoreCase(appliedProjectName)) {
                // Display the project details.
                System.out.println("Project Details for Your Applied Project:");
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Neighbourhood: " + project.getNeighbourhood());
                System.out.println("2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("3-Room Units Available: " + project.getNumberOfThreeRoom());
                System.out.println("Application Opening Date: " + project.getOpeningDate());
                System.out.println("Application Closing Date: " + project.getClosingDate());
                System.out.println("Manager in Charge: " + project.getHDBManagerInCharge());
                System.out.println("------------------------------------------");
                projectFound = true;
                break;
            }
        }
        
        if (!projectFound) {
            System.out.println("The project you applied for could not be found.");
        }
    }

    public static void requestWithdrawal() {
        User currentUser = LocalData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        Applicant applicant = (Applicant) currentUser;
        String appliedProjectName = applicant.getAppliedProject();

        if (appliedProjectName == null || appliedProjectName.trim().isEmpty()) {
            System.out.println("No project applied for, nothing to withdraw.");
            return;
        }
        
        BTOApplication_List applicationList = LocalData.getBTOApplicationList();
        boolean applicationFound = false;
        
        for (int i = 0; i < applicationList.getCount(); i++) {
            BTOApplication application = applicationList.getBTOApplication(i);
            if (application != null &&
                application.getProjectName().equalsIgnoreCase(appliedProjectName) &&
                application.getApplicant().getNRIC().equalsIgnoreCase(applicant.getNRIC())) {
                
                applicationList.removeBTOApplication(application);
                applicationFound = true;
                break;
            }
        }
        
        if (applicationFound) {
            applicant.setAppliedProject(null);
            System.out.println("Your application has been withdrawn successfully.");
        } else {
            System.out.println("Could not find your application to withdraw.");
        }
    }

    public static void quit() {
     
    }
    
}