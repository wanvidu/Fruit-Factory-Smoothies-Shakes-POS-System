package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee
{
    int empID;
    String name;
    int age;
    String address;

    public Employee(int empID, String name, int age, String address)
    {
        this.empID = empID;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getEmpID()
    {
        return empID;
    }

    public void setEmpID(int empID)
    {
        this.empID = empID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public int getBornYear()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String year = dtf.format(now);
        
        int currentYear=Integer.parseInt(year);
        
        return currentYear-age;
    }
}
