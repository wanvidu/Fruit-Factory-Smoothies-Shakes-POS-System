package DatabaseLayer;

import java.sql.*;

public class DatabaseConnection
{

    private final String URL = "jdbc:mysql://localhost:3306/fruit_factory_smoothies_&_shakes_pos_system";

    private Connection conn;
    
    public Statement st;
    
    public ResultSet rs;

    private static DatabaseConnection instance;

    private DatabaseConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,"db","123456");
        } catch (SQLException e)
        {
            System.out.println("Cannot Connect");
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Class not Found");
        }
    }

    public boolean insert(String query)
    {
        try
        {
            st = conn.createStatement();

            int result = st.executeUpdate(query);

            boolean status = (result > 0);

            return status;

        } catch (SQLException e)
        {
            System.out.println("Not Connected");
            return false;
        }
    }
    
    public void get(String query)
    {
        try
        {
            st = conn.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e)
        {
            System.out.println("Not Connected");
        }
    }
    
    public boolean deleteRow(String query)
    {
        try
        {
            st = conn.createStatement();
            
           int result = st.executeUpdate(query);

            boolean status = (result > 0);

            return status;

        } catch (SQLException e)
        {
            System.out.println("Not Connected");
            return false;
        }
    }

    public static DatabaseConnection getSingleConnection()
    {
        try
        {
            if (instance == null)
            {
                instance = new DatabaseConnection();
                return instance;
            }else if (instance.conn.isClosed())
            {
                instance = new DatabaseConnection();
                return instance;
            } else
            {
                return instance;
            }
        } catch (SQLException e)
        {
            System.out.println("Somthing went wrong with Server.");
            return null;
        }
    }
}
