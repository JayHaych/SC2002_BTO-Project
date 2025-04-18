package Controller;

import Entity.User;
import Entity.LocalData;

import java.util.Scanner;

import Entity.*;

public class HDBOfficerController extends ApplicantController {

    public static void registerForProjectAsOfficer() {
        User currentUser = LocalData.getCurrentUser();
        
        HDBOfficer officer = (HDBOfficer) currentUser;  // Upcast to HDBOfficer class
        
        System.out.println("Enter the project name you want to register for as an officer:");
        Scanner sc = new Scanner(System.in);
        String projectName = sc.nextLine();  // Get project name from the user
        boolean projectFound = false;
    
        // Check if the project exists in the project list
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                projectFound = true;

                // project.addOfficerInCharge(officer); SHOULD BE REGISTER, NOT STRAIGHT AWAY ADD
                Registration registration = new Registration(officer.getName(), projectName, "Pending");
                LocalData.getRegistrationList().addRegistration(registration);  // Add registration to the list
                System.out.println("Successfully registered for the project: " + projectName);
                break;
            }
        }
    
        if (!projectFound) {
            System.out.println("Project not found!");
        }
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
        String projectName = ((HDBOfficer) user).getBTOprojectName();

        if (projectName == null || projectName.isEmpty()) {
            System.out.println("You are not registered for any project, hence no enquiries to reply to.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the enquiry number you want to reply to:");

        boolean foundEnquiry = false;
        for (BTOProject project : LocalData.getBTOProjectList().getList()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                System.out.println("Available enquiries for project: " + projectName);
                for (int i = 0; i < project.getEnquiries().size(); i++) {
                    Enquiry enquiry = project.getEnquiries().get(i);
                    System.out.println((i + 1) + ". " + enquiry.getDetails());
                }

                int enquiryChoice = sc.nextInt();
                sc.nextLine(); // consume newline

                if (enquiryChoice > 0 && enquiryChoice <= project.getEnquiries().size()) {
                    Enquiry selectedEnquiry = project.getEnquiries().get(enquiryChoice - 1);
                    System.out.println("You selected the enquiry: " + selectedEnquiry.getDetails());
                    System.out.println("Enter your reply: ");
                    String reply = sc.nextLine();

                    selectedEnquiry.setDetails(selectedEnquiry.getDetails() + "\nOfficer's reply: " + reply);
                    System.out.println("Reply added successfully.");
                    foundEnquiry = true;
                    break;
                } else {
                    System.out.println("Invalid enquiry number.");
                }
            }
        }

        if (!foundEnquiry) {
            System.out.println("No enquiries found for this project.");
        }
    }

    // Officer-specific method to generate a receipt for an application
    public static void getReceipt(User user) {
        // Logic to generate receipt for an application
        System.out.println("Enter the application ID to generate a receipt:");
        String applicationId = sc.nextLine();

        boolean applicationFound = false;
        for (BTOApplication application : LocalData.getBTOApplicationList().getList()) {
            if (application.getApplicationID().equals(applicationId)) {
                applicationFound = true;
                // Logic to generate and print a receipt
                System.out.println("Receipt for Application ID: " + applicationId);
                System.out.println("Project Name: " + application.getProjectName());
                System.out.println("Flat Type: " + application.getFlatType());
                System.out.println("Application Status: " + application.getApplicationStatus());
                break;
            }
        }

        if (!applicationFound) {
            System.out.println("Application not found!");
        }
    }
}
