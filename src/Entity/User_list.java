package Entity;

import java.util.ArrayList;

public class User_list 
{
    private ArrayList<User> user_list;
    private int count;

    public User_list()
    {
        user_list = new ArrayList<User>();
        count = 0;
    }

    public void addUser(User user)
    {
        user_list.add(user);
        count++;
    }

    public void removeUser(User user)
    {
        user_list.remove(user);
        count--;
    }

    public User getUser(int index)
    {
        if (index >= 0 && index < count) {
            return user_list.get(index);
        } else {
            return null;
        }
    }

    public int getCount()
    {
        return count;
    }
}
