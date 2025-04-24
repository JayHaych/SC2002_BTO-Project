package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Entity.*;

/**
 * The {@code CSVReader} class provides static methods for loading
 * data from CSV files into their corresponding entity list objects.
 * <p>
 * Each method is responsible for reading a specific CSV file format
 * and mapping the data into appropriate Java objects.
 */
public class CSVReader {

    /**
     * Reads applicant data from the given CSV file path and returns an Applicant_list.
     *
     * @param filePath the path to the CSV file
     * @return a populated {@link Applicant_list}
     */
    public static Applicant_list readApplicantCSV(String filePath) {
        Applicant_list applicantList = new Applicant_list();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;

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

    /**
     * Reads manager data from the CSV and returns an HDBManager_List.
     */
    public static HDBManager_List readHDBManagerCSV(String filePath) {
        HDBManager_List hdbManagerList = new HDBManager_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                String name = values[0], NRIC = values[1];
                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue;
                }
                boolean visibility = Boolean.parseBoolean(values[3]);
                String maritalStatus = values[4], password = values[5], BTOprojectName = values[6];

                HDBManager hdbManager = new HDBManager(name, NRIC, password, visibility, maritalStatus, age, BTOprojectName);
                hdbManagerList.addHDBManager(hdbManager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hdbManagerList;
    }

    /**
     * Reads officer data from the CSV and returns an HDBOfficer_List.
     */
    public static HDBOfficer_List readHDBOfficerCSV(String filePath) {
        HDBOfficer_List hdbOfficerList = new HDBOfficer_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                String name = values[0], NRIC = values[1];
                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue;
                }
                boolean visibility = Boolean.parseBoolean(values[3]);
                String maritalStatus = values[4], password = values[5], BTOprojectName = values[6];
                HDBOfficer hdbOfficer = new HDBOfficer(name, NRIC, password, visibility, maritalStatus, age, null, BTOprojectName);
                hdbOfficerList.addHDBOfficer(hdbOfficer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hdbOfficerList;
    }

    /**
     * Reads BTO project data and returns a BTOProject_List.
     */
    public static BTOProject_List readBTOProjectCSV(String filePath) {
        BTOProject_List btoProjectList = new BTOProject_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] raw = line.split(",");
                String[] values = new String[14];
                System.arraycopy(raw, 0, values, 0, 12);

                StringBuilder officerBuilder = new StringBuilder();
                int i = 12;
                while (i < raw.length - 1) {
                    officerBuilder.append(raw[i]);
                    if (i < raw.length - 2) officerBuilder.append(",");
                    i++;
                }

                values[12] = officerBuilder.toString().replace("\"", "").trim();
                values[13] = raw[raw.length - 1].trim();

                try {
                    String projectName = values[0], neighbourhood = values[1];
                    int twoRoom = Integer.parseInt(values[3]), twoPrice = Integer.parseInt(values[4]);
                    int threeRoom = Integer.parseInt(values[6]), threePrice = Integer.parseInt(values[7]);
                    String open = values[8], close = values[9], manager = values[10];
                    int officerSlot = Integer.parseInt(values[11]);
                    String[] officerNames = values[12].split(",");
                    if (officerNames.length == 1 && officerNames[0].isEmpty()) officerNames = new String[0];
                    boolean visible = Boolean.parseBoolean(values[13]);

                    BTOProject project = new BTOProject(projectName, neighbourhood, twoRoom, twoPrice, threeRoom, threePrice, open, close, manager, officerSlot, officerNames, visible);
                    btoProjectList.addBTOProject(project);
                } catch (NumberFormatException e) {
                    System.err.println("Number parsing error in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return btoProjectList;
    }

    /**
     * Reads enquiry data and returns an Enquiry_List.
     */
    public static Enquiry_List readEnquiryCSV(String filePath) {
        Enquiry_List enquiryList = new Enquiry_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                Enquiry enquiry = new Enquiry(values[0], values[1], values[2], values[3]);
                enquiryList.addEnquiry(enquiry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enquiryList;
    }

    /**
     * Reads flat booking data and returns a FlatBooking_List.
     */
    public static FlatBooking_List readFlatBookingCSV(String filePath) {
        FlatBooking_List flatBookingList = new FlatBooking_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                FlatBooking booking = new FlatBooking(values[0], values[1], values[2], values[3], values[4]);
                flatBookingList.addFlatBooking(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flatBookingList;
    }

    /**
     * Reads BTO application data and returns a BTOApplication_List.
     */
    public static BTOApplication_List readBTOApplicationCSV(String filePath) {
        BTOApplication_List btoApplicationList = new BTOApplication_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                BTOApplication application = new BTOApplication(
                        values[0], values[1], values[2], values[3],
                        Boolean.parseBoolean(values[4]), values[5], Boolean.parseBoolean(values[6]));
                btoApplicationList.addBTOApplication(application);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return btoApplicationList;
    }

    /**
     * Reads officer registration data and returns a Registration_List.
     */
    public static Registration_List readRegistrationCSV(String filePath) {
        Registration_List registrationList = new Registration_List();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count++ == 0) continue;
                String[] values = line.split(",");
                Registration registration = new Registration(values[0], values[1], values[2]);
                registrationList.addRegistration(registration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrationList;
    }
}