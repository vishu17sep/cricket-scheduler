package cricin;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class league extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JPanel p;
    JButton bh, bsd, btm, bpt,bup,bbk,b[],bdel;
    JComboBox cb;
    JLabel l1[];
    JTextField t;
    int i,n,m;
    String league;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
    countries cn=new countries();

    league(String s)
    {
        league=s;
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            teams();
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

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
        }
        if (e.getSource().equals(bdel)) {
            delete();
        }

        for(int k=0;k<=cn.n;k++)
        {
            if (e.getSource().equals(b[k]))
            {
                if(k<m)
                {alter("-",l1[k].getText());}
                else
                { alter("+",l1[k].getText());}
            }
        }


       if (e.getSource().equals(cb)) {
            j1.remove(p);
            j1.repaint();
            teams();
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
            bdel = new JButton("DELETE LEAGUE");
            bdel.setBounds(20, 600, 680, 50);
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
        j1.add(bdel);
        bdel.addActionListener(this);
        j1.add(bbk);
        bbk.addActionListener(this);

        i=0;
         String l[];
        try {
            stmt = c.con.prepareCall("Select count(distinct LEAGUE) from pointtable");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(distinct LEAGUE)");
            l=new String[n+1];
            stmt = c.con.prepareCall("Select distinct LEAGUE from pointtable");
            rs = stmt.executeQuery();
            while (rs.next()){
             l[i]=rs.getString(1);
             i++;
            }
            l[i]="NEW LEAGUE";
            cb= new JComboBox(l);
             cb.setBounds(200,70,300,60);
             j1.add(cb);
            cb.addActionListener(this);
            } catch (Exception ee) {
            System.out.println(ee);
        }
    }

     void teams()
     {
          league=String.valueOf(cb.getSelectedItem());
           p=new JPanel();
        p.setBounds(10, 120, 695, 465);
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        p.setLayout(null);
        p.setVisible(true);
        j1.add(p);
         if(league.equals("NEW LEAGUE"))
         {
            t=new JTextField("");
            t.setBounds(10,20,300,50);
            p.add(t);
            t.addActionListener(this);
         }
          int k=0,j=0,l;
          i=0;
          String ll[];
        try {
            l1=new JLabel[cn.n+1];
            b=new JButton[cn.n+1];
            ll=new String[cn.n+1];

            stmt = c.con.prepareCall("Select * from pointtable where LEAGUE=?");
            stmt.setString(1, league);
            rs = stmt.executeQuery();
            while (rs.next()){
                i++;
                l1[i]=new JLabel(rs.getString(1));
                l1[i].setBounds(50+(200*j),40*k,150,60);
                p.add(l1[i]);
                b[i]=new JButton("-");
                b[i].setBounds(10+(200*j),15+(40*k),30,30);
                p.add(b[i]);
                b[i].addActionListener(this);
                ll[i]=l1[i].getText();
                k++;
                if(k>10)
                { j=1;
                k=0;
            }}
            i++;
            m=i;//no. of teams in that league
                for(k = 1; k <= cn.n; k++){
                    int count=0;
                for(j = 1; j <=m; j++)
                {if(cn.teams[k].equals(ll[j]))
                  {
                    count++;
                    break;
                  }
                }
                l=i-m;
                j=1;
                if(count==0)
                {
                    if(l>10)
                    { j=2;
                      l=i-m-11;
                    }
                l1[i]=new JLabel(cn.teams[k]);
                l1[i].setBounds(200+(200*j),40*l,150,60);
                p.add(l1[i]);
                b[i]=new JButton("+");
                b[i].setBounds(151+(200*j),15+(40*l),30,30);
                p.add(b[i]);
                b[i].addActionListener(this);
                i++;}
                }
        } catch (Exception ee) {
            System.out.println(ee);
        }
     }



     void alter(String s,String team)
     {
         try{
             if(league.equals("NEW LEAGUE"))
             {
                 if(!t.getText().equals(""))
                 {  league=t.getText();
                  cb.removeActionListener(this);
                  cb.addItem(league);
                  cb.setSelectedItem(league);}
                 else
                 {
                     JOptionPane.showMessageDialog(this,"MENTION NAME FIRST");
                     return;
                 }
             }
             if(s.equals("-"))
         {
             stmt=c.con.prepareCall("delete from pointtable where TEAMS=?");
             stmt.setString(1, team);
             stmt.executeUpdate();
         }
         else
             if(s.equals("+"))
         {
             stmt=c.con.prepareCall("insert into pointtable values(?,?,?,?,?,?,?)");
             stmt.setString(1, team);
             stmt.setInt(2, 0);
             stmt.setInt(3, 0);
             stmt.setInt(4, 0);
             stmt.setInt(5, 0);
             stmt.setInt(6, 0);
             stmt.setString(7, league);
             stmt.executeUpdate();
         }
            j1.remove(p);
            j1.repaint();
            teams();
         }
         catch(Exception ee){System.out.println(ee);}
     }

     void delete()
     {
         try{
         stmt=c.con.prepareCall("delete from pointtable where LEAGUE=?");
             stmt.setString(1, league);
             stmt.executeUpdate();
             JOptionPane.showMessageDialog(this,"LEAGUE IS DELETED");
             league l=new league("");
             j1.dispose();
         }
         catch(Exception ee){System.out.println(ee);}
     }

     }