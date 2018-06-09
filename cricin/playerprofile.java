package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class playerprofile extends javax.swing.JFrame implements ActionListener {
    JFrame j1,j2;
    JButton bh, bsd, btm, bpt, bpp, brec,bbk1,bbk2,badd1,badd2,bsrch,bedt;
    JTextField t1,tt1[]=new JTextField[10],tt2[]=new JTextField[8],tp1,tp2,tp3;
    int i,j,count;
    String tm,type[]={"ODI","TEST","T20"},status[]={"DUE","LIVE","PLAYED"},plyr;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
    countries cn=new countries();

    public playerprofile()
    {
            j1=new JFrame();
            j1.setLayout(null);
            j1.setBounds(300, 10, 715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            page1();
         }


     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(bh))
        {
            cricinput c=new cricinput();
            j1.dispose();
            j2.dispose();
        }
        if (e.getSource().equals(bsd)) {
            schedule s=new schedule();
            j1.dispose();
            j2.dispose();
        }
        if (e.getSource().equals(btm)) {
            teams t=new teams();
            j1.dispose();
            j2.dispose();
        }

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j1.dispose();
            j2.dispose();
        }
        if (e.getSource().equals(bpp)) {
            playerprofile p=new playerprofile();
            j1.dispose();
        }

        if (e.getSource().equals(brec)) {
            league l=new league("");
            j1.dispose();
        }

        if (e.getSource().equals(bbk1)) {
            cricinput c=new cricinput();
            j1.dispose();
        }

        if (e.getSource().equals(bbk2)) {
            playerprofile p=new playerprofile();
            j2.dispose();
        }

        if(e.getSource().equals(badd1))
        {
            page2();
            newplayer("");
        }

         if(e.getSource().equals(badd2))
        {
            add();
        }

        if(e.getSource().equals(bsrch)||e.getActionCommand().equals("OK"))
        {
          edit(t1.getText());
        }

         if(e.getSource().equals(bedt))
        {
            update();
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
            brec = new JButton("LEAGUE");
            brec.setBounds(380, 600, 310, 50);
            bbk1 = new JButton("BACK");
            bbk1.setBounds(10, 650, 690, 50);

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
        j1.add(bbk1);
        bbk1.addActionListener(this);

    }

     void page1()
     {
            t1= new JTextField("ENTER NAME");
            t1.setBounds(300, 150, 150, 50);
            badd1=new JButton("ADD ANOTHER PLAYER");
            badd1.setBounds(20,520,670,50);
            bsrch = new JButton("SEARCH");
            bsrch.setBounds(330, 250, 100, 50);
            j1.add(badd1);
            j1.add(t1);
            j1.add(bsrch);
            bsrch.addActionListener(this);
            t1.setActionCommand("OK");
            t1.addActionListener(this);
            badd1.addActionListener(this);
     }

     void page2()
     {
         j1.dispose();
         j2=new JFrame();
            j2.setLayout(null);
            j2.setSize(715, 950);
            j2.setVisible(true);
            j2.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j2.setResizable(false);
            bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            bbk2 = new JButton("BACK");
            bbk2.setBounds(10, 650, 690, 50);

        j2.add(bh);
        bh.addActionListener(this);
        j2.add(bpt);
        bpt.addActionListener(this);
        j2.add(bsd);
        bsd.addActionListener(this);
        j2.add(btm);
        btm.addActionListener(this);
        j2.add(bbk2);
        bbk2.addActionListener(this);
     }

     void newplayer(String s)
    {
        JLabel l1[]=new JLabel[10],l2[]=new JLabel[10],lp1,lp2,lp3,lbat,lball;
        String s1[]={"TEST","ODI","T20","RUNS","FOURS","SIXES","CENTURY","FIFTY"},s2[]={"TEST","ODI","T20","OVERS","WICKETS"};
         badd2 = new JButton("ADD");
          badd2.setBounds(450, 540, 230, 95);
          j2.add(badd2);
          badd2.addActionListener(this);
        lp1=new JLabel("NAME");
        lp1.setBounds(30,90,100,40);
        j2.add(lp1);
        lp2=new JLabel("TEAM");
        lp2.setBounds(30,135,100,40);
        j2.add(lp2);
        lp3=new JLabel("AGE");
        lp3.setBounds(30,180,100,40);
        j2.add(lp3);
        tp1=new JTextField("");
        tp1.setBounds(110,90,250,40);
        j2.add(tp1);
        tp2=new JTextField(s);
        tp2.setBounds(110,135,250,40);
        j2.add(tp2);
        tp3=new JTextField("");
        tp3.setBounds(110,180,250,40);
        j2.add(tp3);
        lbat=new JLabel("BATTING");
        lbat.setBounds(180,220,200,50);
        j2.add(lbat);
        lball=new JLabel("BOWLING");
        lball.setBounds(520,220,200,50);
        j2.add(lball);
        for(i=0;i<8;i++)
        {   l1[i]=new JLabel(s1[i]);
            l1[i].setBounds(30,270+45*i,100,40);
        j2.add(l1[i]);
        tt1[i]=new JTextField("");
        tt1[i].setBounds(110,270+45*i,230,40);
        j2.add(tt1[i]);
        }
        for(i=0;i<5;i++)
        {   l2[i]=new JLabel(s2[i]);
            l2[i].setBounds(370,270+45*i,100,40);
        j2.add(l2[i]);
        tt2[i]=new JTextField("");
        tt2[i].setBounds(450,270+45*i,230,40);
        j2.add(tt2[i]);
        }
    }


     public void edit(String b)
    {
        try {
            for (j = 1; j<=cn.n; j++) {
                stmt = c.con.prepareCall("Select * from " + cn.teams[j] + " where PLAYER=?");
                stmt.setString(1, b);
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
            else
            {
                page2();
                j2.remove(badd1);
                bedt = new JButton("EDIT");
                bedt.setBounds(450, 540, 230, 95);
                j2.add(bedt);
                bedt.addActionListener(this);
                newplayer("");
                stmt = c.con.prepareCall("Select * from " + cn.teams[j] + " where PLAYER=?");
                stmt.setString(1, b);
                rs = stmt.executeQuery();
                while (rs.next()) {
                   tp1.setText(b);
                   tp2.setText(cn.teams[j]);
                   tp3.setText(String.valueOf(rs.getInt(17)));
                   tt1[0].setText(String.valueOf(rs.getInt(4)));
                   tt1[1].setText(String.valueOf(rs.getInt(5)));
                   tt1[2].setText(String.valueOf(rs.getInt(6)));
                   tt1[3].setText(String.valueOf(rs.getInt(7)));
                   tt1[4].setText(String.valueOf(rs.getInt(8)));
                   tt1[5].setText(String.valueOf(rs.getInt(9)));
                   tt1[6].setText(String.valueOf(rs.getInt(10)));
                   tt1[7].setText(String.valueOf(rs.getInt(11)));
                   tt2[0].setText(String.valueOf(rs.getInt(14)));
                   tt2[1].setText(String.valueOf(rs.getInt(15)));
                   tt2[2].setText(String.valueOf(rs.getInt(16)));
                   tt2[3].setText(String.valueOf(rs.getInt(12)));
                   tt2[4].setText(String.valueOf(rs.getInt(13)));
            }}
               }
               catch(Exception ee){ System.out.println(ee);}
        }


     void update()
     {
         try{
            stmt=c.con.prepareCall("update "+tp2.getText()+" set PLAYER=?,TEAMS=?,TESTBAT=?,ODIBAT=?,T20BAT=?,RUNS=?,FOURS=?,SIX=?,CENTURY=?,FIFTY=?,OVERS=?,WICKETS=?,TESTBALL=?,ODIBALL=?,T20BALL=?,AGE=? where PLAYER=?");
          stmt.setString(1, tp1.getText());
          stmt.setString(2, tp2.getText());
          stmt.setInt(3, Integer.parseInt(tt1[0].getText()));
          stmt.setInt(4, Integer.parseInt(tt1[1].getText()));
          stmt.setInt(5, Integer.parseInt(tt1[2].getText()));
          stmt.setInt(6, Integer.parseInt(tt1[3].getText()));
          stmt.setInt(7, Integer.parseInt(tt1[4].getText()));
          stmt.setInt(8, Integer.parseInt(tt1[5].getText()));
          stmt.setInt(9, Integer.parseInt(tt1[6].getText()));
          stmt.setInt(10, Integer.parseInt(tt1[7].getText()));
          stmt.setFloat(11, Float.parseFloat(tt2[3].getText()));
          stmt.setInt(12, Integer.parseInt(tt2[4].getText()));
          stmt.setInt(13, Integer.parseInt(tt2[0].getText()));
          stmt.setInt(14, Integer.parseInt(tt2[1].getText()));
          stmt.setInt(15, Integer.parseInt(tt2[2].getText()));
          stmt.setInt(16, Integer.parseInt(tp3.getText()));
          stmt.setString(17, tp1.getText());
          stmt.executeUpdate();
          JOptionPane.showMessageDialog(this,"UPDATED SUCCESSFULLY");
          check_records cr=new check_records();
        }
        catch(Exception e){ System.out.println(e);}
}

     void add()
     {
          int n=0;
          try{
           stmt=c.con.prepareCall("select count(*) from "+tp2.getText());
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
            if(n<33)
            {
           stmt=c.con.prepareCall("insert into "+tp2.getText()+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          stmt.setInt(1, n+1);
          stmt.setString(2, tp1.getText());
          stmt.setString(3, tp2.getText());
          stmt.setInt(4, Integer.parseInt(tt1[0].getText()));
          stmt.setInt(5, Integer.parseInt(tt1[1].getText()));
          stmt.setInt(6, Integer.parseInt(tt1[2].getText()));
          stmt.setInt(7, Integer.parseInt(tt1[3].getText()));
          stmt.setInt(8, Integer.parseInt(tt1[4].getText()));
          stmt.setInt(9, Integer.parseInt(tt1[5].getText()));
          stmt.setInt(10, Integer.parseInt(tt1[6].getText()));
          stmt.setInt(11, Integer.parseInt(tt1[7].getText()));
          stmt.setFloat(12, Float.parseFloat(tt2[3].getText()));
          stmt.setInt(13, Integer.parseInt(tt2[4].getText()));
          stmt.setInt(14, Integer.parseInt(tt2[0].getText()));
          stmt.setInt(15, Integer.parseInt(tt2[1].getText()));
          stmt.setInt(16, Integer.parseInt(tt2[2].getText()));
          stmt.setInt(17, Integer.parseInt(tp3.getText()));
          stmt.executeUpdate();
          JOptionPane.showMessageDialog(this,"ADDED SUCCESSFULLY");
          check_records cr=new check_records();
        }}
        catch(Exception e){ System.out.println(e);}
     }
}
