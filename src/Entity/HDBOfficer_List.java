package Entity;

import java.util.ArrayList;

public class HDBOfficer_List 
{
    private ArrayList<HDBOfficer> HDBOfficerList;
    private int count;

    public ArrayList<HDBOfficer> getList() 
    {
        return HDBOfficerList;
    }

    public HDBOfficer_List() 
    {
        this.HDBOfficerList = new ArrayList<>();
        this.count = 0;
    }

    public void addHDBOfficer(HDBOfficer HDBOfficer) 
    {
        HDBOfficerList.add(HDBOfficer);
        count++;
    }

    public void removeHDBOfficer(HDBOfficer HDBOfficer) 
    {
        HDBOfficerList.remove(HDBOfficer);
        count--;
    }

    public HDBOfficer getHDBOfficer(int index) 
    {
        if (index >= 0 && index < count) {
            return HDBOfficerList.get(index);
        } else {
            return null;
        }
    }

    public int getCount() {
        return count;
    }
}
