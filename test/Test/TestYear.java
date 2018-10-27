package Test;

import Model.Employee;

import org.junit.*;

public class TestYear
{
    static Employee employee;
    
    int year;
    
    @BeforeClass
    public static void beforeClass()
    {
        employee=new Employee(25, "Nimal", 21, "Colombo");
    }
    
    @Before
    public void before()
    {
        year=employee.getBornYear();
    }
    
    @Test
    public void test()
    {
        Assert.assertEquals(year, 1997, 0);
    }
    
    @After
    public void after()
    {
        year=0;
    }
    
    @AfterClass
    public static void afterClass()
    {
        employee=null;
    }
}
