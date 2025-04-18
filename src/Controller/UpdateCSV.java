package Controller;

import Entity.LocalData;

public class UpdateCSV 
{
    public static void updateCSV() 
    {
        //Update the CSV files with the current data in LocalData, after the app ends
        CSVWriter.writeApplicantCSV("Data/ApplicantList.csv", LocalData.getApplicantList());
        CSVWriter.writeHDBManagerCSV("Data/ManagerList.csv", LocalData.getHDBManagerList());
        CSVWriter.writeBTOProjectCSV("Data/ProjectList.csv", LocalData.getBTOProjectList());
        CSVWriter.writeEnquiryCSV("Data/EnquiryList.csv", LocalData.getEnquiryList());
        CSVWriter.writeFlatBookingCSV("Data/FlatBookingList.csv", LocalData.getFlatBookingList());
        CSVWriter.writeHDBOfficerCSV("Data/OfficerList.csv", LocalData.getHDBOfficerList());
        CSVWriter.writeBTOApplicationCSV("Data/BTOApplicationList.csv", LocalData.getBTOApplicationList());
        CSVWriter.writeRegistrationCSV("Data/RegistrationList.csv", LocalData.getRegistrationList());
    }
}
