package Entity;

import java.util.ArrayList;

public class Registration_List 
{
    private ArrayList<Registration> registrationList;
    private int registrationCount;

    public Registration_List() 
    {
        this.registrationList = new ArrayList<>();
        this.registrationCount = 0;
    }

    public void addRegistration(Registration registration) 
    {
        this.registrationList.add(registration);
        this.registrationCount++;
    }

    public void removeRegistration(Registration registration) 
    {
        this.registrationList.remove(registration);
        this.registrationCount--;
    }

    public ArrayList<Registration> getList() 
    {
        return registrationList;
    }
    
    public Registration getRegistration(int index) 
    {
        return registrationList.get(index);
    }

    public int getRegistrationCount() 
    {
        return registrationCount;
    }

}
