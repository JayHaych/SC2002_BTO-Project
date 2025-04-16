package Controller;

import java.util.Scanner;
import Entity.LocalData;


public class Login_Controller 
{

    private static String NRIC;
    private static String password;
    private static int role;

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
            System.out.print("NRIC: ");
            NRIC = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
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


