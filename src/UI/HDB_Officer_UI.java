package UI;

import java.util.Scanner;
import Controller.HDBOfficerController;
import Entity.LocalData;

/**
 * The {@code HDB_Officer_UI} class provides the user interface for HDB Officers within the application.
 * It displays a menu of available actions and handles user input to perform tasks through the {@link HDBOfficerController}.
 * 
 * <p>Features include:
 * <ul>
 *     <li>Submitting and managing enquiries</li>
 *     <li>Viewing and applying for BTO projects</li>
 *     <li>Managing flat bookings</li>
 *     <li>Handling officer-specific project registrations</li>
 *     <li>Responding to applicant enquiries</li>
 *     <li>Generating receipts</li>
 *     <li>Logging out</li>
 * </ul>
 */
public class HDB_Officer_UI {

    /**
     * Displays the main menu for HDB Officers and handles input for various operations.
     * Continues to prompt the officer until they choose to logout (option 16).
     * Delegates functional logic to the {@code HDBOfficerController} and uses
     * the currently logged-in user from {@code LocalData}.
     */
    public static void display() {

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the HDB Officer UI!");
        System.out.println("-----------------------------");
        System.out.println("1. Submit an enquiry");
        System.out.println("2. View your enquiries");
        System.out.println("3. Edit an enquiry");
        System.out.println("4. Delete an enquiry");
        System.out.println("5. Apply for a BTO");
        System.out.println("6. View a BTO");
        System.out.println("7. View your applied BTO");
        System.out.println("8. Request a withdrawal from your application");
        System.out.println("9. Create a flat booking");
        System.out.println("10. Change your password");
        System.out.println("11. Register to join a project");
        System.out.println("12. View your registration status");
        System.out.println("13. View the enquiries of the project you are handling");
        System.out.println("14. Reply to enquiries");
        System.out.println("15. Generate receipt");
        System.out.println("16. Process flat bookings");
        System.out.println("17. Logout and quit");

        while (true) {
            System.out.println("What would you like to do? (Select a number 1-17)");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    HDBOfficerController.submitEnquiry(LocalData.getCurrentUser());
                    break;
                case 2:
                    HDBOfficerController.viewEnquiriesOfProject(LocalData.getCurrentUser());
                    break;
                case 3:
                    HDBOfficerController.editEnquiry(LocalData.getCurrentUser());
                    break;
                case 4:
                    HDBOfficerController.deleteEnquiry(LocalData.getCurrentUser());
                    break;
                case 5:
                    HDBOfficerController.apply();
                    break;
                case 6:
                    HDBOfficerController.viewProjects();
                    break;
                case 7:
                    HDBOfficerController.viewAppliedProject();
                    break;
                case 8:
                    HDBOfficerController.requestWithdrawal();
                    break;
                case 9:
                    HDBOfficerController.changePassword();
                    break;
                case 10:
                    HDBOfficerController.createFlatBooking();
                    break;
                case 11:
                    HDBOfficerController.registerForProjectAsOfficer();
                    break;
                case 12:
                    HDBOfficerController.viewRegistrationStatus(LocalData.getCurrentUser());
                    break;
                case 13:
                    HDBOfficerController.viewEnquiriesOfProject(LocalData.getCurrentUser());
                    break;
                case 14:
                    HDBOfficerController.replyToEnquiry(LocalData.getCurrentUser());
                    break;
                case 15:
                    HDBOfficerController.getReceipt(LocalData.getCurrentUser());
                    break;
                case 16:
                    HDBOfficerController.updateBTOProject();
                    break;
                case 17:
                    System.out.println("Logging out...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 16.");
                    break;
            }
        }
    }
}

