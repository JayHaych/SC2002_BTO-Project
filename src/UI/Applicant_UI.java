package UI;

import java.util.Scanner;
import Controller.ApplicantController;
import Entity.LocalData;
import Entity.User;
import Interface.ViewEnquiryInterface;

/**
 * The {@code Applicant_UI} class provides the user interface for Applicants in the HDB Application System.
 * Applicants can perform various actions such as submitting and managing enquiries, applying for BTO projects,
 * booking flats, and handling account operations.
 *
 * <p>Features include:
 * <ul>
 *     <li>Submitting, viewing, editing, and deleting enquiries</li>
 *     <li>Applying for and viewing BTO projects</li>
 *     <li>Requesting withdrawals and creating flat bookings</li>
 *     <li>Changing password and logging out</li>
 * </ul>
 */
public class Applicant_UI {

    /**
     * Displays the menu of available options for the applicant.
     * The menu includes operations ranging from enquiry management to application and account actions.
     */
    public static void displayMenu() {
        System.out.println(LocalData.getCurrentUser().getName() + ", welcome to the Applicant UI!");
        System.out.println("-----------------------------");
        System.out.println("What would you like to do? (Select a number 1-11)");
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
        System.out.println("11. Logout and quit");
    }

    /**
     * Runs the applicant user interface loop, allowing applicants to interact with the system.
     * Processes user input and performs actions based on the selected option.
     * Loops until the user chooses to logout and quit.
     */
    public static void display() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 11) {
            displayMenu();  // Display menu options

            System.out.print("Enter your choice: ");

            // Validate user input to ensure it's an integer
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 11.");
                sc.nextLine();  // Clear invalid input
                continue;
            }

            User currentUser = LocalData.getCurrentUser();  // Retrieve current user context

            switch (choice) {
                case 1:
                    ApplicantController.submitEnquiry(currentUser);
                    break;
                case 2:
                    ViewEnquiryInterface viewInterface = new ApplicantController();
                    viewInterface.viewEnquiry(currentUser);
                    break;
                case 3:
                    ApplicantController.editEnquiry(currentUser);
                    break;
                case 4:
                    ApplicantController.deleteEnquiry(currentUser);
                    break;
                case 5:
                    ApplicantController.apply();
                    break;
                case 6:
                    ApplicantController.viewProjects();
                    break;
                case 7:
                    ApplicantController.viewAppliedProject();
                    break;
                case 8:
                    ApplicantController.requestWithdrawal();
                    break;
                case 9:
                    ApplicantController.createFlatBooking();
                    break;
                case 10:
                    ApplicantController.changePassword();
                    break;
                case 11:
                    ApplicantController.quit();  // Handles logout process
                    System.out.println("You have successfully logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 11.");
                    break;
            }
        }

        sc.close();
    }
}
