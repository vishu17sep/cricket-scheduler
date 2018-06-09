package cricin;
import cricapp.Cricapp;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class cricinput extends javax.swing.JFrame implements ActionListener {

    JButton bh, bsd, btm, bpt, bpp, brec,bsc[]=new JButton[3],blog;
    String stts[]=new String[3];
    int brd,i;
   connection c;
   PreparedStatement stmt;
   ResultSet rs;
   JFrame j1;

   public cricinput() {
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
        blog = new JButton("LOGOUT");
        blog.setBounds(10, 650, 690, 50);
        j1=new JFrame();
        j1.setLayout(null);
        j1.setBounds(300, 10, 715, 950);//CHECK
        j1.setVisible(true);
        j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j1.setResizable(false);
        display();
    }



   public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bh))
        {check_records CR=new check_records();
         Cricapp c=new Cricapp();
            
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

        if (e.getSource().equals(bsc[1])||e.getSource().equals(bsc[2]))
        {
           if(e.getSource().equals(bsc[1]))
           brd=1;
           if(e.getSource().equals(bsc[2]))
            brd=2;
            if(stts[brd].equals("LIVE"))
            {scoreboard s=new scoreboard(brd);
             }
             else
            {start s=new start(brd);}
             j1.dispose();
        }

        if (e.getSource().equals(bpp)) {
            playerprofile p=new playerprofile();
            j1.dispose();
        }

        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j1.dispose();
        }

        if (e.getSource().equals(brec)) {
            league l=new league("");
            j1.dispose();
        }

        if (e.getSource().equals(blog)) {
            password p=new password();
            j1.dispose();
        }
   }

   void display() {
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
        j1.add(blog);
        blog.addActionListener(this);
        home();
    }


    void home() {
        try {
            for(i=1;i<=2;i++)
             {
                 bsc[i] = new JButton("SCORE");
                 bsc[i].setBounds(30+((i-1)*350), 100, 300, 150);
                 j1.add(bsc[i]);
                 bsc[i].addActionListener(this);
            stmt =c.con.prepareCall("Select * from schedule where SR=?");
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            while (rs.next()) {
                stts[i]=rs.getString(2);
            String a="<html>"+rs.getString(6)+','+" "+rs.getString(7)+"<br>"+rs.getString(9)+"<html>";
            a="<html>"+rs.getString(4)+'\t'+"vs"+'\t'+rs.getString(5)+"<br>"+a+"<html>";
            a="<html>"+stts[i]+"<br>"+a+"<html>";
            bsc[i].setText(a);
            }}

        } catch (Exception ee) {
            System.out.println(ee);
        } }

    public static void main(String[] args) {
        connection cc=new connection();
        cc.connect();
        
       // System.out.println("first");
        cricinput c = new cricinput();
    }

}
