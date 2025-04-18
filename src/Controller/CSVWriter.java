package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Entity.*;

public class CSVWriter 
{
    public static void writeApplicantCSV(String filePath, Applicant_list applicantList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {

            bw.write("Name,NRIC,Age,visibility,MaritalStatus,Password,hasApplied");
            bw.newLine();
            

                for (int i = 0 ; i < applicantList.getCount() ; i++) 
                {
                    Applicant applicant = (Applicant) applicantList.getApplicant(i);
                    String line = String.format("%s,%s,%d,%b,%s,%s,%s",
                    applicant.getName(),
                    applicant.getNRIC(),
                    applicant.getAge(),
                    applicant.getVisibility(),
                    applicant.getMaritalStatus(),
                    applicant.getPassword(),
                    applicant.getAppliedProject());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }





    
    public static void writeHDBManagerCSV(String filePath, HDBManager_List hdbManagerList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("Name,NRIC,Age,visibility,MaritalStatus,Password,currentProject");
            bw.newLine();
            

                for (int i = 0 ; i < hdbManagerList.getCount() ; i++) 
                {
                    HDBManager hdbManager = (HDBManager) hdbManagerList.getHDBManager(i);
                    String line = String.format("%s,%s,%d,%b,%s,%s,%s",
                    hdbManager.getName(),
                    hdbManager.getNRIC(),
                    hdbManager.getAge(),
                    hdbManager.getVisibility(),
                    hdbManager.getMaritalStatus(),
                    hdbManager.getPassword(),
                    hdbManager.getBTOprojectname());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }







    public static void writeHDBOfficerCSV(String filePath, HDBOfficer_List hdbOfficerList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("Name,NRIC,Age,visibility,MaritalStatus,Password,BTOprojectName");
            bw.newLine();
            

                for (int i = 0 ; i < hdbOfficerList.getCount() ; i++) 
                {
                    HDBOfficer hdbOfficer = (HDBOfficer) hdbOfficerList.getHDBOfficer(i);
                    String line = String.format("%s,%s,%d,%b,%s,%s,%s",
                    hdbOfficer.getName(),
                    hdbOfficer.getNRIC(),
                    hdbOfficer.getAge(),
                    hdbOfficer.getVisibility(),
                    hdbOfficer.getMaritalStatus(),
                    hdbOfficer.getPassword(),
                    hdbOfficer.getBTOprojectName());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void writeBTOProjectCSV(String filePath, BTOProject_List btoProjectList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("Project Name,Neighborhood,Type 1,Number of units for Type 1,Selling price for Type 1,Type 2,Number of units for Type 2,Selling price for Type 2,Application opening date,Application closing date,Manager,Officer Slot,Officer,Visibility");
            bw.newLine();
            
            for (int i = 0 ; i < btoProjectList.getCount() ; i++) 
                {
                    BTOProject btoProject = (BTOProject) btoProjectList.getBTOProject(i);
                    String officersincharge = String.join(",", btoProject.getOfficerInChargeNames());

                    String line = String.format("%s,%s,2-Room,%d,%d,3-Room,%d,%d,%s,%s,%s,%d,\"%s\",%b",
                    btoProject.getProjectName(),
                    btoProject.getNeighbourhood(),
                    btoProject.getNumberOfTwoRoom(),
                    btoProject.getSellingPriceOfTwoRoom(),
                    btoProject.getNumberOfThreeRoom(),
                    btoProject.getSellingPriceOfThreeRoom(),
                    btoProject.getOpeningDate(),
                    btoProject.getClosingDate(),
                    btoProject.getHDBManagerInChargeName(),
                    btoProject.getAvailableOfficerSlot(),
                    officersincharge,
                    btoProject.isVisible());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }





    public static void writeEnquiryCSV(String filePath, Enquiry_List enquiryList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("Details,ProjectName,UserName");
            bw.newLine();
            

                for (int i = 0 ; i < enquiryList.getEnquiryCount() ; i++) 
                {
                    Enquiry enquiry = (Enquiry) enquiryList.getEnquiry(i);
                    String line = String.format("%s,%s,%s",
                    enquiry.getDetails(),
                    enquiry.getProjectName(),
                    enquiry.getUserName());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }



    public static void writeFlatBookingCSV(String filePath, FlatBooking_List flatBookingList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("bookingID,projectName,flatType,unitNumber,bookingStatus,ApplicantName");
            bw.newLine();
            

                for (int i = 0 ; i < flatBookingList.getCount() ; i++) 
                {
                    FlatBooking flatBooking = (FlatBooking) flatBookingList.getFlatBooking(i);
                    String line = String.format("%s,%s,%s,%s,%s,%s",
                    flatBooking.getBookingID(),
                    flatBooking.getProjectName(),
                    flatBooking.getFlatType(),
                    flatBooking.getUnitNumber(),
                    flatBooking.getBookingStatus(),
                    flatBooking.getApplicantName());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


    public static void writeBTOApplicationCSV(String filePath, BTOApplication_List btoApplicationList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("projectName,flatType,applicationStatus,submissionDate,withdrawalRequested,applicantName,withdrawalApproved");
            bw.newLine();
            

                for (int i = 0 ; i < btoApplicationList.getCount() ; i++) 
                {
                    BTOApplication btoApplication = (BTOApplication) btoApplicationList.getBTOApplication(i);
                    String line = String.format("%s,%s,%s,%s,%b,%s,%b",
                    //btoApplication.getApplicationID(),
                    btoApplication.getProjectName(),
                    btoApplication.getFlatType(),
                    btoApplication.getApplicationStatus(),
                    btoApplication.getSubmissionDate(),
                    btoApplication.getWithdrawalRequested(),
                    btoApplication.getApplicantName(),
                    btoApplication.getWithdrawalApproved());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }





    public static void writeRegistrationCSV(String filePath, Registration_List registrationList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            bw.write("ProjectName,OfficerName,ApprovalStatus");
            bw.newLine();
            

                for (int i = 0 ; i < registrationList.getRegistrationCount() ; i++) 
                {
                    Registration registration = (Registration) registrationList.getRegistration(i);
                    String line = String.format("%s,%s,%s",
                    registration.getProjectName(),
                    registration.getOfficerName(),
                    registration.getApprovalStatus());
                    bw.write(line);
                    bw.newLine();
                }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


}