package UI;

import java.util.Scanner;
import Controller.ApplicantController;
import Entity.LocalData;
import Entity.User;

public class Applicant_UI {

    // Method to display the menu options
    public static void displayMenu() {
        System.out.println(LocalData.getCurrentUser().getName() + ", welcome to the Applicant UI!");
        System.out.println("-----------------------------");
        System.out.println("What would you like to do? (Select a number 1-10)");
        System.out.println("1. Submit an enquiry");
        System.out.println("2. View your enquiries");
        System.out.println("3. Edit an enquiry");
        System.out.println("4. Delete an enquiry");
        System.out.println("5. Apply for a BTO");
        System.out.println("6. View a BTO");
        System.out.println("7. View your applied BTO");
        System.out.println("8. Request a withdrawal from your application");
        System.out.println("9. Change your password");
        System.out.println("10. Logout and quit");
    }

    // Method to run the UI and interact with the user
    public static void display() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        // Continue showing the menu until the user chooses to quit (option 10)
        while (choice != 10) {
            displayMenu();  // Display the menu options

            System.out.print("Enter your choice: ");
            
            // Ensure the input is an integer and handle the case where user provides invalid input
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                // Handle non-integer input
                System.out.println("Invalid input! Please enter a number between 1 and 10.");
                sc.nextLine();  // Consume the invalid input (this prevents infinite loops)
                continue; // Skip the rest of the loop and ask for input again
            }

            // Consume the leftover newline character after reading the integer
            //sc.nextLine();

            User currentUser = LocalData.getCurrentUser();  // Get the current user

            switch (choice) {
                case 1:
                    ApplicantController.submitEnquiry(currentUser);
                    break;
                case 2:
                    ApplicantController.viewEnquiry(currentUser);
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
                    ApplicantController.changePassword();
                    break;
                case 10:
                    ApplicantController.quit();  // Assuming quit will handle logout
                    System.out.println("You have successfully logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 10.");
                    break;
            }
        }

        sc.close();
    }
}
