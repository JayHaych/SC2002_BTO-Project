package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Entity.User;
import Entity.User_list;

public class CSVWriter 
{
    public static void writeCSV(String filePath, User_list userList) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {

            bw.write("Name,NRIC,Age,MaritalStatus,Password");
            bw.newLine();
            

                for (int i = 0 ; i < userList.getCount() ; i++) 
                {
                    User user = userList.getUser(i);
                    String line = String.format("%s,%s,%d,%s,%s",
                            user.getName(),
                            user.getNRIC(),
                            user.getAge(),
                            user.getMaritalStatus(),
                            user.getPassword());
                    bw.write(line);
                    bw.newLine();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
