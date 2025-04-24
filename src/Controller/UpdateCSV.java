package Controller;

import Entity.LocalData;

public class UpdateCSV 
{
    /**
     * Updates the CSV files with the current data from the LocalData object.
     * This method writes the current state of various lists such as applicants, 
     * BTO projects, flat bookings, and more, to their respective CSV files.
     * If an error occurs during the writing process, an error message will 
     * be printed to the standard error stream.
     *
     * <p>This method calls various write methods from the CSVWriter class, 
     * each of which writes a specific list to a corresponding CSV file:
     * <ul>
     *     <li>writeApplicantCSV for "Data/ApplicantList.csv"</li>
     *     <li>writeHDBManagerCSV for "Data/ManagerList.csv"</li>
     *     <li>writeBTOProjectCSV for "Data/ProjectList.csv"</li>
     *     <li>writeEnquiryCSV for "Data/EnquiryList.csv"</li>
     *     <li>writeFlatBookingCSV for "Data/FlatBookingList.csv"</li>
     *     <li>writeHDBOfficerCSV for "Data/OfficerList.csv"</li>
     *     <li>writeBTOApplicationCSV for "Data/BTOApplicationList.csv"</li>
     *     <li>writeRegistrationCSV for "Data/RegistrationList.csv"</li>
     * </ul>
     * 
     * <p>If any exception occurs during this process, it is caught and the 
     * stack trace is printed for debugging.
     */
    public static void updateCSV() {
        try {
            CSVWriter.writeApplicantCSV("Data/ApplicantList.csv", LocalData.getApplicantList());
            CSVWriter.writeHDBManagerCSV("Data/ManagerList.csv", LocalData.getHDBManagerList());
            CSVWriter.writeBTOProjectCSV("Data/ProjectList.csv", LocalData.getBTOProjectList());
            CSVWriter.writeEnquiryCSV("Data/EnquiryList.csv", LocalData.getEnquiryList());
            CSVWriter.writeFlatBookingCSV("Data/FlatBookingList.csv", LocalData.getFlatBookingList());
            CSVWriter.writeHDBOfficerCSV("Data/OfficerList.csv", LocalData.getHDBOfficerList());
            CSVWriter.writeBTOApplicationCSV("Data/BTOApplicationList.csv", LocalData.getBTOApplicationList());
            CSVWriter.writeRegistrationCSV("Data/RegistrationList.csv", LocalData.getRegistrationList());
        } catch (Exception e) {
            System.err.println("An error occurred while updating CSV files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
