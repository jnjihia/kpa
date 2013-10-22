
package kpa;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeremy
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.border.Border;

public class login extends JFrame implements ActionListener,FocusListener{
    
    private JLabel name,clearance,password,alert,logo;
    JPanel panel = new JPanel(null);
    private JComboBox credentials;
    private  JButton Exit,login;
    private JTextField nametxt;
    private JPasswordField passwordtxt;
    
    public login()
    {
    super("LOGIN FORM");
    setUndecorated(true);
    setIconImage (getToolkit().getImage ("images/dat.jpg"));
    //Container holder = getContentPane();
    Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
    Border bd = BorderFactory.createLineBorder(Color.blue, 5);
    
    
    logo = new JLabel(new ImageIcon("src/kpa/images/budget.png"));
    logo.setBounds(0, 0, 362, 138);
    
    clearance = new JLabel("CLEARANCE");
    clearance.setBounds(15,155, 100, 25);
    clearance.setForeground(Color.black);
    clearance.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC,15));
    
    
    alert=new JLabel("",JLabel.CENTER);
    alert.setBounds(0, 360, 362, 40);
    alert.setForeground(Color.red);
    alert.setOpaque(true);
    alert.setFont(new Font("Corbel", Font.BOLD ,12));
    
    credentials = new JComboBox();
    credentials.setBounds(130, 155, 140, 25);
    try{
         String []results= new String[10];
         int i=0;
         int j;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
        Statement stmn=conn.createStatement();
        ResultSet rs=stmn.executeQuery("Select Clearance from clearance");
        
        while(rs.next()){
        results[i]=rs.getString(1);
        i++;
        }
        for(j=0;j<i;j++){
        credentials.addItem(results[j]);
        credentials.addActionListener(this);
        credentials.setSelectedItem(results[0]);
        }
        }
        catch(Exception conection){
        System.out.print("database" +conection);
        JOptionPane.showMessageDialog(this,"the program cannot reach the database");
        alert.setBackground(Color.white);
        alert.setIcon(new ImageIcon("src/images/dat.jpg"));
        alert.setText("No database connection, please contact your system admin");
       
        }
    
    
    name = new JLabel("USERNAME");
    name.setBounds(15, 200, 100,25 );
    name.setForeground(Color.black);
    name.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC,15));
    
    nametxt = new JTextField();
    nametxt.setBounds(130, 200, 140, 28);
//    nametxt.addKeyListener(new KeyAdapter() {
//    public void keytyped(KeyEvent ke){
//    if (! Character.isDigit(ke.getKeyChar())){
//             System.out.println(ke);
//    }
//    }
//    
//    });
    
    
    
    
    password = new JLabel("PASSWORD");
    password.setBounds(15, 235, 100, 25);
    password.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC,15));
    
    passwordtxt = new JPasswordField();
    passwordtxt.setBounds(130, 235, 140, 25);
    
    
    login= new JButton("Login");
    login.setBounds(60, 290, 100, 25);
    login.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC,15));
    login.addActionListener(this);
    
    
    
    Exit= new JButton("Exit");
    Exit.setBounds(230, 290, 100, 25);
    Exit.setFont(new Font("Corbel", Font.BOLD + Font.ITALIC,15));
    Exit.addActionListener(this);
    
    
    
    
    panel.add(logo);
    panel.add(clearance);
    panel.add(credentials);
    panel.add(name);
    panel.add(nametxt);
    panel.add(password);
    panel.add(passwordtxt);
    panel.add(Exit);
    panel.add(login);
    panel.add(alert);

    this.add(panel);
    this.setBounds(0, 0, 362, 400);
    this.setBackground(Color.red);
    this.setLocation(dime.width / 2 - getSize().width / 2, dime.height / 2 - getSize().height / 2)
            ;
 
    
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      
      if(obj==Exit){
      closeapp();
      }
      else if((obj==login))
                  {
                      
             String paswad = new String(passwordtxt.getPassword());
            if (nametxt.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "provide username on logon");       //if name section is not filled
                name.requestFocus();
            } else if (paswad.equals("")) {
                JOptionPane.showMessageDialog(this, "provide password on logon");
            } else {
                String categorie="";
                String ctgry = (String) credentials.getSelectedItem();
                if (ctgry.equals("Chairman")) {
                    categorie = "1";
                } else if (ctgry.equals("Admin")) {
                    categorie = "2";
                } 
                String userName = nametxt.getText();   
                ResultSet rst;
                try {
                    Connection cnect =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
                    Statement log=cnect.createStatement();
                    String Query = "SELECT username FROM users WHERE id_u ='" + categorie + "' AND username = '" + userName + "' AND password = '" + paswad + "'";
                    rst = log.executeQuery(Query);
                    
                    int count = 0;
                    while (rst.next()) {
                        count = count + 1;
                    }
                    if (count == 1) {
                        int cat = Integer.parseInt(categorie);
                        // System.out.println(cat);
                        kpa_home home = new kpa_home(cat,userName, cnect);
                       // cat,userName, cnect
                       //new trial(1,1, con);                         
                       this.dispose();
//                        System.out.println("baadae");
                    } else if (count > 1) {
                        JOptionPane.showMessageDialog(null, "duplicate user access resticted");
                    } else {
                        JOptionPane.showMessageDialog(null, "user not found");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage()+" loggin");
                }


            }


        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void closeapp() {
    System.exit(0);
        
    }
    
private boolean enterkeyed(KeyEvent ke) {
        char cr=ke.getKeyChar();  
       if (cr == KeyEvent.VK_ENTER){
             }
  return true;
    }
}
