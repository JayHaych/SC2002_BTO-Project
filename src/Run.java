import Controller.UpdateCSV;
import UI.*;
import Entity.LocalData;

/**
 * The {@code Run} class serves as the entry point for the application.
 * It loads local data, manages user login, and routes the user to 
 * the appropriate UI interface based on their role (Applicant, HDB Officer, or HDB Manager).
 * After user interaction, it updates the CSV data files accordingly.
 */
public class Run 
{

    /**
     * The main method is the starting point of the application.
     * It handles:
     * <ul>
     *     <li>Loading local data from persistent storage</li>
     *     <li>User login and role identification</li>
     *     <li>Displaying the UI based on the logged-in user's role</li>
     *     <li>Saving any changes back to CSV files</li>
     * </ul>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) 
    {
        LocalData.LocalDataLoad();

        int logged_in_user = Login_UI.login();
        
        switch(logged_in_user)
        {
            case 1:
                Applicant_UI.display();
                break;
            case 2:
                HDB_Officer_UI.display();
                break;
            case 3:
                HDB_Manager_UI.display();
                break;
        }

        UpdateCSV.updateCSV();
    }

}