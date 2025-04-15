package Entity;

import java.util.ArrayList;

public class FlatBooking_List 
{
    public ArrayList<FlatBooking> flatBookingList;
    private int count = 0;

    public FlatBooking_List() 
    {
        flatBookingList = new ArrayList<FlatBooking>();
    }

    public void addFlatBooking(FlatBooking flatBooking) 
    {
        flatBookingList.add(flatBooking);
        count++;
    }

    public void removeFlatBooking(FlatBooking flatBooking) 
    {
        flatBookingList.remove(flatBooking);
        count--;
    }

    public FlatBooking getFlatBooking(int index) 
    {
        return flatBookingList.get(index);
    }

    public int getCount() 
    {
        return count;
    }

    public ArrayList<FlatBooking> getList() 
    {
        return flatBookingList;
    }


}
