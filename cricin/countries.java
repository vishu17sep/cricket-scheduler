package cricin;
import java.sql.*;

public class countries {
    public int i,n=0;
    public String teams[];
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
     public countries()
     {
         try{
          stmt=c.con.prepareCall("select count(*) from teams");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
         teams=new String[n+1];
         teams[0]="SELECT TEAM";
         i=1;
             stmt=c.con.prepareCall("select * from teams");
             rs=stmt.executeQuery();
             while(rs.next())
             {
                 teams[i]=rs.getString(1);
                 i++;
             }
         }
         catch(Exception ee){System.out.println();}
     }

}
