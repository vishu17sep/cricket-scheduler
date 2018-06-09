package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class teams extends javax.swing.JFrame implements ActionListener {
    JFrame j1,j2;
    JButton bh, bsd, btm, bpt, bpp, brec,bbk,bchng,badd,badd1,bc,bk;
    JComboBox cb1;
    JTextField ts[]=new JTextField[2],t;
    JRadioButton nm[]=new JRadioButton[24];
    ButtonGroup jb;
    int i,j,p;
    String tm;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
    countries cn=new countries();

    public teams()
    {
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
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

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
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

        if (e.getSource().equals(bbk)) {
            cricinput c=new cricinput();
            j1.dispose();
        }
        if (e.getSource().equals(bk)) {
            teams c=new teams();
            j1.dispose();
        }

        if (e.getSource().equals(cb1)) {
            j1.dispose();
            tm=String.valueOf(cb1.getSelectedItem());
            team();
        }

        if (e.getSource().equals(bchng)) {
            change();
        }

        if (e.getSource().equals(badd)) {
            j1.dispose();
            playerprofile p=new playerprofile();
            j1.dispose();
            p.page2();
            p.newplayer(tm);
        }

        if(e.getSource().equals(bc))
        {
            addteam();
        }

        for(i=1;i<=p;i++)
         {
             if(nm[i].isSelected())
                 if(ts[0].getText().equals(""))
                 ts[0].setText(nm[i].getText());
                 else if(ts[1].getText().equals(""))
                  ts[1].setText(nm[i].getText());
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
            bpp.setBounds(20, 580, 310, 50);
            brec = new JButton("LEAGUE");
            brec.setBounds(380, 580, 310, 50);
            bc=new JButton("CREATE NEW TEAM");
            bc.setBounds(240, 292, 170, 60);
            bbk = new JButton("BACK");
            bbk.setBounds(10, 640, 690, 50);
            cb1 = new JComboBox(cn.teams);
            cb1.setBounds(200, 100, 250, 150);
            t=new JTextField("");
            t.setBounds(220, 250, 210, 40);
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
        j1.add(bc);
        bc.addActionListener(this);
        j1.add(bbk);
        bbk.addActionListener(this);
        j1.add(cb1);
        cb1.addActionListener(this);
        j1.add(t);
        t.addActionListener(this);
    }


     public void team()
    {
        jb=new ButtonGroup();
        j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            bk = new JButton("BACK");
            bk.setBounds(10, 640, 690, 50);
            j1.add(bk);
            bk.addActionListener(this);
        int k;
        JLabel no[]=new JLabel[20],pl,ex;
        bchng=new JButton("CHANGE");
        bchng.setBounds(550,550,150,60);
        j1.add(bchng);
        bchng.addActionListener(this);
        try{
            stmt=c.con.prepareCall("select count(*) from "+tm);
            rs=stmt.executeQuery();
            while(rs.next())
            p=rs.getInt("count(*)");
           for(i=1;i<=p;i++){
               nm[i]=new JRadioButton();
         stmt=c.con.prepareCall("Select * from "+tm+" where SR=?");
         stmt.setInt(1, i);
         rs=stmt.executeQuery();
           while(rs.next())
          nm[i].setText(rs.getString(2));
          }
         }
        catch(Exception e){ System.out.println(e);}
        for(i=1;i<=11&&i<=p;i++)
        {
        no[i]=new JLabel(""+i);
        no[i].setBounds(15,10+(i*43),100,50);
        j1.add(no[i]);
        }
        for(j=1;j<=11&&j<=p-i+1;j++)
        {
        no[j]=new JLabel(""+(j));
        no[j].setBounds(300,10+(j*43),100,50);
        j1.add(no[j]);
        }
        ts[0]=new JTextField("");
        ts[0].setBounds(50,550,220,60);
        j1.add(ts[0]);
        ts[1]=new JTextField("");
        ts[1].setBounds(300,550,220,60);
        j1.add(ts[1]);
        pl=new JLabel("PLAYING 11");
        pl.setBounds(50,10,150,50);
        j1.add(pl);
        ex=new JLabel("EXTRAS");
        ex.setBounds(350,10,150,50);
        j1.add(ex);

        k=0;int l;
        try{
        for(i=1,l=1;i<=p;i++,l++)
        {
            if(i==12) {k=1;l=1;}
        nm[i].setBounds(45+(k*285),10+(l*43),200,50);
        j1.add(nm[i]);
        jb.add(nm[i]);
        nm[i].addActionListener(this);
        }}
        catch(Exception e){System.out.println(e);}

        badd=new JButton("ADD NEW PLAYER");
        badd.setBounds(550,110,150,100);
        j1.add(badd);
        badd.addActionListener(this);
        nm[23]=new JRadioButton("");
        nm[23].setText("");
        nm[23].setVisible(false);
         nm[23].setSelected(true);
    }


     void change()
     {
         int n[]=new int[2];
         try{
             for(i=0;i<2;i++)
             {
             stmt=c.con.prepareCall("select * from "+tm+" where PLAYER=?");
             stmt.setString(1, ts[i].getText());
            rs=stmt.executeQuery();
             while(rs.next())
             n[i]=rs.getInt(1);
             }

         stmt=c.con.prepareCall("update "+tm+" set SR=? where PLAYER=?");
        stmt.setInt(1, n[1]);
        stmt.setString(2, ts[0].getText());
        stmt.executeUpdate();
        stmt=c.con.prepareCall("update "+tm+" set SR=? where PLAYER=?");
        stmt.setInt(1, n[0]);
        stmt.setString(2, ts[1].getText());
        stmt.executeUpdate();
        }
        catch(Exception ee){System.out.println(ee);}
         j1.dispose();
         team();
     }


     void addteam()
     {
         Statement st;
         String s=String.valueOf(t.getText());
         try {
             stmt=c.con.prepareCall("select * from teams where TEAMS=?");
             stmt.setString(1, s);
            rs=stmt.executeQuery();
            if(rs.absolute(1))
            {JOptionPane.showMessageDialog(this,"ALREADY EXIST");}
         else{
          String Table = "CREATE TABLE "+s+" ("
            + "SR INT(2),"+ "PLAYER VARCHAR(30),"+ "TEAMS VARCHAR(20),"+ "TESTBAT INT(5),"
            + "ODIBAT INT(5),"+ "T20BAT INT(5),"+ "RUNS INT(7),"
            + "FOURS INT(5),"+ "SIX INT(5),"+ "CENTURY INT(5),"
            + "FIFTY INT(5),"+ "OVERS FLOAT(12),"+ "WICKETS INT(5),"
            + "TESTBALL INT(5),"+ "ODIBALL INT(5),"+ "T20BALL INT(5),"
            + "AGE INT(3))";
            st = c.con.createStatement();
            st.executeUpdate(Table);
            stmt=c.con.prepareCall("insert into teams values(?)");
            stmt.setString(1, s);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this,"CREATED SUCCESSFULLY");
            }}
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
     }




}

