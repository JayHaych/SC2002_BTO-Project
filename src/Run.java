import Controller.UpdateCSV;
import UI.*;
import Entity.LocalData;

/**
 * The {@code Run} class serves as the entry point for the application.
 * It is responsible for:
 * <ul>
 *     <li>Loading local data from persistent storage</li>
 *     <li>Authenticating the user and determining their role</li>
 *     <li>Routing the user to the appropriate UI interface based on their role:
 *         <ul>
 *             <li>1 - Applicant</li>
 *             <li>2 - HDB Officer</li>
 *             <li>3 - HDB Manager</li>
 *         </ul>
 *     </li>
 *     <li>Saving updated data back to CSV files</li>
 * </ul>
 */
public class Run {
    
    /**
     * The main method is the starting point of the application.
     * It performs the following operations:
     * <ul>
     *     <li>Loads local data from CSV files</li>
     *     <li>Prompts the user to log in and identifies their role</li>
     *     <li>Displays the appropriate user interface based on the role</li>
     *     <li>Updates the CSV files with any changes made during the session</li>
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