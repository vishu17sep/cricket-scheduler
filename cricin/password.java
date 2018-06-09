package cricin;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class password extends javax.swing.JFrame implements ActionListener {
    JPasswordField p,np[]=new JPasswordField[2] ;
    JFrame j1;
    JLabel l[]=new JLabel[5];
    JTextField t1,t2;
    JButton b1,b2,b3;
    PreparedStatement stmt;
    connection c;
    ResultSet rs;
    String pass,user,checkusr,checkpass="",repass,nwuser,nwpass;

    password()
    {
        j1=new JFrame("LOGIN");
        p = new JPasswordField("");
        p.setBounds(225,200 ,250,50);
        j1.add(p);
        p.setActionCommand("OK");
        p.addActionListener(this);
        l[0]=new JLabel("USER NAME");
        l[1]=new JLabel("PASSWORD");
        l[2]=new JLabel("NEW USER");
        l[3]=new JLabel("PASSWORD");
        l[4]=new JLabel("CONFIRM PASSWORD");
        for(int i=0;i<2;i++)
        {
            np[i] = new JPasswordField("");
            np[i].setActionCommand("NEW");
            np[i].addActionListener(this);
            l[i].setBounds(310, 60+(i*100), 200, 50);
            j1.add(l[i]);
        }
            l[2].setBounds(115,450, 200, 50);
            j1.add(l[2]);
            l[3].setBounds(390,450, 200, 50);
            j1.add(l[3]);
            l[4].setBounds(80,530, 200, 50);
            j1.add(l[4]);
            np[0].setBounds(300,490 ,250,50);
            j1.add(np[0]);
            np[1].setBounds(20,570,250,50);
            j1.add(np[1]);

        t1=new JTextField("USERNAME");
        t1.setBounds(225, 100, 250, 50);
        j1.add(t1);
        t1.setActionCommand("OK");
        t1.addActionListener(this);
        t2=new JTextField("NEW USERNAME");
        t2.setBounds(20, 490, 250, 50);
        j1.add(t2);
        t2.setActionCommand("NEW");
        t2.addActionListener(this);
        b1=new JButton("LOG IN");
        b1.setBounds(275, 260, 150, 50);
        j1.add(b1);
        b1.addActionListener(this);
        b2=new JButton("DELETE ACCOUNT");
        b2.setBounds(275, 320, 150, 50);
        j1.add(b2);
        b2.addActionListener(this);
        b3=new JButton("SIGN UP");
        b3.setBounds(350,570,150,50);
        j1.add(b3);
        b3.addActionListener(this);
        j1.setLayout(null);
        j1.setBounds(300, 10, 715, 950);
        j1.setVisible(true);
        j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j1.setResizable(false);


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("NEW")||e.getSource().equals(b3))
        {
            update();
            return;
        }
            pass = String.valueOf(p.getPassword());
            user=t1.getText();
            login();
            if(checkpass.equals("")){
                JOptionPane.showMessageDialog(this,
                "Invalid USER NAME. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        if (pass.equals(checkpass))
        {
            if(e.getActionCommand().equals("OK")||e.getSource().equals(b1))
            { JOptionPane.showMessageDialog(this,"LOGIN SUCCESSFULL");
                cricinput c=new cricinput();
            j1.dispose();}
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid PASSWORD. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(e.getSource().equals(b2))
        {
            delete();
        }

       /* if(e.getActionCommand().equals("NEW")||e.getSource().equals(b3))
        {
            update();
        }*/
    }

    void login()
    {
        try {
            stmt =c.con.prepareCall("Select * from log where User=?");
            stmt.setString(1, user);
            rs = stmt.executeQuery();
            while (rs.next())
                checkpass=rs.getString(2);
        }
         catch (Exception ee) {
            System.out.println(ee);
        }
    }

    void delete()
    {
        try {
            stmt =c.con.prepareCall("delete from log where User=?");
            stmt.setString(1, user);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this,"DELETED SUCCESSFULLY");
            t1.setText("");
            p.setText("");
        }
         catch (Exception ee) {
            System.out.println(ee);
        }
    }

    void update()
    {
        nwpass = String.valueOf(np[0].getPassword());
            repass=String.valueOf(np[1].getPassword());
            nwuser=t2.getText();
            if(nwpass.equals(repass)){
               if(nwuser.equals(""))
                { JOptionPane.showMessageDialog(this,
                "Invalid NEW USERNAME. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
                return;
                }
                if(nwpass.equals(""))
                { JOptionPane.showMessageDialog(this,
                "Invalid NEW PASSWORD. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
                return;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                "Invalid NEW PASSWORD. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        try{
            stmt=c.con.prepareCall("select * from log where USER=?");
            stmt.setString(1,nwuser);
            rs = stmt.executeQuery();
            if(rs.absolute(1))
               JOptionPane.showMessageDialog(this,
                "USER NAME ALREADY EXIST. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
            else{
            stmt=c.con.prepareCall("insert into log values(?,?)");
            stmt.setString(1,nwuser);
            stmt.setString(2,nwpass);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this,"REGISTERED SUCCESSFULLY");
                 cricinput c=new cricinput();
                 j1.dispose();
                }}catch(Exception ee){ System.out.println(ee);}
    }



    public static void main(String[] args)
    {
         connection cc=new connection();
        cc.connect();
        password p=new password();
    }
}

