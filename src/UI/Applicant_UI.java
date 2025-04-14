package UI;

import java.util.Scanner;

import Entity.CurrentUser;
import Controller.ApplicantController;

public class Applicant_UI {
    CurrentUser user;

    public static void display(CurrentUser user) {
        System.out.println("Welcome to the ApplicantUI!");
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

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                ApplicantController.submitEnquiry(user);
                break;
            case 2:
                ApplicantController.viewEnquiry(user);
                break;
            case 3:
                ApplicantController.editEnquiry(user);
                break;
            case 4:
                ApplicantController.deleteEnquiry(user);
                break;
            case 5:
                ApplicantController.apply(user);
                break;
            case 6:
                ApplicantController.viewProject(user);
                break;
            case 7:
                ApplicantController.viewAppliedProject(user);
                break;
            case 8:
                ApplicantController.requestWithdrawal(user);
                break;
            case 9:
                
                break;
            case 10:
                ApplicantController.quit();
                break;
            default:
                System.out.println("Invalid choice. Please select a number from 1 to 10.");
                break;
        }
    }
}
