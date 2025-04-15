package Entity;

import java.util.ArrayList;

public class HDBManager_List 
{
    private ArrayList<HDBManager> HDBManagerList;
    private int count;

    public ArrayList<HDBManager> getList() 
    {
        return HDBManagerList;
    }

    public HDBManager_List() 
    {
        this.HDBManagerList = new ArrayList<>();
        this.count = 0;
    }

    public void addHDBManager(HDBManager hdbManager) 
    {
        HDBManagerList.add(hdbManager);
        count++;
    }

    public void removeHDBManager(HDBManager hdbManager) 
    {
        HDBManagerList.remove(hdbManager);
        count--;
    }

    public HDBManager getHDBManager(int index) 
    {
        if (index >= 0 && index < count) {
            return HDBManagerList.get(index);
        } else {
            return null;
        }
    }

    public int getCount() {
        return count;
    }
}
