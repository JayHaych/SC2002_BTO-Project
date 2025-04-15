package Entity;

import java.util.ArrayList;

public class BTOApplication_List 
{
    private ArrayList<BTOApplication> btoApplicationList;
    private int count;

    public ArrayList<BTOApplication> getList() 
    {
        return btoApplicationList;
    }

    public BTOApplication_List()
    {
        btoApplicationList = new ArrayList<BTOApplication>();
        count = 0;
    }

    public void addBTOApplication(BTOApplication btoApplication)
    {
        btoApplicationList.add(btoApplication);
        count++;
    }

    public void removeBTOApplication(BTOApplication btoApplication)
    {
        btoApplicationList.remove(btoApplication);
        count--;
    }

    public BTOApplication getBTOApplication(int index)
    {
        if (index >= 0 && index < count) {
            return btoApplicationList.get(index);
        } else {
            return null;
        }
    }

    public int getCount()
    {
        return count;
    }
    

}
