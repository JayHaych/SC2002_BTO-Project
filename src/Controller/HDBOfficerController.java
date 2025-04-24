package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entity.*;

public class HDBOfficerController extends ApplicantController {
    //Implemented DIP in viewEnquiry for officer
    public void viewEnquiry(User user) {
        boolean found = false;
        int count = 1;
        System.out.println("Your own enquiries:");
    
        for (Enquiry enquiry : LocalData.getEnquiryList().getList()) {
            if (enquiry.getUser().equals(user)) {
                System.out.println(count + ". " + enquiry.getDetails());
                System.out.println("Reply: " + enquiry.getReply() + "\n");
                count++;
                found = true;
            }
        }
    
        if (!found) {
            System.out.println("No enquiries to display.");
        }
    }

    public static void viewProjects(HDBOfficer officer) {
        boolean foundProject = false;
        int count = 1;
        System.out.println("Projects You Are In Charge Of:");
    
        ArrayList<BTOProject> projectList = LocalData.getBTOProjectList().getList();
        

        for (BTOProject project : projectList) {
            if (project.hasOfficer(officer.getNRIC())) {
                System.out.println(count + ". Project Name: " + project.getProjectName());
                System.out.println("   Neighbourhood: " + project.getNeighbourhood());
                System.out.println("   2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("   3-Room Units Available: " + project.getNumberOfThreeRoom());
                System.out.println("   Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                System.out.println("------------------------------------------");
                foundProject = true;
                count++;
            }
        }
    
        System.out.println("Projects Based on Your Eligibility:");
        User currentUser = LocalData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        for (BTOProject project : projectList) {
            if (!project.isVisible()) {
                continue;
            }
    
            // Married applicants (age >= 21): Show both 2-room and 3-room flats.
            if (currentUser.getMaritalStatus().equalsIgnoreCase("Married") && currentUser.getAge() >= 21) {
                System.out.println(count + ". Project Name: " + project.getProjectName());
                System.out.println("   Neighbourhood: " + project.getNeighbourhood());
                System.out.println("   2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("   3-Room Units Available: " + project.getNumberOfThreeRoom());
                System.out.println("   Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                System.out.println("------------------------------------------");
                foundProject = true;
                count++; // Increment the counter for each project
            }
            // Single applicants (age >= 35): Only show projects with available 2-room flats.
            else if (currentUser.getMaritalStatus().equalsIgnoreCase("Single") && currentUser.getAge() >= 35) {
                System.out.println(count + ". Project Name: " + project.getProjectName());
                System.out.println("   Neighbourhood: " + project.getNeighbourhood());
                System.out.println("   2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("   Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                System.out.println("------------------------------------------");
                foundProject = true;
                count++; // Increment the counter for each project
            }
        }
    
        if (!foundProject) {
            System.out.println("No projects available based on your eligibility criteria.");
        }
    }
    
    public static void viewProjectEnquiry(User user) {
        boolean found = false;
        int count = 1;
        System.out.println("Enquiries for your project:");

        // Double check if the user is an officer and retrieve their current project
        if (user instanceof HDBOfficer) {
            HDBOfficer officer = (HDBOfficer) user;
            
            // Display enquiries related to the officer's current project only
            for (Enquiry enquiry : officer.getCurrentProject().getEnquiries()) {
                System.out.println(count + ". " + enquiry.getDetails());
                System.out.println("Reply: " + enquiry.getReply() + "\n");
                count++;
                found = true;
            }
        } else {
            System.out.println("This feature is only available for HDB Officers.");
        }

        if (!found) {
            System.out.println("No project enquiries to display.");
        }
    }
    
    public static void updateBTOProject() {
        User user = LocalData.getCurrentUser();
        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        if (!(user instanceof HDBOfficer)) {
            System.out.println("This feature is only available for HDB Officers.");
            return;
        }

        HDBOfficer officer = (HDBOfficer) user;
    
        // 1. Get the officer’s assigned project name
        String assignedName = officer.getBTOprojectName();
        if (assignedName == null || assignedName.isBlank()) {
            System.out.println("You are not assigned to any project yet.");
            return;
        }
    
        // 2. Locate the BTOProject object by name
        BTOProject projectToUpdate = null;
        for (BTOProject proj : LocalData.getBTOProjectList().getList()) {
            if (proj.getProjectName().equalsIgnoreCase(assignedName)) {
                projectToUpdate = proj;
                break;
            }
        }
        if (projectToUpdate == null) {
            System.out.println("Assigned project '" + assignedName + "' not found.");
            return;
        }
    
        // 3. Process only “Approved” bookings for this project
        boolean processedAny = false;  // ← track if we handle at least one booking
    
        for (FlatBooking booking : LocalData.getFlatBookingList().getList()) {
            if (!booking.getProjectName().equalsIgnoreCase(assignedName)) {
                continue;
            }
            if (!"Approved".equalsIgnoreCase(booking.getBookingStatus())) {
                continue;
            }
    
            // Decrement available units
            String ft = booking.getFlatType();
            if ("2-Room".equalsIgnoreCase(ft) && projectToUpdate.getNumberOfTwoRoom() > 0) {
                projectToUpdate.setNumberOfTwoRoom(projectToUpdate.getNumberOfTwoRoom() - 1);
            } else if ("3-Room".equalsIgnoreCase(ft) && projectToUpdate.getNumberOfThreeRoom() > 0) {
                projectToUpdate.setNumberOfThreeRoom(projectToUpdate.getNumberOfThreeRoom() - 1);
            }
    
            // Mark the application as “Booked”
            for (BTOApplication app : LocalData.getBTOApplicationList().getList()) {
                if (app.getApplicantName().equals(booking.getApplicantName())
                    && app.getProjectName().equalsIgnoreCase(assignedName)) {
                    app.setApplicationStatus("Booked");
                    break;
                }
            }
    
            // Mark the FlatBooking as "Booked" too so it won’t be re‐processed
            booking.setBookingStatus("Booked");
    
            System.out.println("BTO project updated for Booking ID: " + booking.getBookingID());
            processedAny = true;
        }
    
        // 4. If nothing was processed, inform the officer
        if (!processedAny) {
            System.out.println("No flat bookings to update for your project at this time.");
        }
    }
    
    
    
    public static void apply() {
        // Retrieve the current user (officer or applicant)
        User user = LocalData.getCurrentUser();
        
        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;  // Exit if no user is logged in
        }

        // Check if the current user is an officer
        if (!(user instanceof HDBOfficer)) {
            System.out.println("This feature is only available for HDB Officers.");
            return;
        }
        
        HDBOfficer officer = (HDBOfficer) user;  // Cast the user to HDBOfficer

        // Debug: Check if officer's name is valid
        if (officer.getName() == null || officer.getName().isEmpty()) {
            System.out.println("Error: Officer's name is not set.");
            return;  // Exit if officer's name is invalid
        }

        // Check if the officer has already applied for a project by comparing names
        for (BTOApplication application : LocalData.getBTOApplicationList().getList()) {
            if (application.getApplicant() != null && application.getApplicant().getName().equals(officer.getName())) {
                System.out.println("You have already applied for a project. You cannot apply for another.");
                return;  // Exit the method if the officer has already applied
            }
        }

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the project name you would like to apply for:");
        String projectName = sc.nextLine();
        
        // Check if the officer is already managing the project
        if (officer.getBTOprojectName() != null && officer.getBTOprojectName().equalsIgnoreCase(projectName)) {
            System.out.println("You cannot apply for the project that you are managing.");
            return;  // Return early if the officer is managing the project
        }
        
        boolean projectFound = false;
        
        // Iterate through the list of projects to check if the entered project is valid
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
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

        // Check eligibility for married officers (age >= 21)
        if (officer.getMaritalStatus().equals("Married") && officer.getAge() >= 21) {
            System.out.println("Enter the flat type you want to apply for (enter 2 for 2-room, 3 for 3-room):");

            while (true) { // Loop until a valid choice is entered
                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();
                    sc.nextLine(); // Consume the newline character left after nextInt()

                    if (choice == 2) {
                        flatType = "2-room";
                        break;
                    } else if (choice == 3) {
                        flatType = "3-room";
                        break; 
                    } else {
                        System.out.println("Invalid input! Please enter 2 for 2-room or 3 for 3-room.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number (2 for 2-room or 3 for 3-room).");
                    sc.nextLine();
                }
            }
        }
        // Check eligibility for single officers (age >= 35)
        else if (officer.getMaritalStatus().equals("Single") && officer.getAge() >= 35) {
            System.out.println("As a single officer, you can only apply for a 2-room flat. Proceeding to Apply for 2-room flat.");
            flatType = "2-room";
        }
        // Officer is not eligible
        else {
            System.out.println("You are not eligible to apply for a BTO application based on your marital status or age.");
            return;
        }

        // Create the application for the officer
        BTOApplication application = new BTOApplication(projectName, flatType, officer);

        // Check if the applicant field is set correctly after creating the application
        if (application.getApplicant() == null) {
            System.out.println("Error: The applicant is not set properly in the application.");
            return;  // Exit if the applicant field is not set
        }

        // Add the application to the BTO application list
        LocalData.getBTOApplicationList().addBTOApplication(application);

        // Set the applied project for the officer (if needed)
        officer.setAppliedProject(projectName);

        System.out.println("Application submitted successfully.");
    }

    

    public static void registerForProjectAsOfficer() {
        User currentUser = LocalData.getCurrentUser();


        if (!(currentUser instanceof HDBOfficer)) {
            System.out.println("Only HDB officers can register for projects as officers.");
            return;
        }

        HDBOfficer officer = (HDBOfficer) currentUser;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the project name you want to register for as an officer:");
        String projectName = sc.nextLine().trim();

        BTOProject selectedProject = null;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                selectedProject = project;
                break;
            }
        }

        if (selectedProject == null) {
            System.out.println("Project not found!");
            return;
        }

        // Check 1: Officer is not also an applicant
        for (Applicant applicant : LocalData.getApplicantList().getList()) {
            if (applicant.getNRIC().equals(officer.getNRIC())) {
                System.out.println("You cannot register as an officer because you are already an applicant.");
                return;
            }
        }

        // Check 2: Officer has not already registered as officer for any project
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.hasOfficer(officer.getNRIC())) {
                System.out.println("You have already registered as an officer for another BTO project.");
                return;
            }
        }

        // Check 3: Project has fewer than 10 officers
        if (selectedProject.getNumberOfOfficers() >= 10) {
            System.out.println("This project already has 10 officers.");
            return;
        }

        // All checks passed → add officer
        selectedProject.addOfficerInCharge(officer);
        Registration registration = new Registration(projectName,officer.getName(),"Pending");
        LocalData.getRegistrationList().addRegistration(registration);
        System.out.println("Registration request submitted for: " + selectedProject.getProjectName());
    }




    public static void viewProjectDetails(User user) {
        System.out.println("Enter the project name you want to view details for:");
        Scanner sc = new Scanner(System.in);
        String projectName = sc.nextLine();

        boolean projectFound = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                projectFound = true;
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Neighbourhood: " + project.getNeighbourhood());
                System.out.println("2-Room Units Available: " + project.getNumberOfTwoRoom());
                System.out.println("3-Room Units Available: " + project.getNumberOfThreeRoom());
                System.out.println("Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());
                break;
            }
        }

        if (!projectFound) {
            System.out.println("Project not found!");
        }
    }

    public static void updateNumberOfFlats(User user) {
  
        System.out.println("Enter the project name to update the number of flats:");
        Scanner sc = new Scanner(System.in);
        String projectName = sc.nextLine();

        boolean projectFound = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                projectFound = true;
                System.out.println("Enter the new number of 2-room units:");
                int newTwoRoom = sc.nextInt();
                System.out.println("Enter the new number of 3-room units:");
                int newThreeRoom = sc.nextInt();
                project.setNumberOfTwoRoom(newTwoRoom);
                project.setNumberOfThreeRoom(newThreeRoom);
                System.out.println("Updated number of flats for project " + projectName);
                break;
            }
        }

        if (!projectFound) {
            System.out.println("Project not found!");
        }
    }

    public static void viewRegistrationStatus(User user) {
        // Check if the user is registered for a project
        String projectName = ((HDBOfficer) user).getBTOprojectName();  // Assuming that this field is correctly set

        if (projectName != null && !projectName.isEmpty()) {
            System.out.println("You are registered for the project: " + projectName);
        } else {
            System.out.println("You are not registered for any project.");
        }
    }

    // Officer-specific method to view the enquiries of the project they are handling
    public static void viewEnquiriesOfProject(User user) {
        String projectName = ((HDBOfficer) user).getBTOprojectName();

        if (projectName == null || projectName.isEmpty()) {
            System.out.println("You are not registered for any project, hence no enquiries to view.");
            return;
        }

        boolean foundEnquiries = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                System.out.println("Enquiries for project " + projectName + ":");
                for (Enquiry enquiry : project.getEnquiries()) {
                    System.out.println("Enquiry from: " + enquiry.getUserName() + " - " + enquiry.getDetails());
                    foundEnquiries = true;
                }
                break;
            }
        }

        if (!foundEnquiries) {
            System.out.println("No enquiries found for this project.");
        }
    }

    public static void replyToEnquiry(User user) {

        //Casting to access attribute
        HDBOfficer officer = (HDBOfficer) user;
        String officerProject = officer.getBTOprojectName();

        if (officerProject == null || officerProject.isBlank()) {
            System.out.println("You are not registered for any project");
            return;
        }

        List<Enquiry> targetEnquiries = new ArrayList<>();
        for (Enquiry e : LocalData.getEnquiryList().getList()) {
            if (e.getProjectName().equalsIgnoreCase(officerProject)) {
                targetEnquiries.add(e);
            }
        }

        if (targetEnquiries.isEmpty()) {
            System.out.println("There are no enquiries for project\""+ officerProject + "\".");
            return;
        }

        System.out.println("Enquiries for project \"" + officerProject + "\":");
        for (int i = 0; i < targetEnquiries.size(); i++) {
            Enquiry e = targetEnquiries.get(i);
            System.out.printf("%d. %s  (asked by %s)%n", i + 1, e.getDetails(), e.getUserName());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the enquiry number to reply: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 1 || idx > targetEnquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        Enquiry chosen = targetEnquiries.get(idx - 1);
        System.out.println("Enter your reply: ");
        String reply = sc.nextLine();

        chosen.setReply(reply);
        System.out.println("Reply recorded successfully.");
    }

    // Officer-specific method to generate a receipt for an application
    public static void getReceipt(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the applicant name to generate a receipt:");
        String applicantnameString = sc.nextLine().trim();
    
        boolean applicationFound = false;
    
        for (BTOApplication application : LocalData.getBTOApplicationList().getList()) {


            if (application.getApplicantName().equalsIgnoreCase(applicantnameString)) {
                applicationFound = true;
                System.out.println("Generating receipt for " + application.getApplicantName() + "...");
    
                // Cast applicant to actual Applicant class to get more details
                Applicant applicant = null;
                for (Applicant a : LocalData.getApplicantList().getList()) {
                    if (a.getName().equalsIgnoreCase(application.getApplicantName())) {
                        applicant = a;
                        break;
                    }
                }
    
                System.out.println("======= Booking Receipt =======");
                System.out.println("Applicant Name : " + application.getApplicantName());
    
                if (applicant != null) {
                    System.out.println("NRIC           : " + applicant.getNRIC());
                    System.out.println("Age            : " + applicant.getAge());
                    System.out.println("Marital Status : " + applicant.getMaritalStatus());
                } else {
                    System.out.println("NRIC           : [Unknown]");
                    System.out.println("Age            : [Unknown]");
                    System.out.println("Marital Status : [Unknown]");
                }
    
                System.out.println("Project Name   : " + application.getProjectName());
                System.out.println("Flat Type      : " + application.getFlatType());
                System.out.println("Status         : " + application.getApplicationStatus());
                System.out.println("================================");
                break;
            }
        }
    
        if (!applicationFound) {
            System.out.println("Application not found!");
        }
    }
}
