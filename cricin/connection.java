package cricin;
import java.sql.*;

public class connection {
  static public Connection con;
  public void connect()
  {
       try {
         Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "");
       }

       catch(Exception ee){System.out.println(ee);}
}

}

