package cricin;

import cricapp.record;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class pointtable extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JPanel p;
    JButton bh, bsd, btm, bpt, bpp, brec,bk,bw,bt,bl,bc;
    JComboBox cb;
     JLabel lpb[][] = new JLabel[20][10];
    JRadioButton rb[]=new JRadioButton[10];
    ButtonGroup bg=new ButtonGroup();
    int i,j,k,change=0,n;
    String league,team;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;

    public pointtable()
    {
          bk = new JButton("BACK");
            bk.setBounds(400, 650, 300, 50);
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            pointtable();
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

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
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
            
           record l=new record();
           j1.dispose();
        }

        if (e.getSource().equals(bk)) {
            cricinput c=new cricinput();
            j1.dispose();
        }

        if (e.getSource().equals(cb)) {
            league=String.valueOf(cb.getSelectedItem());
            j1.remove(p);
            j1.repaint();
            pointtable();
        }

        for(i=0;i<n;i++)
        {
            if (rb[i].isSelected())
            {
                change=i;
                team=rb[i].getText();
            }
        }

        if (e.getSource().equals(bw)) {
            change("w");
        }

        if (e.getSource().equals(bl)) {
            change("l");
        }

        if (e.getSource().equals(bt)) {
            change("t");
        }

        if (e.getSource().equals(bc)) {
          clear();
        }
     }


     void display()
     {
         String l[];
         bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 650, 310, 50);
            brec = new JButton("RECORD");
            brec.setBounds(380, 600, 310, 50);
            bk = new JButton("BACK");
            bk.setBounds(380, 650, 300, 50);
            bw = new JButton("WIN");
            bw.setBounds(20, 535, 150, 65);
            bl = new JButton("LOSE");
            bl.setBounds(180, 535, 150, 65);
            bt = new JButton("TIE");
            bt.setBounds(20, 600, 310, 50);
            bc = new JButton("CLEAR");
            bc.setBounds(380, 535, 310, 65);
        j1.add(bh);
        bh.addActionListener(this);
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(btm);
        btm.addActionListener(this);
        j1.add(bk);
        bk.addActionListener(this);
        j1.add(bw);
        bw.addActionListener(this);
        j1.add(bl);
        bl.addActionListener(this);
        j1.add(bt);
        bt.addActionListener(this);
        j1.add(bc);
        bc.addActionListener(this);
        j1.add(brec);
        brec.addActionListener(this);
        j1.add(bpp);
        bpp.addActionListener(this);

        i=0;
        try {
            stmt = c.con.prepareCall("Select count(distinct LEAGUE) from pointtable");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(distinct LEAGUE)");
            l=new String[n];
            l[0]="";
            stmt = c.con.prepareCall("Select distinct LEAGUE from pointtable");
            rs = stmt.executeQuery();
            while (rs.next()){
             l[i]=rs.getString(1);
             i++;
            }
            league=l[0];
            cb= new JComboBox(l);
             cb.setBounds(200,65,300,60);
             j1.add(cb);
            cb.addActionListener(this);

                 league=String.valueOf(cb.getSelectedItem());

         }
         catch(Exception ee){}

     }



     void pointtable()
    {
       String s[]={"TEAMS","PLAYED","WIN","LOSE","TIE","POINTS"};
         JLabel l1[]=new JLabel[7];
        p=new JPanel();
        p.setBounds(10, 140, 695, 380);
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        p.setLayout(null);
        p.setVisible(true);
        j1.add(p);
        try{
         stmt=c.con.prepareCall("select count(*) from pointtable where LEAGUE=?");
            stmt.setString(1, league);
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
        }
        catch(Exception e){ System.out.println(e);}

        for(j=0;j<6;j++)
            {
                    l1[j] = new JLabel(s[j]);
                    l1[j].setBounds(40+(110*j),5,83,50);
                    p.add(l1[j]);
            }
         i=0;
        try {
            stmt = c.con.prepareCall("Select * from pointtable where LEAGUE=?");
            stmt.setString(1, league);
            rs = stmt.executeQuery();
            while (rs.next()){
                rb[i]= new JRadioButton(rs.getString(1));
                rb[i].setBounds(10,40+(40*i),120,60);
                 p.add(rb[i]);
                 bg.add(rb[i]);
                 for(j=0;j<5;j++)
            {
                lpb[i][j] = new JLabel(String.valueOf(rs.getString(j+2)));
                lpb[i][j].setBounds(40+(110*(j+1)),40+(40*i),83,60);
                p.add(lpb[i][j]);
            }
                    i++;
            }

        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

     void change(String s)
     {
         int played=0,wins=0,lose=0,tie=0,point=0;
         try{
             stmt = c.con.prepareCall("Select * from pointtable where LEAGUE=? and TEAMS=?");
            stmt.setString(1, league);
            stmt.setString(2, team);
            rs = stmt.executeQuery();
            while (rs.next()){
                played=rs.getInt(2);
                wins=rs.getInt(3);
                lose=rs.getInt(4);
                tie=rs.getInt(5);
                point=rs.getInt(6);
            }}
            catch(Exception ee){System.out.println(ee);}

         if(s.equals("w"))
         {
         try{
             String query = "{CALL win(?,?)}";
              CallableStatement stm = c.con.prepareCall(query);
              stm.setString(1, league);
            stm.setString(2, team);
              stm.executeQuery();
          }
         catch(Exception ee){System.out.println(ee);}
         
            wins++;
             played++;
             point+=2;
         }
         else if(s.equals("l"))
         {
             try{
             String query = "{CALL loss(?,?)}";
              CallableStatement stm = c.con.prepareCall(query);
              stm.setString(1, league);
            stm.setString(2, team);
              stm.executeQuery();
          }
         catch(Exception ee){System.out.println(ee);}
             played++;
             lose++;
         }
         else
         {
             try{
                 String query = "{CALL draw(?,?)}";
              CallableStatement stm = c.con.prepareCall(query);
              stm.setString(1, league);
            stm.setString(2, team);
              stm.executeQuery();
          }
         catch(Exception ee){System.out.println(ee);}

             played++;
             tie++;
             point++;
         }
        /* try{
             stmt = c.con.prepareCall("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=? where LEAGUE=? and TEAMS=?");
            stmt.setInt(1, played);
            stmt.setInt(2, wins);
            stmt.setInt(3, lose);
            stmt.setInt(4, tie);
            stmt.setInt(5, point);
            stmt.setString(6, league);
            stmt.setString(7, team);
            stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}*/
         lpb[change][0].setText(String.valueOf(played));
         lpb[change][1].setText(String.valueOf(wins));
         lpb[change][2].setText(String.valueOf(lose));
         lpb[change][3].setText(String.valueOf(tie));
         lpb[change][4].setText(String.valueOf(point));
     }

     void clear()
     {
         try{
             stmt = c.con.prepareCall("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=?  where LEAGUE=? and TEAMS=?");
             stmt.setInt(1, 0);
             stmt.setInt(2, 0);
             stmt.setInt(3, 0);
             stmt.setInt(4, 0);
             stmt.setInt(5, 0);
             stmt.setString(6, league);
             stmt.setString(7, team);
             stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
         lpb[change][0].setText("0");
         lpb[change][1].setText("0");
         lpb[change][2].setText("0");
         lpb[change][3].setText("0");
         lpb[change][4].setText("0");
     }
}