package Entity;

import java.util.ArrayList;

public class Enquiry_List 
{
    private ArrayList <Enquiry> enquiryList;
    private int enquiryCount;

    public Enquiry_List() 
    {
        enquiryList = new ArrayList<Enquiry>();
        enquiryCount = 0;
    }

    public void addEnquiry(Enquiry enquiry) 
    {
        enquiryList.add(enquiry);
        enquiryCount++;
    }

    public void removeEnquiry(Enquiry enquiry) 
    {
        enquiryList.remove(enquiry);
        enquiryCount--;
    }

    public Enquiry getEnquiry(int index) 
    {
        return enquiryList.get(index);
    }

    public int getEnquiryCount() 
    {
        return enquiryCount;
    }

    public ArrayList<Enquiry> getList() 
    {
        return enquiryList;
    }

    
}
