package UI;

import java.util.Scanner;

import Controller.HDBManagerController;

public class HDB_Manager_UI {
    
    public static void display(){
        Scanner sc = new Scanner(System.in);
        int choice;

        HDBManagerController controller = new HDBManagerController(true, true, true);

        while (true) {
            System.out.println("___________________________________________________________________________________________");
            System.out.println("Welcome to the HDB Manager UI!");
            System.out.println("1. Create new BTO project");
            System.out.println("2. Edit existing BTO project");
            System.out.println("3. Delete BTO project");
            System.out.println("4. Set project visibility");
            System.out.println("5. View all projects");
            System.out.println("6. View my projects");
            System.out.println("7. Generate report");
            System.out.println("8. Approve registration");
            System.out.println("9. Approve application");
            System.out.println("10. Approve withdrawal");
            System.out.println("11. Logout and quit");
            System.out.print("Select option: ");
            
           
            choice = sc.nextInt();
            String report;

            switch (choice) {
                case 1:
                    controller.createProject();
                    break;
                case 2:
                    controller.editProject();
                    break;
                case 3:
                    controller.deleteProject();
                    break;
                case 4:
                    controller.setVisibility();
                    break;
                case 5:
                    controller.viewAllProjects();
                    break;
                case 6:
                    controller.viewMyProjects();
                    break;
                case 7:
                    report = controller.generateReport();
                    System.out.println(report);
                    break;
                case 8:
                    if (controller.approveRegistration()){
                        System.out.println("Registration approved!");
                    } else{
                        System.out.println("Registration rejected");
                    }
                    break;
                case 9:
                    if (controller.approveApplication()){
                        System.out.println("Application approved!");
                    } else {
                        System.out.println("Application rejected");
                    }
                    break;
                case 10:
                    if (controller.approveWithdrawal()){
                        System.out.println("Withdrawal approved!");
                    } else{
                        System.out.println("Withdrawal rejected");
                    }
                    break;
                case 11:
                    System.out.println("Logging out...");
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}