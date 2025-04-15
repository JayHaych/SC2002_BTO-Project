package Entity;

public class User
{
    private String name;
    private String NRIC;
    private String password;
    private boolean visibility;
    private String marital_status;
    private int age;

    public User(String name, String NRIC, String password, boolean visibility, String marital_status, int age)
    {
        this.name = name;
        this.NRIC = NRIC;
        this.password = password;
        this.visibility = visibility;
        this.marital_status = marital_status;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getNRIC()
    {
        return NRIC;
    }
    public void setNRIC(String NRIC)
    {
        this.NRIC = NRIC;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public boolean isVisible()
    {
        return visibility;
    }
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
    }
    public boolean getVisibility()
    {
        return visibility;
    }
    public String getMaritalStatus()
    {
        return marital_status;
    }
    public void setMaritalStatus(String marital_status)
    {
        this.marital_status = marital_status;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
}

