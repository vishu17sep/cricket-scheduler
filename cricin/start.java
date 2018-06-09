package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class start extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, btm, bpt, bpp, brec,bbk,bstrt;
    JComboBox c1,c2,c3;
    int i,brd;
    String status[]={"DUE","LIVE","PLAYED"},TM[]=new String[3],stts,typ,team1,team2;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;

    start(int n)
    {
        brd=n;
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            JLabel st,tmf,tmb;
            allot();
        st=new JLabel("STATUS");
        st.setBounds(150,200,100,60);
        j1.add(st);
        tmb=new JLabel("TEAM BATTING");
        tmb.setBounds(150,300,100,60);
        j1.add(tmb);
        tmf=new JLabel("TEAM FIELDING");
        tmf.setBounds(150,400,100,60);
        j1.add(tmf);
        c1=new JComboBox(status);
        c1.setBounds(260,200,150,60);
        j1.add(c1);
        String s[]={team1,team2};
        c2=new JComboBox(s);
        c2.setBounds(260,300,150,60);
        j1.add(c2);
        TM[1]=String.valueOf(c2.getSelectedItem());
        c3=new JComboBox(s);
        c3.setBounds(260,400,150,60);
        j1.add(c3);
        if(String.valueOf(c2.getSelectedItem())==team1)
        c3.setSelectedItem(team2);
        else
        c3.setSelectedItem(team1);
        bstrt=new JButton("START");
        bstrt.setBounds(300,500,100,60);
        j1.add(bstrt);
        bstrt.addActionListener(this);
            display();
         }


     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(bh))
        {
            cricinput c=new cricinput();
            j1.dispose();
        }
        if (e.getSource().equals(bsd)) {
            schedule s=new schedule();
            j1.dispose();
        }
        if (e.getSource().equals(btm)) {
            teams t=new teams();
            j1.dispose();
        }

        if (e.getSource().equals(bpp)) {
            playerprofile p=new playerprofile();
            j1.dispose();
        }

        if (e.getSource().equals(brec)) {
            league l=new league("");
            j1.dispose();
        }

        if(e.getSource().equals(bstrt))
        {
            start();
            j1.dispose();
        }

        if (e.getSource().equals(bbk)) {
            cricinput c=new cricinput();
            j1.dispose();
        }
     }


     void display()
     {
         bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("RECORDS");
            brec.setBounds(380, 600, 310, 50);
            bbk = new JButton("BACK");
            bbk.setBounds(10, 650, 690, 50);
        j1.add(bh);
        bh.addActionListener(this);
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(btm);
        btm.addActionListener(this);
        j1.add(bpp);
        bpp.addActionListener(this);
        j1.add(brec);
        brec.addActionListener(this);
        j1.add(bbk);
        bbk.addActionListener(this);
    }


     void allot()
     {
         try {
            stmt = c.con.prepareCall("Select * from schedule where SR=?");
            stmt.setInt(1, brd);
            rs = stmt.executeQuery();
            while (rs.next()) {
                stts=rs.getString(2);
                team1=rs.getString(4);
                team2=rs.getString(5);
                typ=rs.getString(9);
            }

        } catch (Exception ee) {
            System.out.println(ee);
        }
     }

      void start()
    {
            stts=String.valueOf(c1.getSelectedItem());
            TM[1]=String.valueOf(c2.getSelectedItem());
            TM[2]=String.valueOf(c3.getSelectedItem());
            try{
             stmt=c.con.prepareCall("update schedule set STATUS=?,SR=? where SR=?");
             stmt.setString(1,stts);
             stmt.setInt(2, brd);
             stmt.setInt(3, brd);
             stmt.executeUpdate();
             stmt=c.con.prepareCall("delete from score"+brd);
            stmt.executeUpdate();
            for(i=1;i<=2;i++)
            {
            stmt=c.con.prepareCall("insert into "+"score"+brd+" values(?,?,?,?,?,?,?)");
            stmt.setInt(1,i);
            stmt.setString(2, TM[i]);
             stmt.setInt(3,0);
             stmt.setInt(4,0);
             stmt.setFloat(5,0);
             stmt.setFloat(6,0);
             stmt.setString(7,typ);
             stmt.executeUpdate();
            }

            stmt=c.con.prepareCall("delete from "+"scorecardbat"+brd);
            stmt.executeUpdate();
             stmt=c.con.prepareCall("delete from "+"scorecardball"+brd);
             stmt.executeUpdate();
          }catch(Exception ex){ System.out.println(ex);}
            scoreboard s=new scoreboard(brd);

    }

}
