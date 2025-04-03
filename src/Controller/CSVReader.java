package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Entity.User;
import Entity.User_list;

public class CSVReader {
    public static User_list readCSV(String filePath) {
        
        User_list userList = new User_list();

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
                

                if (values.length < 5) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = values[0];
                String NRIC = values[1];


                int age;
                try {
                    age = Integer.parseInt(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid age format for line: " + line);
                    continue; 
                }

                String maritalStatus = values[3];
                String password = values[4];
                boolean visibility = true;


                User user = new User(name, NRIC, password, visibility, maritalStatus, age);
                userList.addUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
