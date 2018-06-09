package cricin;

import java.sql.*;


public class check_records {
    PreparedStatement stmt,stm;
    connection c;
    ResultSet rs;
    countries cntry=new countries();
    int i;

    check_records()
    {//System.out.println("Reached here");
        String countries="";
        for(i=1;i<cntry.n;i++)
            countries+="Select * from "+cntry.teams[i]+" union ";
        String countries1=countries+"Select * from "+cntry.teams[i]+" order by RUNS desc";
        String countries2=countries+"Select * from "+cntry.teams[i]+" order by WICKETS desc";
        try{
            i=1;
             stmt=c.con.prepareCall(countries1);
             rs=stmt.executeQuery();
                   while(rs.next()&&i<11)
                   {
                       stm=c.con.prepareCall("update recordbat set PLAYER=?,TEAMS=?,TESTBAT=?,ODIBAT=?,T20BAT=?,RUNS=?,FOURS=?,SIX=?,CENTURY=?,FIFTY=? where SR=?");
                       stm.setString(1, rs.getString(2));
                       stm.setString(2, rs.getString(3));
                       stm.setInt(3, rs.getInt(4));
                       stm.setInt(4, rs.getInt(5));
                       stm.setInt(5, rs.getInt(6));
                       stm.setInt(6, rs.getInt(7));
                       stm.setInt(7, rs.getInt(8));
                       stm.setInt(8, rs.getInt(9));
                       stm.setInt(9, rs.getInt(10));
                       stm.setInt(10, rs.getInt(11));
                       stm.setInt(11,i);
                       stm.executeUpdate();
                       i++;}

                   i=1;
                       stmt=c.con.prepareCall(countries2);
                       rs=stmt.executeQuery();
                       while(rs.next()&&i<11)
                   {
                       stm=c.con.prepareCall("update recordball set PLAYER=?,TEAMS=?,OVERS=?,WICKETS=?,TESTBALL=?,ODIBALL=?,T20BALL=? where SR=?");
                       stm.setString(1, rs.getString(2));
                       stm.setString(2, rs.getString(3));
                       stm.setInt(3, rs.getInt(12));
                       stm.setInt(4, rs.getInt(13));
                       stm.setInt(5, rs.getInt(14));
                       stm.setInt(6, rs.getInt(15));
                       stm.setInt(7, rs.getInt(16));
                       stm.setInt(8, i);
                       stm.executeUpdate();
                       i++;
                   }
        }
        catch(Exception ee){System.out.println(ee);}
    }
}
