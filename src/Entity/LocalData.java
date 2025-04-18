package Entity;

import Controller.CSVReader;

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

    public static Registration_List getRegistrationList() 
    {
        return registrationList;
    }


    public static BTOApplication_List getBTOApplicationList() 
    {
        return btoApplicationList;
    }

    
    public static FlatBooking_List getFlatBookingList() 
    {
        return flatBookingList;
    }
    
    public static Enquiry_List getEnquiryList() 
    {
        return enquiryList;
    }

    public static Applicant_list getApplicantList() 
    {
        return applicantList;
    }

    public static HDBManager_List getHDBManagerList() 
    {
        return hdbManagerList;
    }   

    public static HDBOfficer_List getHDBOfficerList() 
    {
        return hdbOfficerList;
    }
    public static User getCurrentUser() 
    {
        return currentUser;
    }
    public static void setCurrentUser(User currentUser) 
    {
        LocalData.currentUser = currentUser;
    }

    public static BTOProject_List getBTOProjectList() 
    {
        return btoProjectList;
    }
}
