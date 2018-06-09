package cricapp;

import cricin.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Cricapp extends javax.swing.JFrame implements ActionListener {

    JButton bh, bsd, brs, bpt, bpp, brec,bsc[] = new JButton[3];
    int i;
    String stts[]=new String[3];
    PreparedStatement stmt;
    static connection c=new connection();
    ResultSet rs;

   public Cricapp() {
            bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            brs = new JButton("RESULT");
            brs.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            bsc[1] = new JButton("SCORE");
            bsc[1].setBounds(30, 100, 300, 150);
            bsc[2] = new JButton("SCORE");
            bsc[2].setBounds(380, 100, 300, 150);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("RECORDS");
            brec.setBounds(380, 600, 310, 50);
            stts[1]="";
            stts[2]="";
            home();
            setLayout(null);
            setBounds(300, 10, 715, 950);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bh)) {
            cricinput c=new cricinput();
            this.dispose();
        }

        if (e.getSource().equals(bsd)) {
            scheduleout s=new scheduleout();
            this.dispose();
        }

        if (e.getSource().equals(brs)) {
            resultout r=new resultout();
            this.dispose();
        }

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            this.dispose();
        }
        if (e.getSource().equals(bsc[1])) {
            if(stts[1].equals("LIVE")||stts[1].equals("PLAYED"))
            {
            scoreboard s=new scoreboard(1);
            this.dispose();}
        }
        if (e.getSource().equals(bsc[2])) {
            if(stts[2].equals("LIVE")||stts[2].equals("PLAYED"))
            {
            scoreboard s=new scoreboard(2);
            this.dispose();}
        }
        if (e.getSource().equals(bpp)) {
           search s=new search();
             this.dispose();
        }
        if (e.getSource().equals(brec)) {
            record r=new record();
            this.dispose();
        }
    }

    void display() {


        add(bh);
        bh.addActionListener(this);
        add(bpt);
        bpt.addActionListener(this);
        add(bsd);
        bsd.addActionListener(this);
        add(brs);
        brs.addActionListener(this);
        add(bpp);
        bpp.addActionListener(this);
        add(brec);
        brec.addActionListener(this);
        add(bsc[1]);
        bsc[1].addActionListener(this);
        add(bsc[2]);
        bsc[2].addActionListener(this);
    }

    void home() {
        display();
        String a="";
        for (i = 1; i <= 2; i++) {
            try {
                stmt = c.con.prepareCall("Select * from schedule where SR=?");
                stmt.setInt(1, i);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    stts[i]=rs.getString(2);
                     a = "<html>" + rs.getString(6) + '\t' + rs.getString(7) +","+ " " + rs.getString(8)+ "<br>" + rs.getString(9) + "<html>";
                        a = "<html>" + rs.getString(4) + '\t' + "vs" + '\t' + rs.getString(5) + "<br>" + a + "<html>";
                        a="<html>"+ stts[i]+"<br>"+a+"<html>";
            bsc[i].setText(a);
                }
            } catch (Exception ee) {
                System.out.println(ee);
            }
        }

    }

    public static void main(String[] args) {
        c.connect();
        Cricapp a = new Cricapp();
    }
}