package UI;

import java.util.Scanner;
import Controller.HDBManagerController;

/**
 * The {@code HDB_Manager_UI} class provides the user interface for HDB Managers in the system.
 * It allows HDB Managers to perform administrative operations related to BTO projects, such as
 * creation, editing, deletion, and visibility settings. Additionally, it provides access to 
 * enquiry handling, approval processes, and project reports.
 *
 * <p>Key features include:
 * <ul>
 *     <li>Creating and managing BTO projects</li>
 *     <li>Setting project visibility</li>
 *     <li>Generating project reports</li>
 *     <li>Viewing and replying to enquiries</li>
 *     <li>Approving registrations, applications, and withdrawals</li>
 *     <li>Logging out of the system</li>
 * </ul>
 */
public class HDB_Manager_UI {
    
    /**
     * Displays the HDB Manager menu and processes user selections.
     * Operations are handled via an instance of {@link HDBManagerController}.
     * Loops until the manager chooses to log out.
     */
    public static void display() {
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
            System.out.println("8. View all enquiries");
            System.out.println("9. Reply enquiries");
            System.out.println("10. Approve registration");
            System.out.println("11. Approve application");
            System.out.println("12. Approve withdrawal");
            System.out.println("13. Change password");
            System.out.println("14. Approve flatbooking");
            System.out.println("15. Logout and quit");
            System.out.print("Select option: ");
            
            choice = sc.nextInt();

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
                    controller.generateReport();
                    break;
                case 8:
                    controller.viewAllEnquiries();
                    break;
                case 9:
                    controller.replyEnquiry();
                    break;
                case 10:
                    controller.approveRegistration();
                    break;
                case 11:
                    controller.approveApplication();
                    break;
                case 12:
                    controller.approveWithdrawal();
                    break;
                case 13:
                    controller.changePassword();
                    break;
                case 14:
                    controller.approveFlatBooking();
                    break;
                case 15:
                    System.out.println("Logging out...");
                    System.out.println("Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}