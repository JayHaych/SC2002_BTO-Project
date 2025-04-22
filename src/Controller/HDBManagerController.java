package Controller;

import java.nio.file.SecureDirectoryStream;
import java.util.Scanner;

import Entity.BTOProject_List;
import Entity.Enquiry_List;
import Entity.FlatBooking;
import Entity.FlatBooking_List;
import Entity.HDBManager;
import Entity.HDBManager_List;
import Entity.HDBOfficer_List;
import Entity.BTOApplication_List;
import Entity.BTOProject;
import Entity.User;
import Entity.LocalData;
import Entity.Registration_List;

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
        
        if (manager.getCurrentProject() != null) {
            System.out.println("You already have an ongoing project: " + manager.getCurrentProject().getProjectName());
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
                    registrationList.getRegistration(i).setApprovalStatus("Approved");
                    System.out.println("Registration Approved!");
                    for (int j = 0; j < officerList.getCount(); j++){
                        if (officerList.getHDBOfficer(j).getName() == registrationList.getRegistration(i).getOfficerName()){
                            officerList.getHDBOfficer(j).setBTOprojectName(registrationList.getRegistration(i).getProjectName());
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

    public boolean approveApplication(){
        applicationList = LocalData.getBTOApplicationList();
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < applicationList.getCount(); i++){
            System.out.println("All applications: ");
            System.out.println((i+1) + ". " + applicationList.getBTOApplication(i).getApplicantName());
            
            String applicationStatus = applicationList.getBTOApplication(i).getApplicationStatus();
            if (applicationStatus.equals("Pending")){
                System.out.println("\nApplication " + (i + 1) + ":");
                System.out.println("Applicant Name: " + applicationList.getBTOApplication(i).getApplicantName());
                System.out.println("Project: " + applicationList.getBTOApplication(i).getProjectName());
                System.out.println("Flat Type: " + applicationList.getBTOApplication(i).getFlatType());
                System.out.println("Status: " + applicationList.getBTOApplication(i).getApplicationStatus());
                System.out.println("Approve Application?");
                System.out.println("1.YES");
                System.out.println("2.NO");
                int choice = sc.nextInt();
        
                if (choice == 1){
                    applicationList.getBTOApplication(i).setApplicationStatus("Approved");
                    System.out.println("Application Approved!");
                    return applicationApproved;
                } else if(choice == 2){
                    applicationList.getBTOApplication(i).setApplicationStatus("Rejected");
                    System.out.println("Application Rejected.");
                    applicationApproved = false;
                    return applicationApproved;
                }
            }
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

        for(int i = 0; i < projectList.getCount(); i++){
            BTOProject project = projectList.getBTOProject(i);

            for (FlatBooking flatBooking : project.getFlatBookings()){
                User applicant = flatBooking.getApplicant();
                String applicantName = applicant.getName();
                int age = applicant.getAge();
                String maritalStatus = applicant.getMaritalStatus();
                String flatType = flatBooking.getFlatType();
                String projectName = flatBooking.getProjectName();
            
                
                boolean filter = false;

                switch(choice){
                    case 1:
                        System.out.println("Filter for Married Applicants:");
                        if(maritalStatus.toLowerCase().equals("married")){
                            filter = true;
                        }
                        break;
                    
                    case 2:
                        System.out.println("Filter for Single Applicants:");
                        if(maritalStatus.toLowerCase().equals("single")){
                            filter = true;
                        }
                        break;

                    case 3:
                        System.out.println("Filter for Two-Room Flats:");
                        if(flatType.toLowerCase().equals("2-room")){
                            filter = true;
                        }
                        break;

                    case 4:
                        System.out.println("Filter for Three-Room Flats:");
                        if(flatType.toLowerCase().equals("3-room")){
                            filter = true;
                        }
                        break;

                    case 5:
                        System.out.println("All applicants:");
                        filter = true;
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                    }

                    if (filter = true){
                        System.out.println("Applicant: " + applicantName);
                        System.out.println("Age: " + age);
                        System.out.println("Marital Status: " + maritalStatus);
                        System.out.println("Flat Type: " + flatType);
                        System.out.println("Project Name: " + projectName);
                    }
                
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
}
