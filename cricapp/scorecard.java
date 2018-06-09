package cricapp;

import cricin.connection;
import cricin.pointtable;
import cricin.scoreboard;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class scorecard extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, brs, bpt, bpp, brec,bk;
    int i,j,brd;
    String team;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;

    scorecard(String s,int n)
    {
        team=s;
        brd=n;
         bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            scorecard();
         }


     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(bh)) {
            Cricapp c=new Cricapp();
            j1.dispose();
        }

        if (e.getSource().equals(bsd)) {
            scheduleout s=new scheduleout();
            j1.dispose();
        }

        if (e.getSource().equals(brs)) {
            resultout r=new resultout();
            j1.dispose();
        }

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j1.dispose();
           }

         if(e.getSource().equals(bk)) {
             scoreboard s=new scoreboard(brd);
             j1.dispose();
         }

         if (e.getSource().equals(bpp)) {
             search s=new search();
             j1.dispose();
        }
        if (e.getSource().equals(brec)) {
            record r=new record();
            j1.dispose();
        }
     }


     void display()
     {
         bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            brs = new JButton("RESULT");
            brs.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("RECORDS");
            brec.setBounds(380, 600, 310, 50);
            bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);

        j1.add(bh);
        bh.addActionListener(this);
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(brs);
        brs.addActionListener(this);
        j1.add(bpp);
        bpp.addActionListener(this);
        j1.add(brec);
        brec.addActionListener(this);
        j1.add(bk);
        bk.addActionListener(this);
    }

void scorecard()
{
        remove(bpp);
        remove(brec);
        JLabel lpb[][] = new JLabel[20][10], lpf[][] = new JLabel[20][10],l1[]=new JLabel[20];
        i = 0;
        int n=0;
        String s[]={"PLAYER","RUNS","BALLS","FOURS","SIXES","SR","STATUS"},s1[]={"PLAYER","RUNS","OVERS","WICKETS","ECO","STATUS"};
        l1[0] = new JLabel("BATTING");
                    l1[0].setBounds(250,75,80,50);
                    j1.add(l1[0]);
        for(j=0;j<7;j++)
            {
                    l1[j] = new JLabel(s[j]);
                    l1[j].setBounds(50+(90*j),100,80,50);
                    j1.add(l1[j]);
            }
        l1[7] = new JLabel("BOWLING");
                    l1[7].setBounds(250,425,80,50);
                    j1.add(l1[7]);
                    for(j=0;j<6;j++)
            {
                    l1[j] = new JLabel(s1[j]);
                    l1[j].setBounds(50+(90*j),450,80,50);
                    j1.add(l1[j]);
            }
        try {

            stmt = c.con.prepareCall("Select * from " + "scorecardbat" + brd + " where TEAMS=? ");
            stmt.setString(1, team);
            rs = stmt.executeQuery();
            while (rs.next()){
            for(j=0;j<7;j++)
            {
                    lpb[i][j] = new JLabel();
                    lpb[i][j].setBounds(50+(90*j),125+(25*i),80,50);
                    j1.add(lpb[i][j]);
            }
                    lpb[i][0].setText(rs.getString(3));
                    lpb[i][1].setText(String.valueOf(rs.getInt(4)));
                    lpb[i][2].setText(String.valueOf(rs.getInt(5)));
                    lpb[i][3].setText(String.valueOf(rs.getInt(6)));
                    lpb[i][4].setText(String.valueOf(rs.getInt(7)));
                    lpb[i][5].setText(String.valueOf(rs.getFloat(8)));
                    lpb[i][6].setText(rs.getString(9));
                    i++;
            }
            i=0;
            stmt=c.con.prepareCall("select count(*) from scorecardball"+brd+" where TEAMS=?");
            stmt.setString(1,team);
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
            stmt = c.con.prepareCall("Select * from scorecardball" + brd + " where TEAMS=?");
            stmt.setString(1, team);
            rs = stmt.executeQuery();
            while (rs.next()){
            for(j=0;j<6;j++)
            {
                    lpf[i][j] = new JLabel();
                    lpf[i][j].setBounds(50+(90*j),475+(25*i),80,50);
                    j1.add(lpf[i][j]);
            }
                    lpf[i][0].setText(rs.getString(3));
                    lpf[i][1].setText(String.valueOf(rs.getInt(4)));
                    lpf[i][2].setText(String.valueOf(rs.getFloat(5)));
                    lpf[i][3].setText(String.valueOf(rs.getInt(6)));
                    lpf[i][4].setText(String.valueOf(rs.getFloat(7)));
                    lpf[i][5].setText(rs.getString(8));
                    i++;
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }
}