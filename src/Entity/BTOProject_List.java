package Entity;

import java.util.ArrayList;

public class BTOProject_List 
{
    private ArrayList<BTOProject> BTOProjectList;
    private int count; 


    public ArrayList<BTOProject> getList() 
    {
        return BTOProjectList; 
    }


    public BTOProject_List() 
    {
        BTOProjectList = new ArrayList<BTOProject>();
        count = 0; 
    }

    public void addBTOProject(BTOProject btoProject) 
    {
        BTOProjectList.add(btoProject);
        count++; 
    }

    public void removeBTOProject(BTOProject btoProject) 
    {
        BTOProjectList.remove(btoProject);
        count--; 
    }

    public BTOProject getBTOProject(int i) 
    {
        if (i >= 0 && i < count) 
        {
            return BTOProjectList.get(i);
        } else 
        {
            return null; // or throw an exception
        }
    }

    public int getCount() 
    {
        return count; 
    }

    public BTOProject findProjectByName(String name) {
        for (BTOProject project : BTOProjectList) {
            if (project.getProjectName().equalsIgnoreCase(name.trim())) {
                return project;
            }
        }
        return null;
    }


}
