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

        BTOProject project = projectList.getBTOProject(projectNumber - 1);
        project.setVisible(!project.isVisible());
        System.out.println("Project Visibility: " + project.isVisible());

    }

    public void viewAllProjects(){
        for (int i = 0; i < projectList.getCount(); i++){
            if (projectList.getCount() > 0){
                System.out.println("---------ALL PROJECTS---------");
                BTOProject btoProject = projectList.getBTOProject(i);
                System.out.println((i + 1) + ". " + btoProject.getProjectName());
            } else{
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Approve Registration?");
        System.out.println("1.YES");
        System.out.println("2.NO");
        int choice = sc.nextInt();

        if (choice == 1){
            return registrationApproved;
        } else if(choice == 2){
            registrationApproved = false;
            return registrationApproved;
        } else{
            System.out.println("Invalid Option.");
            return false;
        }
    }

    public boolean approveApplication(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Approve Application?");
        System.out.println("1.YES");
        System.out.println("2.NO");
        int choice = sc.nextInt();

        if (choice == 1){
            return applicationApproved;
        } else if(choice == 2){
            applicationApproved = false;
            return applicationApproved;
        } else{
            System.out.println("Invalid Option.");
            return false;
        }
    }

    public boolean approveWithdrawal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Approve Withdrawal?");
        System.out.println("1.YES");
        System.out.println("2.NO");
        int choice = sc.nextInt();

        if (choice == 1){
            return withdrawalApproved;
        } else if(choice == 2){
            withdrawalApproved = false;
            return withdrawalApproved;
        } else{
            System.out.println("Invalid Option.");
            return false;
        }
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
}
