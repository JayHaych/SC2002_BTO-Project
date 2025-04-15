package Controller;
import java.util.Scanner;

import Entity.BTOProject_List;
import Entity.FlatBooking;
import Entity.FlatBooking_List;
import Entity.BTOProject;
import Entity.User;

public class HDBManagerController{
    private boolean applicationApproved;
    private boolean registrationApproved;
    private boolean withdrawalApproved;
    private BTOProject_List projectList;
    private FlatBooking_List flatBookingList;
    
    public HDBManagerController(boolean applicationApproved, boolean registrationApproved, boolean withdrawalApproved){
        this.applicationApproved = applicationApproved;
        this.registrationApproved = registrationApproved;
        this.withdrawalApproved = withdrawalApproved;
        this.projectList = new BTOProject_List();
        this.flatBookingList = new FlatBooking_List();
    }

    public void createProject(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Project Name: ");
        String projectName = sc.nextLine();
        System.out.print("Neighbourhood: ");
        String neighbourhood = sc.nextLine();
        System.out.print("Number of 2-room flats: ");
        int numberOfTwoRoom = sc.nextInt();
        System.out.println("Price of 2-room flats: ");
        int sellingPriceOfTwoRoom = sc.nextInt();
        System.out.print("Number of 3-room flats: ");
        int numberOfThreeRoom = sc.nextInt();
        System.out.println("Price of 3-room flats: ");
        int sellingPriceOfThreeRoom = sc.nextInt();

        sc.nextLine();

        System.out.print("Opening Date: ");
        String openingDate = sc.nextLine();
        System.out.print("Closing Date: ");
        String closingDate = sc.nextLine();
        System.out.print("HDB Manager's Name: ");
        String managerName = sc.nextLine();
        System.out.print("Number of officer slots: ");
        int availableOfficerSlot = sc.nextInt();

        sc.nextLine();
        String[] OfficerInChargeNames = {}; 
        boolean isVisible = true;

        BTOProject project = new BTOProject(projectName, neighbourhood, numberOfTwoRoom, sellingPriceOfTwoRoom, numberOfThreeRoom, sellingPriceOfThreeRoom, openingDate, closingDate, managerName, availableOfficerSlot, OfficerInChargeNames, isVisible);
        projectList.addBTOProject(project);
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

        System.out.println("Edit project name (current: " + project.getProjectName() + ") :");
        project.setProjectName(sc.nextLine());
        System.out.println("Edit neighbourhood (current: )" + project.getNeighbourhood() + ") :");
        project.setNeighbourhood(sc.nextLine());
        System.out.println("Edit number of two room flats (current: " + project.getNumberOfTwoRoom() + ") :");
        project.setNumberOfTwoRoom(sc.nextInt());
        System.out.println("Edit the number of three room flats (current: " + project.getNumberOfThreeRoom() + ") :");
        project.setNumberOfThreeRoom(sc.nextInt());
        System.out.println("Edit opening date of project (current: " + project.getOpeningDate() + ") :");
        project.setOpeningDate(sc.nextLine());
        System.out.println("Edit closing date of the project (current: " + project.getClosingDate() + ") :");
        project.setClosingDate(sc.nextLine());
        System.out.println("Edit HDB Manager in-charge (current: " + project.getHDBManagerInCharge() + ") :");
        project.setHDBManagerInChargeName(sc.nextLine());
        System.out.println("Edit available officer slots (current: " + project.getAvailableOfficerSlot() + ") :");
        project.setAvailableOfficerSlot(sc.nextInt());
        System.out.println("BTO Project updated!");
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

        BTOProject project = projectList.getBTOProject(projectNumber - 1);
        project.setVisible(!project.isVisible());
        System.out.println("Project Visibility: " + project.isVisible());

    }

    public void viewAllProjects(){
        System.out.println("---------ALL PROJECTS---------");
        for (int i = 0; i < projectList.getCount(); i++){
            BTOProject btoProject = projectList.getBTOProject(i);
            System.out.println((i + 1) + ". " + btoProject.getProjectName());
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
            } else{
                System.out.println("No projects found under manager name " + managerName);
            }
        }

    }

    public boolean approveRegistration(){
        return registrationApproved;
    }

    public boolean approveApplication(){
        return applicationApproved;
    }

    public boolean approveWithdrawal(){
        return withdrawalApproved;
    }

    public String generateReport(){
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
        }

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
