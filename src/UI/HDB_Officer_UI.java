package UI;

import Entity.LocalData;

public class HDB_Officer_UI 
{


    public static void display() 
    {
        System.out.println(LocalData.getCurrentUser().getName() + "Welcome to the HDB Officer UI!");
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
        System.out.println("11. Register to join a project");
        System.out.println("12. View your registration status");
        System.out.println("13. View the enquiries of the project you are handling");
        System.out.println("14. Reply to enquiries");
        System.out.println("15. Manage applications");
        System.out.println("16. Generate receipt");

    }
}

/*         Scanner sc = new Scanner(System.in);
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
        } */