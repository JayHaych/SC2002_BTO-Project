package Entity;

import Controller.CSVReader;

public class LocalData 
{
    private static Applicant_list applicantList = CSVReader.readApplicantCSV("Data/ApplicantList.csv");
    private static HDBManager_List hdbManagerList = CSVReader.readHDBManagerCSV("Data/ManagerList.csv");
    private static HDBOfficer_List hdbOfficerList = CSVReader.readHDBOfficerCSV("Data/OfficerList.csv");
    private static BTOProject_List btoProjectList = CSVReader.readBTOProjectCSV("Data/ProjectList.csv");
    private static Enquiry_List enquiryList = CSVReader.readEnquiryCSV("Data/EnquiryList.csv");
    private static FlatBooking_List flatBookingList = CSVReader.readFlatBookingCSV("Data/FlatBookingList.csv");
    private static BTOApplication_List btoApplicationList = CSVReader.readBTOApplicationCSV("Data/BTOApplicationList.csv");
    private static User currentUser = null;



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
