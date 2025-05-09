package Controller;

import java.util.*;
import Entity.*;
import Interface.ViewEnquiryInterface;

public class ApplicantController implements ViewEnquiryInterface {

    public static void submitEnquiry(User user) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter your enquiry:");
        String details = sc.nextLine();
        System.out.println("Enter the project name: ");
        String projectName = sc.nextLine();

        // Create the enquiry
        Enquiry enquiry = new Enquiry(details, projectName, user);
        
        boolean projectFound = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                project.getEnquiries().add(enquiry); // Add enquiry to the project's own enquiry list
                projectFound = true;
                break;
            }
        }
        
        if (!projectFound) {
            System.out.println("No project found with the name '" + projectName + "'. Enquiry not saved.");
            return;
        }
        
        // Add the enquiry to the global enquiry list in LocalData
        LocalData.getEnquiryList().addEnquiry(enquiry);
        System.out.println("Enquiry submitted successfully.");
    }

    public void viewEnquiry(User user) {
        boolean found = false;
        int count = 1;
        System.out.println("Your Enquiries:");
        
        // Only show enquiries that belong to the applicant
        for (Enquiry enquiry : LocalData.getEnquiryList().getList()) {
            if (enquiry.getUserName().equals(user.getName())) {
                System.out.println(count + ". " + enquiry.getDetails());
                System.out.println("Reply: "+ enquiry.getReply() +"\n");
                count++;
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No enquiries to display.");
        }
    }

    public static void editEnquiry(User user) {
        // New list of user enquiries
        List<Enquiry> userEnquiries = new ArrayList<>();
        for (Enquiry enquiry : LocalData.getEnquiryList().getList()) {
            if (enquiry.getUserName().equals(user.getName())) {
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
    }

    public static void deleteEnquiry(User user) {
        List<Enquiry> userEnquiries = new ArrayList<>();
        for (Enquiry enquiry : LocalData.getEnquiryList().getList()) {
            if (enquiry.getUserName().equals(user.getName())) {
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
            LocalData.getEnquiryList().removeEnquiry(enquiryToDelete);  // Remove the enquiry from the global enquiry list in LocalData
            System.out.println("Enquiry deleted successfully.");
        } else {
            System.out.println("Invalid enquiry number.");
        }
    }
    

    public static void apply() {
        User user = LocalData.getCurrentUser();
        
        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;  // Exit if no user is logged in
        }
    
        if (!(user instanceof Applicant)) {
            System.out.println("This feature is only available for Applicants.");
            return;
        }
        
        Applicant applicant = (Applicant) user;  // Cast the user to Applicant
    
        // Check if applicant's name is valid
        if (applicant.getName() == null || applicant.getName().isEmpty()) {
            System.out.println("Error: Applicant's name is not set.");
            return;  // Exit if applicant's name is invalid
        }
    
        // Check if the applicant has already applied for a project by comparing names
        for (BTOApplication application : LocalData.getBTOApplicationList().getList()) {
            if (application.getApplicantName() != null && application.getApplicantName().equals(applicant.getName())) {
                System.out.println("You have already applied for a project. You cannot apply for another.");
                return;  // Exit the method if the applicant has already applied
            }
        }
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the project name you would like to apply for:");
        String projectName = sc.nextLine();
        
        boolean projectFound = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            // Check if the project exists and is visible
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                if (!project.isVisible()) {
                    System.out.println("This project is not visible and cannot be applied for.");
                    return;  // Exit if the project is not visible
                }
                projectFound = true;
                break;
            }
        }
        
        // If the project name is invalid, print an error and return
        if (!projectFound) {
            System.out.println("Invalid project name. Please choose a valid project from the list.");
            return;  // Exit the method if the project is not found
        }
        
        String flatType = "";
        
        // Check eligibility for married applicants (age >= 21)
        if (user.getMaritalStatus().equals("Married") && user.getAge() >= 21) {
            System.out.println("You are eligible to apply for 2-room or 3-room flats.");
            
            // We assume flatType has already been decided from the application
            // No need to ask again, use the flatType from the BTOApplication (already set in the application CSV)
            while (true) {
                System.out.print("Enter flat type (2 for 2-room, 3 for 3-room): ");
                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "2":
                        flatType = "2-room";
                        break;
                    case "3":
                        flatType = "3-room";
                        break;
                    default:
                        System.out.println("Invalid selection. Please enter 2 or 3.");
                        continue;  // ask again
                }
                break;  // valid choice, exit loop
            }  // Example: change this as per actual logic (use the same flatType in the application)
        }
        // Check eligibility for single applicants (age >= 35)
        else if (user.getMaritalStatus().equals("Single") && user.getAge() >= 35) {
            System.out.println("As a single applicant, you can only apply for a 2-room flat.");
            flatType = "2-room";
        }
        // Applicant is not eligible
        else {
            System.out.println("You are not eligible to apply for a BTO application based on your marital status or age.");
            return;
        }
        
        // Create and add the application
        BTOApplication application = new BTOApplication(projectName, flatType, user); // Create application with the current user
        
        LocalData.getBTOApplicationList().addBTOApplication(application);  // Add application to the list
    
        applicant.setAppliedProject(projectName); // Set the applied project for the applicant
        
        System.out.println("Application submitted successfully.");
    }

    
    

    public static void viewProjects() {
        User currentUser = LocalData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        BTOProject_List projectList = LocalData.getBTOProjectList();
        boolean foundProject = false;
        
        System.out.println();
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
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Neighbourhood: " + project.getNeighbourhood());
                System.out.println("2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                System.out.println("------------------------------------------");
                foundProject = true;
            }
        }
    
        if (!foundProject) {
            System.out.println("No projects available based on your eligibility criteria.");
            System.out.println();
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
                System.out.println("Manager in Charge: " + project.getHDBManagerInChargeName());
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
                application.getApplicantName().equalsIgnoreCase(applicant.getName())) {
                
                application.setWithdrawalRequested(true);
                applicationFound = true;
                break;
            }
        }
        
        if (applicationFound) {
            applicant.setAppliedProject("false");
            System.out.println("Your withdrawal request is submitted successfully.");
        } else {
            System.out.println("Could not find your application to withdraw.");
        }
    }

    public static void changePassword(){
        User currentUser = LocalData.getCurrentUser();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your new password");
        String newPassword = sc.nextLine();
        currentUser.setPassword(newPassword);
        // need to change password here
        System.out.println("Password saved successfully");
    }

    public static void createFlatBooking() {
        User user = LocalData.getCurrentUser();
        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        // 1. Find their successful application by comparing name (String-based comparison)
        BTOApplication_List appList = LocalData.getBTOApplicationList();
        BTOApplication successfulApp = null;
        for (BTOApplication app : appList.getList()) {
            if (app.getApplicantName() != null && app.getApplicantName().equals(user.getName())
                && "Successful".equalsIgnoreCase(app.getApplicationStatus())) {
                successfulApp = app;
                break;
            }
        }
    
        if (successfulApp == null) {
            System.out.println("You do not have a successful application to book a flat.");
            return;
        }
    
        // 2. Check they haven't already booked (compare applicant's name)
        FlatBooking_List bookingList = LocalData.getFlatBookingList();
        for (FlatBooking fb : bookingList.getList()) {
            if (fb.getApplicantName() != null && fb.getApplicantName().equals(user.getName())
                && !"Pending".equalsIgnoreCase(fb.getBookingStatus())) {
                System.out.println("You have already completed a flat booking.");
                return;
            }
        }
    
        // 3. Use the flatType from the successful application (do not ask the user for it again)
        String flatType = successfulApp.getFlatType();  // Use the flat type from the application
    
        // 4. Generate a booking ID (simple increment, can be replaced with UUID if desired)
        String bookingID = String.valueOf(bookingList.getList().size() + 1);
        String projectName = successfulApp.getProjectName();
        String bookingStatus = "Pending";  // initial status
    
        // 5. Create and store the booking
        FlatBooking booking = new FlatBooking(
            bookingID,
            projectName,
            flatType,
            bookingStatus,
            user.getName()  // Store applicant's name here
        );
        booking.setApplicantName(user.getName()); // Set applicant's name for booking
    
        bookingList.addFlatBooking(booking);
    
        System.out.println("Flat booking created successfully! Your Booking ID is " + bookingID + ".");
    }
    

    public static void quit() {
     
    }
    
}