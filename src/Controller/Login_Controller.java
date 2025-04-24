package Controller;

import java.util.Scanner;
import Entity.LocalData;

/**
 * The Login_Controller class is responsible for handling the login process of users
 * within the system. It allows users to log in as one of three roles: Applicant, 
 * HDB Officer, or HDB Manager. The login process involves verifying the user's NRIC,
 * password, and role, and matching it against the user data stored in the system.
 * 
 * <p>Role-based authentication is performed, where each role (Applicant, HDB Officer, 
 * HDB Manager) has its own set of valid users. Upon successful login, the current user 
 * is set in the LocalData class, making them accessible throughout the application.
 * </p>
 * 
 * <p>The login process ensures input validation, such as checking for valid NRIC format 
 * and correct password entries, and it provides feedback to the user on the outcome 
 * of their login attempt.</p>
 * 
 * <p>This class contains the following methods:</p>
 * <ul>
 *   <li>{@link #login()} - Handles the user login process for all roles and authenticates 
 *   based on the entered credentials (NRIC, password).</li>
 * </ul>
 * 
 * @see Entity.LocalData
 */

public class Login_Controller 
{

    private static String NRIC;
    private static String password;
    private static int role;

    /**
     * Prompts the user to log in by entering their role, NRIC, and password. The method 
     * checks the validity of the entered credentials and authenticates the user based on 
     * their role. If the login is successful, the current user is set and the method 
     * returns the role of the user.
     * 
     * @return the role of the logged-in user (1 for Applicant, 2 for HDB Officer, 
     *         3 for HDB Manager).
     */

     
    public static int login()
    {

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("Login page");
        System.out.println("___________________________________________________________________________________________");
        while (!validInput)
        {
            try
            {
                System.out.println("Please enter the role you're signing in as:  \n1. Applicant \n2. HDB Officer \n3. HDB Manager");
                System.out.print("Role: ");
                role = scanner.nextInt();
                scanner.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("___________________________________________________________________________________________");
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the invalid input
                System.out.println();
                continue;
            }

            if (role != 1 && role != 2 && role != 3)
            {
                System.out.println("___________________________________________________________________________________________");
                System.out.println("Invalid input. Please try again.");
                System.out.println();
                continue;
            }
            System.out.println("Please enter your NRIC and password.");
            System.out.print("NRIC ( format: T1234567R ): ");
            NRIC = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();

            
            if (! NRIC.matches("[a-zA-Z][0-9]{7}[a-zA-Z]"))
            {
                System.out.println("___________________________________________________________________________________________");
                System.out.println("Invalid input. Format must be: letter + 7 digits + letter");
                System.out.println();
                continue;
            }



            System.out.println("___________________________________________________________________________________________");
            switch(role)
            {
                case 1:
                    for (int i = 0; i <= LocalData.getApplicantList().getCount(); i++)
                    {
                        if (i == LocalData.getApplicantList().getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (LocalData.getApplicantList().getApplicant(i).getNRIC().equals(NRIC) && 
                            LocalData.getApplicantList().getApplicant(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, Applicant!");
                            LocalData.setCurrentUser(LocalData.getApplicantList().getApplicant(i));
                            validInput = true;
                            break;
                        }
                        else if (LocalData.getApplicantList().getApplicant(i).getNRIC().equals(NRIC) && 
                                !LocalData.getApplicantList().getApplicant(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }
                    }
                    continue;
                case 2:
                    for (int i = 0; i <= LocalData.getHDBOfficerList().getCount(); i++)
                    {
                        if (i == LocalData.getHDBOfficerList().getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (LocalData.getHDBOfficerList().getHDBOfficer(i).getNRIC().equals(NRIC) && 
                            LocalData.getHDBOfficerList().getHDBOfficer(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, HDB Officer!");
                            LocalData.setCurrentUser(LocalData.getHDBOfficerList().getHDBOfficer(i));
                            validInput = true;
                            break;
                        }
                        else if (LocalData.getHDBOfficerList().getHDBOfficer(i).getNRIC().equals(NRIC) && 
                                !LocalData.getHDBOfficerList().getHDBOfficer(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }
                    }
                    continue;
                case 3:
                    for (int i = 0; i <= LocalData.getHDBManagerList().getCount(); i++)
                    {
                        if(i == LocalData.getHDBManagerList().getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (LocalData.getHDBManagerList().getHDBManager(i).getNRIC().equals(NRIC) && 
                            LocalData.getHDBManagerList().getHDBManager(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, HDB Manager!");
                            LocalData.setCurrentUser(LocalData.getHDBManagerList().getHDBManager(i));
                            validInput = true;
                            break;
                        }
                        else if (LocalData.getHDBManagerList().getHDBManager(i).getNRIC().equals(NRIC) && 
                                !LocalData.getHDBManagerList().getHDBManager(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }

                    }
                    continue;
            }
        }


        return role;
    }

}


