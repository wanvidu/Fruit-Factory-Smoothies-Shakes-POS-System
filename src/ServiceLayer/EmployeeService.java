package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Model.Employee;

public class EmployeeService
{
    private final DatabaseConnection con;

    public EmployeeService() {
        con = DatabaseConnection.getSingleConnection();
    }
/*
    public boolean InsertNewUnderGraduate(Employee emp) {
        try {
            String query = "INSERT INTO IT17266574_StudentJava "
                    + "VALUES ('" + emp.Name + "'," + emp.NICNumber + ",'UnderGraduate'," + emp.ITNumber + ")";

            boolean result = con.insert(query);

            return result;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }
*/
}
