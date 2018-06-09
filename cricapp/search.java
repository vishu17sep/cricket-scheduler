package cricapp;
import cricin.connection;
import cricin.countries;
import cricin.playerprofile;
import cricin.pointtable;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class search extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, brs, bpt, bpp, brec,bk,bsrch;
    JTextField t1;
    int i,j;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
    countries cn=new countries();

    search()
    {
         bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            t1 = new JTextField("ENTER NAME");
            t1.setBounds(255, 150, 250, 50);
            j1.add(t1);
            t1.setActionCommand("OK");
            t1.addActionListener(this);
            bsrch = new JButton("SEARCH");
            bsrch.setBounds(330, 250, 100, 50);
            j1. add(bsrch);
            bsrch.addActionListener(this);

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

        if (e.getSource().equals(bsrch)||e.getActionCommand().equals("OK")) {
            try {
                int count=0;
            for (j = 1; j <=cn.n; j++) {
                stmt = c.con.prepareCall("Select * from " + cn.teams[j] + " where PLAYER=?");
                stmt.setString(1,t1.getText());
                rs = stmt.executeQuery();
                if(rs.absolute(1))
                { count++;
                break;
                }
            }
            if(count==0)
            {
                JOptionPane.showMessageDialog(this,"PLAYER DOES NOT EXIST");
                return;
            }
            else{
            playerprofile p=new playerprofile();
            p.edit(t1.getText());
            j1.dispose();
            }}
            catch(Exception ee){System.out.println(ee);}
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
}
