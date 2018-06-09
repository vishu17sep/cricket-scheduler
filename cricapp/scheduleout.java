package cricapp;

import cricin.connection;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;

public class scheduleout extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bk;
    int i,j,k=1;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;

    scheduleout()
    {
            bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame("SCHEDULE");
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            schedule();
    }


     public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(bk))
         {
             Cricapp c=new Cricapp();
             j1.dispose();
         }
     }

    void schedule() {
        j1.add(bk);
        bk.addActionListener(this);
        JButton l[][]= new JButton[4][2];
        String a="";
        int n=15, m = 10,p=0;
       try {
                    stmt=c.con.prepareCall("select count(*) from schedule ");
            rs=stmt.executeQuery();
            while(rs.next())
            p=rs.getInt("count(*)");
        for (i = 0; i < 4&&k<=p; i++)
        {
            n=15;
            for(j=0;j<2&&k<=p;j++,k++)
        {
                    stmt = c.con.prepareCall("Select * from schedule where SR=?");
                    stmt.setInt(1, k);
                    l[i][j] = new JButton("NO SCHEDULE");
                    l[i][j].setBounds(n, m, 330, 150);
                    j1.add(l[i][j]);

                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        a = "<html>" + rs.getString(6) + '\t' + rs.getString(7) + "<br>" + rs.getString(9) + "<html>";
                        a = "<html>" + rs.getString(8) + "<br>" + a + "<html>";
                        a = "<html>" + rs.getString(4) + '\t' + "vs" + '\t' + rs.getString(5) + "<br>" + a + "<html>";
                    l[i][j].setText(a);
                    n+=345;
                }
                }
            m+=165;
        }
            }
            catch (Exception ee) {
                    System.out.println(ee);
        }
    }

}