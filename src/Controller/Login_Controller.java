package Controller;

import java.util.Scanner;
import Entity.User_list;
import Entity.User;

public class Login_Controller 
{
    static User_list list1 = CSVReader.readCSV("Data/ApplicantList.csv");
    static User_list list2 = CSVReader.readCSV("Data/OfficerList.csv");
    static User_list list3 = CSVReader.readCSV("Data/ManagerList.csv");

    private static String NRIC;
    private static String password;
    private static int role;
    private static User current_user;

    public static User getCurrentUser()
    {
        return current_user;
    }

    public static int login()
    {

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("Login page");
        System.out.println("___________________________________________________________________________________________");
        while (!validInput)
        {
            System.out.println("Please enter the role you're signing in as:  \n1. Applicant \n2. HDB Officer \n3. HDB Manager");
            System.out.print("Role: ");
            role = scanner.nextInt();
            scanner.nextLine();
            if (role != 1 && role != 2 && role != 3)
            {
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
                    for (int i = 0; i <= list1.getCount(); i++)
                    {
                        if (i == list1.getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (list1.getUser(i).getNRIC().equals(NRIC) && list1.getUser(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, Applicant!");
                            validInput = true;
                            current_user = list1.getUser(i);
                            break;
                        }
                        else if (list1.getUser(i).getNRIC().equals(NRIC) && !list1.getUser(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }
                    }
                    continue;
                case 2:
                    for (int i = 0; i <= list2.getCount(); i++)
                    {
                        if (i == list2.getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (list2.getUser(i).getNRIC().equals(NRIC) && list2.getUser(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, HDB Officer!");
                            validInput = true;
                            current_user = list2.getUser(i);
                            break;
                        }
                        else if (list2.getUser(i).getNRIC().equals(NRIC) && !list2.getUser(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }
                    }
                    continue;
                case 3:
                    for (int i = 0; i <= list3.getCount(); i++)
                    {
                        if(i == list3.getCount())
                        {
                            System.out.println("No such user found in the database.");
                            break;
                        }
                        if (list3.getUser(i).getNRIC().equals(NRIC) && list3.getUser(i).getPassword().equals(password))
                        {
                            System.out.println();
                            System.out.println("Login successful! Welcome, HDB Manager!");
                            validInput = true;
                            current_user = list3.getUser(i);
                            break;
                        }
                        else if (list3.getUser(i).getNRIC().equals(NRIC) && !list3.getUser(i).getPassword().equals(password))
                        {
                            System.out.println("Incorrect password. Please try again.");
                            break;
                        }

                    }
                    continue;
            }
        }

    
        scanner.close();

        return role;
    }


}


