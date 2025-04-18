package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Entity.*;

public class CSVReader 
{
    public static Applicant_list readApplicantCSV(String filePath) 
    {
        
        Applicant_list applicantList = new Applicant_list();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
        

                String name = values[0];
                String NRIC = values[1];


                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue; 
                }
                boolean visibility = Boolean.parseBoolean(values[3]);
                String maritalStatus = values[4];
                String password = values[5];
                String appliedProject = values[6];




                Applicant applicant = new Applicant(name, NRIC, password, visibility, maritalStatus, age, appliedProject);
                applicantList.addApplicant(applicant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return applicantList;
    }

    
    
    
    
    public static HDBManager_List readHDBManagerCSV(String filePath) 
    {
        HDBManager_List hdbManagerList = new HDBManager_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
                

                String name = values[0];
                String NRIC = values[1];


                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue; 
                }
                boolean visibility = Boolean.parseBoolean(values[3]);
                String maritalStatus = values[4];
                String password = values[5];



                HDBManager hdbManager = new HDBManager(name, NRIC, password, visibility, maritalStatus, age, null);
                hdbManagerList.addHDBManager(hdbManager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hdbManagerList;
    }



    public static HDBOfficer_List readHDBOfficerCSV(String filePath) {
        HDBOfficer_List hdbOfficerList = new HDBOfficer_List();
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;  // Skip the header row
                }
    
                String[] values = line.split(",");
    
    
                // Extract the values from the CSV line
                String name = values[0];
                String NRIC = values[1];
    
                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue;
                }
    
                boolean visibility = Boolean.parseBoolean(values[3]);
                String maritalStatus = values[4];
                String password = values[5];
                String BTOprojectName = values[6];  // BTO project name the officer is managing
    
                // Create a new HDBOfficer object and add it to the list
                HDBOfficer hdbOfficer = new HDBOfficer(name, NRIC, password, visibility, maritalStatus, age, null, BTOprojectName);
                hdbOfficerList.addHDBOfficer(hdbOfficer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return hdbOfficerList;
    }







    public static BTOProject_List readBTOProjectCSV(String filePath) {
        BTOProject_List btoProjectList = new BTOProject_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue; // Skip header

                // Basic split
                String[] raw = line.split(",");

                // Prepare clean values array
                String[] values = new String[14];
                System.arraycopy(raw, 0, values, 0, 12);

                // Join officer names (quoted field)
                StringBuilder officerBuilder = new StringBuilder();
                int i = 12;
                while (i < raw.length - 1) {
                    officerBuilder.append(raw[i]);
                    if (i < raw.length - 2) officerBuilder.append(",");
                    i++;
                }

                // Clean up quotes
                values[12] = officerBuilder.toString().replace("\"", "").trim();
                values[13] = raw[raw.length - 1].trim(); // Visibility

                try {
                    // Parse fields
                    String projectName = values[0];
                    String neighbourhood = values[1];
                    int numberOfTwoRoom = Integer.parseInt(values[3]);
                    int sellingPriceOfTwoRoom = Integer.parseInt(values[4]);
                    int numberOfThreeRoom = Integer.parseInt(values[6]);
                    int sellingPriceOfThreeRoom = Integer.parseInt(values[7]);
                    String openingDate = values[8];
                    String closingDate = values[9];
                    String manager = values[10];
                    int officerSlot = Integer.parseInt(values[11]);

                    // Split officer names
                    String[] OfficerInChargeNames = values[12].split(",");
                    if (OfficerInChargeNames.length == 1 && OfficerInChargeNames[0].trim().isEmpty()) {
                        OfficerInChargeNames = new String[0];
                    }

                    boolean isVisible = Boolean.parseBoolean(values[13]);

                    // Create and add BTOProject object
                    BTOProject btoProject = new BTOProject(
                            projectName, neighbourhood, numberOfTwoRoom, sellingPriceOfTwoRoom,
                            numberOfThreeRoom, sellingPriceOfThreeRoom, openingDate, closingDate,
                            manager, officerSlot, OfficerInChargeNames, isVisible
                    );

                    btoProjectList.addBTOProject(btoProject);

                } catch (NumberFormatException e) {
                    System.err.println("Number parsing error in line: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return btoProjectList;
    }




    


    public static Enquiry_List readEnquiryCSV(String filePath) 
    {
        Enquiry_List enquiryList = new Enquiry_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
                


                String message = values[0];
                String ProjectName = values[1];
                String ApplicantName = values[2];



                Enquiry enquiry = new Enquiry(message, ProjectName, ApplicantName);
                enquiryList.addEnquiry(enquiry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enquiryList;
    }





    
    public static FlatBooking_List readFlatBookingCSV(String filePath) 
    {
        FlatBooking_List flatBookingList = new FlatBooking_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
                


                String projectName = values[0];
                String applicantName = values[1];
                String flatType = values[2];
                String unitNumber = values[3];
                String bookingStatus  = values[4];
                String ApplicantName = values[5];



                FlatBooking flatBooking = new FlatBooking(projectName, applicantName, flatType, unitNumber, bookingStatus, ApplicantName);
                flatBookingList.addFlatBooking(flatBooking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flatBookingList;
    }





    public static BTOApplication_List readBTOApplicationCSV(String filePath) 
    {
        BTOApplication_List btoApplicationList = new BTOApplication_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
                



                String projectName = values[0];
                String flatType = values[1];
                String applicationStatus = values[2];
                String submissionDate  = values[3];
                boolean withdrawalRequested = Boolean.parseBoolean(values[4]);
                String applicantName = values[5];
                String withdrawalApproved = values[6];



                BTOApplication btoApplication = new BTOApplication(projectName, flatType, applicationStatus, submissionDate, withdrawalRequested, applicantName, Boolean.parseBoolean(withdrawalApproved));
                btoApplicationList.addBTOApplication(btoApplication);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return btoApplicationList;
    }





    public static Registration_List readRegistrationCSV(String filePath) 
    {
        Registration_List registrationList = new Registration_List();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) 
                {
                    count++;
                    continue; 
                }

                String[] values = line.split(",");
                


                String ProjectName = values[0];
                String OfficerName = values[1];
                String ApprovalStatus = values[2];



                Registration registration = new Registration(ProjectName, OfficerName, ApprovalStatus);
                registrationList.addRegistration(registration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registrationList;
    }

}