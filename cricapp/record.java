package cricapp;

import cricin.connection;
import cricin.pointtable;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class record extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, brs, bpt, bpp, brec,bk;
    JComboBox catg;
    JPanel p;
    int i,j,n;
    String catog[] = {"BATTING", "BOWLING"},teams="";
    PreparedStatement stmt;
    connection c;
    ResultSet rs;

    public record()
    {
         j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            record();
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

         if(e.getSource().equals(bk))
         {
             Cricapp c=new Cricapp();
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

        if (e.getSource().equals(catg)) {

            String  s= String.valueOf(catg.getSelectedItem());
            j1.remove(p);
            j1.repaint();
            record();
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
        catg = new JComboBox(catog);
        catg.setBounds(170, 80, 350, 70);
        j1.add(catg);
        catg.addActionListener(this);

    }


     void record()
     {
          p=new JPanel();
        p.setBounds(10, 140, 695, 450);
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        p.setLayout(null);
        p.setVisible(true);
        j1.add(p);
        JLabel l[][] = new JLabel[16][16], ld[] = new JLabel[15];
        String s1[] = {"SR", "NAME", "TEAM", "TEST", "ODI", "T20", "RUNS", "FOURS", "SIX", "CENTURY", "FIFTY"}, s2[] = {"SR", "NAME", "TEAM", "OVERS","WICKETS" ,"TEST", "ODI", "T20", "OVERS"};

        if (String.valueOf(catg.getSelectedItem()).equals("BATTING")) {
            ld[0] = new JLabel(s1[0]);
            ld[0].setBounds(10, -10, 40, 70);
            p.add(ld[0]);
            for (i = 1; i < 3; i++) {
                ld[i] = new JLabel(s1[i]);
                ld[i].setBounds(55 + ((i - 1) * 110), -10, 100, 70);
                p.add(ld[i]);
            }
            for (i = 3; i < 11; i++) {
                ld[i] = new JLabel(s1[i]);
                ld[i].setBounds(260 + ((i - 3) * 55), -10, 50, 70);
                p.add(ld[i]);
            }
            i=0;
                try {
                        stmt = c.con.prepareCall("Select * from recordbat");
                        rs = stmt.executeQuery();
                        while (rs.next()&&i<10) {
                                l[i][1] = new JLabel(rs.getString(1));
                                l[i][1].setBounds(10, 30+(40*i), 40, 70);
                                p.add(l[i][1]);
                                for (j = 2; j < 4; j++) {
                                l[i][j] = new JLabel(rs.getString(j));
                                l[i][j].setBounds(55+((j - 2) * 110), 30+(40*i), 100, 70);
                                p.add(l[i][j]);
                             }
                            for (j = 4; j <= 11; j++)
                            {
                                l[i][j] = new JLabel(rs.getString(j));
                                l[i][j].setBounds(260 + ((j - 4) * 55), 30+(40*i), 50, 70);
                                p.add(l[i][j]);
                             }
                            i++;
                        }
                } catch (Exception ee) {
                    System.out.println(ee);
                }
            }
        if (String.valueOf(catg.getSelectedItem()).equals("BOWLING")) {
            ld[0] = new JLabel(s2[0]);
            ld[0].setBounds(10, -10, 40, 70);
            p.add(ld[0]);
            for (i = 1; i < 3; i++) {
                ld[i] = new JLabel(s2[i]);
                ld[i].setBounds(60 + ((i - 1) * 120), -10, 100, 70);
                p.add(ld[i]);
            }
            for (i = 3; i < 8; i++) {
                ld[i] = new JLabel(s2[i]);
                ld[i].setBounds(300 + ((i - 3) * 80), -10, 50, 70);
                p.add(ld[i]);
            }
            i=0;
                try {
                    stmt = c.con.prepareCall("Select * from recordball ");
                    rs = stmt.executeQuery();
                    while (rs.next()&&i<10) {
                                l[i][1] = new JLabel(rs.getString(1));
                                l[i][1].setBounds(10, 30+(40*i), 40, 70);
                                p.add(l[i][1]);
                                for (j = 2; j < 4; j++) {
                                l[i][j] = new JLabel(rs.getString(j));
                                l[i][j].setBounds(60+((j - 2) * 120), 30+(40*i), 100, 70);
                                p.add(l[i][j]);
                             }
                            for (j = 4; j <= 8; j++)
                            {
                                l[i][j] = new JLabel(rs.getString(j));
                                l[i][j].setBounds(300 + ((j - 4) * 80), 30+(40*i), 50, 70);
                                p.add(l[i][j]);
                             }
                            i++;
                        }
                } catch (Exception ee) { System.out.println(ee);}

        }
    }
}
