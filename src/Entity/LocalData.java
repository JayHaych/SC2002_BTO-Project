package Entity;

import Controller.CSVReader;

/**
 * The {@code LocalData} class acts as a centralized in-memory storage for all
 * major lists and entities used in the HDB Application System.
 * <p>
 * It loads data from CSV files at the start of the application and provides static
 * access to these data structures throughout the runtime.
 * <p>
 * The class also manages the currently logged-in user.
 */
public class LocalData 
{
    private static BTOApplication_List btoApplicationList;
    private static FlatBooking_List flatBookingList;
    private static Enquiry_List enquiryList;
    private static Applicant_list applicantList;
    private static HDBOfficer_List hdbOfficerList;
    private static HDBManager_List hdbManagerList;
    private static BTOProject_List btoProjectList;
    private static Registration_List registrationList;
    private static User currentUser;

    /**
     * Loads all necessary data from CSV files into memory.
     * This should be called once at the beginning of the application.
     */
    public static void LocalDataLoad()
    {
        applicantList = CSVReader.readApplicantCSV("Data/ApplicantList.csv");
        hdbManagerList = CSVReader.readHDBManagerCSV("Data/ManagerList.csv");
        hdbOfficerList = CSVReader.readHDBOfficerCSV("Data/OfficerList.csv");
        btoProjectList = CSVReader.readBTOProjectCSV("Data/ProjectList.csv");
        enquiryList = CSVReader.readEnquiryCSV("Data/EnquiryList.csv");
        flatBookingList = CSVReader.readFlatBookingCSV("Data/FlatBookingList.csv");
        btoApplicationList = CSVReader.readBTOApplicationCSV("Data/BTOApplicationList.csv");
        registrationList = CSVReader.readRegistrationCSV("Data/RegistrationList.csv");
        currentUser = null;
    }

    /**
     * @return the list of all BTO applications
     */
    public static BTOApplication_List getBTOApplicationList() 
    {
        return btoApplicationList;
    }

    /**
     * @return the list of all flat bookings
     */
    public static FlatBooking_List getFlatBookingList() 
    {
        return flatBookingList;
    }

    /**
     * @return the list of all enquiries
     */
    public static Enquiry_List getEnquiryList() 
    {
        return enquiryList;
    }

    /**
     * @return the list of all applicants
     */
    public static Applicant_list getApplicantList() 
    {
        return applicantList;
    }

    /**
     * @return the list of all HDB managers
     */
    public static HDBManager_List getHDBManagerList() 
    {
        return hdbManagerList;
    }

    /**
     * @return the list of all HDB officers
     */
    public static HDBOfficer_List getHDBOfficerList() 
    {
        return hdbOfficerList;
    }

    /**
     * @return the list of all BTO projects
     */
    public static BTOProject_List getBTOProjectList() 
    {
        return btoProjectList;
    }

    /**
     * @return the list of all project registrations
     */
    public static Registration_List getRegistrationList() 
    {
        return registrationList;
    }

    /**
     * @return the currently logged-in user, or {@code null} if no user is logged in
     */
    public static User getCurrentUser() 
    {
        return currentUser;
    }

    /**
     * Sets the current logged-in user.
     *
     * @param currentUser The user to set as currently logged in
     */
    public static void setCurrentUser(User currentUser) 
    {
        LocalData.currentUser = currentUser;
    }
}