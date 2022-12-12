import java.sql.*;
import java.sql.DriverManager;

public class Jdbc
{
  Connection conn;
  PreparedStatement pst;
  ResultSet rs;
  
  public void runDB() throws Exception
  {
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/steam","root","");
    System.out.println("Connection established");
    
    this.pst = conn.prepareStatement("SELECT* FROM konto WHERE Benutzername = 'Kekw2.0' AND Passwort = '143312';");
    rs = pst.executeQuery();
    
    while (rs.next()) 
    { 
      System.out.println(rs.getString(2));
    } // end of while
    
    conn.close();
  }
  

  
  
}

