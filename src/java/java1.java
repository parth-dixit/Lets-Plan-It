import java.sql.*;

public class java1 {
    public static void main(String[] args)
    {
        String jdbc_driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:derby://localhost:1527/Let_Plan";
        String user="Parth";
        String pass="parth";
 
        try
        {
            
            Connection con = DriverManager.getConnection(url,user,pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}
