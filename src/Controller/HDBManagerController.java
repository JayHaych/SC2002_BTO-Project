package Controller;

import java.nio.file.SecureDirectoryStream;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import Entity.*;

public class HDBManagerController{
    private boolean applicationApproved;
    private boolean registrationApproved;
    private boolean withdrawalApproved;
    private BTOProject_List projectList;
    private FlatBooking_List flatBookingList;
    private Enquiry_List enquiryList;
    private BTOApplication_List applicationList;
    private Registration_List registrationList;
    private HDBOfficer_List officerList;
    private String applicationStatus;
    
    public HDBManagerController(boolean applicationApproved, boolean registrationApproved, boolean withdrawalApproved){
        this.applicationApproved = applicationApproved;
        this.registrationApproved = registrationApproved;
        this.withdrawalApproved = withdrawalApproved;
        this.projectList =  LocalData.getBTOProjectList();
        this.flatBookingList = LocalData.getFlatBookingList();
        this.enquiryList = LocalData.getEnquiryList();
        this.applicationList = LocalData.getBTOApplicationList();
        this.registrationList = LocalData.getRegistrationList();
        this.officerList = LocalData.getHDBOfficerList();
    }

    public void createProject(){
        User currentUser = LocalData.getCurrentUser();
        HDBManager manager = (HDBManager) currentUser;


        if (! manager.getBTOprojectname().equals("null")) {
            System.out.println("You already have an ongoing project: " + manager.getBTOprojectname());
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Project Name: ");
        String projectName = sc.nextLine();
        System.out.print("Neighbourhood: ");
        String neighbourhood = sc.nextLine();
        System.out.print("Number of 2-room flats: ");
        int numberOfTwoRoom = sc.nextInt();
        System.out.print("Price of 2-room flats: $");
        int sellingPriceOfTwoRoom = sc.nextInt();
        System.out.print("Number of 3-room flats: ");
        int numberOfThreeRoom = sc.nextInt();
        System.out.print("Price of 3-room flats: $");
        int sellingPriceOfThreeRoom = sc.nextInt();

        sc.nextLine();

        System.out.print("Opening Date (DD/MM/YYYY): ");
        String openingDate = sc.nextLine();
        System.out.print("Closing Date (DD/MM/YYYY): ");
        String closingDate = sc.nextLine();
        System.out.print("HDB Manager's Name: ");
        String managerName = sc.nextLine();
        System.out.print("Number of officer slots: ");
        int availableOfficerSlot = sc.nextInt();

        sc.nextLine();
        
        String[] OfficerInChargeNames = {}; 
        boolean isVisible = true;

        BTOProject project = new BTOProject(projectName, neighbourhood, numberOfTwoRoom, sellingPriceOfTwoRoom, numberOfThreeRoom, sellingPriceOfThreeRoom, openingDate, closingDate, managerName, availableOfficerSlot, OfficerInChargeNames, isVisible);
        // projectList.addBTOProject(project);
        manager.setCurrentProject(project);
        manager.setBTOprojectname(projectName);
        project.setHDBManagerInCharge(manager);
        LocalData.getBTOProjectList().addBTOProject(project);
        System.out.println("Project successfully created!");
        
    }

    public void editProject(){
        viewAllProjects();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter project number: ");
        int projectNumber = sc.nextInt();
        sc.nextLine();

        if (projectNumber <= 0 || projectNumber > projectList.getCount()){
            System.out.println("Invalid Project Number.");
            return;
            }
        
        BTOProject project = projectList.getBTOProject(projectNumber - 1);
        System.out.println("1.Edit Project Name\n2.Edit Neighbourhood\n3.Edit number of two room flats\n4.Edit number of three room flats\n5.Edit opening date of project\n6.Edit closing date of the project\n7.Edit HDB Manager in-charge\n8.Edit available office slots\n9.Done editing");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.print("Edit project name (current: " + project.getProjectName() + ") :");
                project.setProjectName(sc.nextLine());
                sc.nextLine();
                break;
            case 2:
                System.out.print("Edit neighbourhood (current: " + project.getNeighbourhood() + ") :");
                project.setNeighbourhood(sc.nextLine());
                sc.nextLine();
                break;
            case 3:
                System.out.print("Edit number of two room flats (current: " + project.getNumberOfTwoRoom() + ") :");
                project.setNumberOfTwoRoom(sc.nextInt());
                sc.nextLine();

                break;
            case 4:
                System.out.print("Edit the number of three room flats (current: " + project.getNumberOfThreeRoom() + ") :");
                project.setNumberOfThreeRoom(sc.nextInt());
                sc.nextLine();

                break;
            case 5:
                System.out.print("Edit opening date of project (current: " + project.getOpeningDate() + ") :");
                project.setOpeningDate(sc.nextLine());
                sc.nextLine();
                break;
            case 6:
                System.out.print("Edit closing date of the project (current: " + project.getClosingDate() + ") :");
                project.setClosingDate(sc.nextLine());
                sc.nextLine();
                break;
            case 7:
                System.out.print("Edit HDB Manager in-charge (current: " + project.getHDBManagerInCharge() + ") :");
                project.setHDBManagerInChargeName(sc.nextLine());
                sc.nextLine();
                break;
            case 8:
                System.out.print("Edit available officer slots (current: " + project.getAvailableOfficerSlot() + ") :");
                project.setAvailableOfficerSlot(sc.nextInt());
                sc.nextLine();
                break;
            case 9:
                System.out.print("BTO Project updated!");
                return;
        }
    }

    public void deleteProject(){
        viewAllProjects();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of BTO project to delete: ");
        int projectNumber = sc.nextInt();

        if (projectNumber <=0 || projectNumber > projectList.getCount()){
            System.out.println("Invalid Project Number.");
            return;
        }

        BTOProject project = projectList.getBTOProject(projectNumber - 1);
        projectList.removeBTOProject(project);
        System.out.println("BTO Project successfully deleted!");
    }

    public void setVisibility(){
        viewAllProjects();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter project number: ");
        int projectNumber = sc.nextInt();

        if (projectNumber <= 0 || projectNumber > projectList.getCount()){
            System.out.println("Invalid Project Number.");
            return;
        }
        projectList = LocalData.getBTOProjectList();
        BTOProject project = projectList.getBTOProject(projectNumber - 1);
        if (project.isVisible() == true){
            System.out.println("Project Visibility: Visible");
        } else{
            System.out.println("Project Visibility: Not Visible.");
        }
        System.out.println("Set Project Visibility:\n1. Visible\n2. Not Visible");
        int choice = sc.nextInt();
        if (choice == 1){
            project.setVisible(true);
            System.out.println("Project is now visible!");
        } else if (choice == 2){
            project.setVisible(false);
            System.out.println("Project is now not visible!");
        }
    }

    public void viewAllProjects(){
        System.out.println("---------ALL PROJECTS---------");
        for (int i = 0; i < projectList.getCount(); i++){
            if (projectList.getCount() > 0){
                BTOProject btoProject = projectList.getBTOProject(i);
                System.out.println((i + 1) + ". " + btoProject.getProjectName());
            } else {
                System.out.println("No projects found.");
            }
        }
    }

    public void viewMyProjects(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String managerName = sc.nextLine();

        boolean nameIsInSystem = false;
        for(int i = 0; i < projectList.getCount(); i++){
            BTOProject project = projectList.getBTOProject(i);
            if (managerName.toLowerCase().equals(project.getHDBManagerInChargeName())){
                System.out.println(project.getProjectName());
                nameIsInSystem = true;
            } 
        }
        if(!nameIsInSystem){
            System.out.println("No projects found under manager name " + managerName);
        }
    }

    public boolean approveRegistration(){
        registrationList = LocalData.getRegistrationList();
        officerList = LocalData.getHDBOfficerList();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < registrationList.getRegistrationCount(); i++){
            System.out.println("All HDB Officer Registrations: ");
            System.out.println((i+1) + ". " + registrationList.getRegistration(i).getOfficerName());

            String registrationStatus = registrationList.getRegistration(i).getApprovalStatus();
            if(registrationStatus.equals("Pending")){
                System.out.println("\nRegistration: " + (i+1) + ":");
                System.out.println("Officer Name: " + registrationList.getRegistration(i).getOfficerName());
                System.out.println("Project Name " + registrationList.getRegistration(i).getProjectName());
                System.out.println("Registration Status: " + registrationList.getRegistration(i).getApprovalStatus());
                System.out.println("Approve Registration?");
                System.out.println("1.YES");
                System.out.println("2.NO");
                int choice = sc.nextInt();
            
                if (choice == 1){
                    registrationList.getRegistration(i).setApprovalStatus("Successful");
                    System.out.println("Registration Approved!");
                    String target_name = registrationList.getRegistration(i).getOfficerName();
                    String target_BTO_ProjectName = registrationList.getRegistration(i).getProjectName();

                    System.out.println(target_BTO_ProjectName);
                    System.out.println(target_name);
                    
                    for (HDBOfficer officer : officerList.getList())
                    {
                        if (officer.getName().equals(target_name))
                        {
                            officer.setBTOprojectName(target_BTO_ProjectName);
                        }
                    }
                    

                    return registrationApproved;

                } else if(choice == 2){
                    registrationList.getRegistration(i).setApprovalStatus("Rejected");
                    System.out.println("Registration Rejected.");
                    registrationApproved = false;
                    return registrationApproved;
                }

            }
        }
        return registrationApproved;
    }

    public boolean approveApplication() {
        BTOApplication_List applicationList = LocalData.getBTOApplicationList();
        Scanner sc = new Scanner(System.in);
        boolean sawPending = false;
    
        for (int i = 0; i < applicationList.getCount(); i++) {
            BTOApplication app = applicationList.getBTOApplication(i);
    
            // Only consider pending applications
            if (!"Pending".equalsIgnoreCase(app.getApplicationStatus())) {
                continue;
            }
            sawPending = true;
    
            // Display details
            System.out.println("\nApplication " + (i + 1) + ":");
            System.out.println(" Applicant Name: " + app.getApplicantName());
            System.out.println(" Project       : " + app.getProjectName());
            System.out.println(" Flat Type     : " + app.getFlatType());
            System.out.println(" Status        : " + app.getApplicationStatus());
    
            // Force a valid choice
            int choice;
            while (true) {
                System.out.println("Approve Application?");
                System.out.println(" 1. YES");
                System.out.println(" 2. NO");
                String line = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(line);
                    if (choice == 1 || choice == 2) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    // ignore
                }
                System.out.println("Invalid input. Please enter 1 (YES) or 2 (NO).");
            }
    
            // Handle approval or rejection
            if (choice == 1) {
                // Find project
                BTOProject project = null;
                for (BTOProject p : LocalData.getBTOProjectList().getList()) {
                    if (p.getProjectName().equalsIgnoreCase(app.getProjectName())) {
                        project = p;
                        break;
                    }
                }
                if (project == null) {
                    System.out.println("Project not found. Cannot approve.");
                    applicationApproved = false;
                    return applicationApproved;
                }
    
                // Check availability
                String ft = app.getFlatType();
                if (ft.equalsIgnoreCase("2-Room")) {
                    if (project.getNumberOfTwoRoom() > 0) {
                        project.setNumberOfTwoRoom(project.getNumberOfTwoRoom() - 1);
                        app.setApplicationStatus("Successful");
                        applicationApproved = true;
                        System.out.println("Application Approved!");
                    } else {
                        app.setApplicationStatus("Rejected");
                        applicationApproved = false;
                        System.out.println("No 2-Room units available. Application Rejected.");
                    }
                } else if (ft.equalsIgnoreCase("3-Room")) {
                    if (project.getNumberOfThreeRoom() > 0) {
                        project.setNumberOfThreeRoom(project.getNumberOfThreeRoom() - 1);
                        app.setApplicationStatus("Successful");
                        applicationApproved = true;
                        System.out.println("Application Approved!");
                    } else {
                        app.setApplicationStatus("Rejected");
                        applicationApproved = false;
                        System.out.println("No 3-Room units available. Application Rejected.");
                    }
                } else {
                    app.setApplicationStatus("Rejected");
                    applicationApproved = false;
                    System.out.println("Unknown flat type. Application Rejected.");
                }
    
                return applicationApproved;
            } else {
                // choice == 2
                app.setApplicationStatus("Rejected");
                applicationApproved = false;
                System.out.println("Application Rejected.");
                return applicationApproved;
            }
        }
    
        if (!sawPending) {
            System.out.println("There are no pending applications to process.");
        }
        return applicationApproved;
    }
    

    public boolean approveWithdrawal(){
        applicationList = LocalData.getBTOApplicationList();
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < applicationList.getCount(); i++){
            if (applicationList.getBTOApplication(i).getWithdrawalRequested() == true){
                System.out.println("All withdrawal requests: ");
                System.out.println((i+1) + ". " + applicationList.getBTOApplication(i).getApplicantName());
                if (applicationList.getBTOApplication(i).getWithdrawalApproved().equals(false)){
                    System.out.println("\nApplication " + (i+1) + ":");
                    System.out.println("Application Name: " + applicationList.getBTOApplication(i).getApplicantName());
                    System.out.println("Project: " + applicationList.getBTOApplication(i).getProjectName());
                    System.out.println("Flat Type: " + applicationList.getBTOApplication(i).getFlatType());
                    System.out.println("Withdrawal Status: " + applicationList.getBTOApplication(i).getWithdrawalRequested());
                    System.out.println("Approve Withdrawal?");
                    System.out.println("1. YES");
                    System.out.println("2. NO");
                    int choice =  sc.nextInt();

                    if(choice == 1 ){
                        applicationList.getBTOApplication(i).setWithdrawalApproved(true);
                        applicationList.getBTOApplication(i).setWithdrawalRequested(null);
                        System.out.println("Withdrawal Approved!");
                        applicationList.removeBTOApplication(applicationList.getBTOApplication(i));
                        return withdrawalApproved;
                    } else if(choice == 2){
                        applicationList.getBTOApplication(i).setWithdrawalApproved(false);
                        applicationList.getBTOApplication(i).setWithdrawalRequested(null);
                        System.out.println("Withdrawal Rejected.");
                        return withdrawalApproved;
                    }
                }
            } else{
                System.out.println("No withdrawal requested.");
            }
        }
        return withdrawalApproved;
    }


    public void generateReport(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Married Applicants");
        System.out.println("2.Single Applicants");
        System.out.println("3.Two-Room Flats");
        System.out.println("4.Three-Room Flats");
        System.out.println("5.All applicants");
        System.out.println("Please enter your choice (1-5): ");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.println("Filter for Married Applicants:");
                break;

            case 2:
                System.out.println("Filter for Single Applicants:");
                break;

            case 3:
                System.out.println("Filter for Two-Room Flats:");
                break;

            case 4:
                System.out.println("Filter for Three-Room Flats:");
                break;

            case 5:
                System.out.println("All applicants:");
                break;

            default:
                System.out.println("Invalid choice");
                return;

        }



        for (FlatBooking flatBooking : LocalData.getFlatBookingList().getList()){
            String maritalStatus = null;
            int age = 0;
            String applicantName = flatBooking.getApplicantName();
            for (Applicant applicant : LocalData.getApplicantList().getList())
            {
                if (applicant.getName().equals(applicantName)){
                    applicantName = applicant.getName();
                    age = applicant.getAge();
                    maritalStatus = applicant.getMaritalStatus();
                }
            }
            String flatType = flatBooking.getFlatType();
            String projectName = flatBooking.getProjectName();
        

            switch(choice){
                case 1:
                    // System.out.println("Filter for Married Applicants:");
                    if(maritalStatus.toLowerCase().equals("married")){
                        System.out.println("------------------------");
                        System.out.println("Applicant: " + applicantName);
                        System.out.println("Age: " + age);
                        System.out.println("Marital Status: " + maritalStatus);
                        System.out.println("Flat Type: " + flatType);
                        System.out.println("Project Name: " + projectName);
                    }
                    break;
                
                case 2:
                    // System.out.println("Filter for Single Applicants:");
                    if(maritalStatus.toLowerCase().equals("single")){
                        System.out.println("------------------------");
                        System.out.println("Applicant: " + applicantName);
                        System.out.println("Age: " + age);
                        System.out.println("Marital Status: " + maritalStatus);
                        System.out.println("Flat Type: " + flatType);
                        System.out.println("Project Name: " + projectName);
                    }
                    break;

                case 3:
                    // System.out.println("Filter for Two-Room Flats:");
                    if(flatType.toLowerCase().equals("2-room")){
                        System.out.println("------------------------");
                        System.out.println("Applicant: " + applicantName);
                        System.out.println("Age: " + age);
                        System.out.println("Marital Status: " + maritalStatus);
                        System.out.println("Flat Type: " + flatType);
                        System.out.println("Project Name: " + projectName);
                    }
                    break;

                case 4:
                    // System.out.println("Filter for Three-Room Flats:");
                    if(flatType.toLowerCase().equals("3-room")){
                        System.out.println("------------------------");
                        System.out.println("Applicant: " + applicantName);
                        System.out.println("Age: " + age);
                        System.out.println("Marital Status: " + maritalStatus);
                        System.out.println("Flat Type: " + flatType);
                        System.out.println("Project Name: " + projectName);
                    }
                    break;

                case 5:
                    // System.out.println("All applicants:");
                    System.out.println("------------------------");
                    System.out.println("Applicant: " + applicantName);
                    System.out.println("Age: " + age);
                    System.out.println("Marital Status: " + maritalStatus);
                    System.out.println("Flat Type: " + flatType);
                    System.out.println("Project Name: " + projectName);
                    break;
                }
            
        }
    }

    public void viewAllEnquiries(){
        enquiryList = LocalData.getEnquiryList();
        for (int i = 0; i < enquiryList.getEnquiryCount(); i ++){
            // BTOProject project = projectList.getBTOProject(i);

            System.out.println("All enquiries: ");
            System.out.println((i+1) + ". " + enquiryList.getEnquiry(i).getUserName());
            System.out.println("\nApplication " + (i+1) +":");
            System.out.println("Applicant Name: " + enquiryList.getEnquiry(i).getUserName());
            System.out.println("BTO Project: " + enquiryList.getEnquiry(i).getProjectName());
            System.out.println("Enquiry: " + enquiryList.getEnquiry(i).getDetails());
            System.out.println("Manager Reply: " + enquiryList.getEnquiry(i).getReply());
        }
    }

    public void replyEnquiry(){
        Scanner sc = new Scanner(System.in);
        enquiryList = LocalData.getEnquiryList();
        for (int i = 0; i<enquiryList.getEnquiryCount(); i++){
            System.out.println("All enquiries: ");
            System.out.println((i+1) + ". " + enquiryList.getEnquiry(i).getUserName());
            System.out.println("\nApplication " + (i+1) +":");
            System.out.println("Applicant Name: " + enquiryList.getEnquiry(i).getUserName());
            System.out.println("BTO Project: " + enquiryList.getEnquiry(i).getProjectName());
            System.out.println("Enquiry: " + enquiryList.getEnquiry(i).getDetails());
            System.out.println("Reply Enquiry?");
            System.out.println("1.YES");
            System.out.println("2.NO");

            boolean hasReplied = false;
            while(!hasReplied){
                int choice = sc.nextInt();

                if (choice == 1){
                    sc.nextLine();
                    System.out.print("Please Enter Reply: ");
                    String reply = sc.nextLine();
                    enquiryList.getEnquiry(i).setReply(reply);
                    hasReplied = true;
                } else if (choice == 2){
                    System.out.println("No reply given.");
                    // String reply = "No reply given.";
                    // enquiryList.getEnquiry(i).setReply(reply);
                    hasReplied = true;
                } else{
                    System.out.println("Invalid Input.");
                }
            }
        }
        
    }

    public void approveFlatBooking() {
        // Retrieve the current user (HDB Manager)
        User user = LocalData.getCurrentUser();
        
        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;  // Exit if no user is logged in
        }

        // Check if the current user is a HDB Manager
        if (!(user instanceof HDBManager)) {
            System.out.println("This feature is only available for HDB Managers.");
            return;
        }

        HDBManager manager = (HDBManager) user;  // Cast the user to HDBManager
        
        // Retrieve the list of all flat bookings
        FlatBooking_List flatBookingList = LocalData.getFlatBookingList();
        
        System.out.println("Pending Flat Bookings:");
        
        boolean foundPendingBooking = false;

        // Iterate through the list of flat bookings
        for (FlatBooking flatBooking : flatBookingList.getList()) {
            if ("Pending".equalsIgnoreCase(flatBooking.getBookingStatus())) {
                foundPendingBooking = true;
                System.out.println("Booking ID: " + flatBooking.getBookingID());
                System.out.println("Project Name: " + flatBooking.getProjectName());
                System.out.println("Flat Type: " + flatBooking.getFlatType());
                System.out.println("Applicant Name: " + flatBooking.getApplicantName());
                System.out.println("----------------------------------");

                // Check if the manager wants to approve this booking
                Scanner sc = new Scanner(System.in);
                System.out.println("Do you want to approve this booking? (yes/no)");
                String approvalResponse = sc.nextLine().trim().toLowerCase();

                if ("yes".equals(approvalResponse)) {
                    // Find the project associated with the booking
                    BTOProject projectToApprove = null;
                    for (BTOProject project : LocalData.getBTOProjectList().getList()) {
                        if (project.getProjectName().equalsIgnoreCase(flatBooking.getProjectName())) {
                            projectToApprove = project;
                            break;
                        }
                    }

                    if (projectToApprove == null) {
                        System.out.println("Project not found.");
                        continue;
                    }

                    // Check available units for the flat type
                    boolean isAvailable = false;
                    if ("2-room".equalsIgnoreCase(flatBooking.getFlatType()) && projectToApprove.getNumberOfTwoRoom() > 0) {
                        isAvailable = true;
                        projectToApprove.setNumberOfTwoRoom(projectToApprove.getNumberOfTwoRoom() - 1);
                    } else if ("3-room".equalsIgnoreCase(flatBooking.getFlatType()) && projectToApprove.getNumberOfThreeRoom() > 0) {
                        isAvailable = true;
                        projectToApprove.setNumberOfThreeRoom(projectToApprove.getNumberOfThreeRoom() - 1);
                    }

                    // If there are available units, approve the booking
                    if (isAvailable) {
                        flatBooking.setBookingStatus("Successful");
                        System.out.println("Flat booking approved successfully!");
                    } else {
                        System.out.println("No available units for this flat type.");
                    }
                } else {
                    System.out.println("Flat booking not approved.");
                }
            }
        }

        if (!foundPendingBooking) {
            System.out.println("No pending flat bookings to approve.");
        }
    }

    public void changePassword(){
        User currentUser = LocalData.getCurrentUser();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your new password");
        String newPassword = sc.nextLine();
        currentUser.setPassword(newPassword);
        // need to change password here
        System.out.println("Password saved successfully");
    }
    
}
