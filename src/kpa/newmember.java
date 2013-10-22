/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class newmember extends JInternalFrame implements ActionListener,FocusListener{
    
    private JLabel names,kpaid,nationalid,telephone,emailadr;
    private JButton infolble,clearr,submit;
    private JTextField nameztxt,kpatxt,nationalidtxt,phonetxt,emailtxt;
    Connection mshiko;
    Container all= getContentPane();
    
    private java.util.Date currDate = new java.util.Date ();				    //Creating Object.
    private SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-mm-dd", Locale.getDefault());	    //Changing Format.
    private String date = sdf.format (currDate);
    
   public newmember(Connection con){
   super("Add new Member",false,true,false,true);
   
      setSize(630,350);

   setLocation((((Toolkit.getDefaultToolkit().getScreenSize().width  -getWidth()) / 2/3)),
               ((Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2/3));

    all.setLayout(null);
    
    infolble= new JButton("New Member Details");
    infolble.setBounds(0, 0, 630, 20);
    infolble.setOpaque(true);
    infolble.setBackground(Color.white);
    infolble.setFont(new Font("castellar", Font.BOLD, 18));
    infolble.setForeground(Color.blue);
    
    
    names=new JLabel("Members Name");
    names.setBounds(10,40, 120, 25);
    names.setFont(new Font("Gisha", Font.BOLD, 14));
    
    nameztxt = new JTextField();
    nameztxt.setBounds(150, 40, 150, 27);
    nameztxt.addKeyListener(new KeyAdapter() {
        @Override
         public void keyTyped(KeyEvent e) {
                nondigits(e);
            }
     
    });
    
    kpaid = new JLabel("K.P.A Number");
    kpaid.setBounds(325,40, 100, 25);
    kpaid.setFont(new Font("Gisha", Font.BOLD, 14));
    
    kpatxt = new JTextField();
    kpatxt.setBounds(430, 40, 120, 25);
    
    telephone= new JLabel("Phone No.");
    telephone.setBounds(10, 75, 100, 25);
    telephone.setFont(new Font("Gisha", Font.BOLD, 14));
    
    phonetxt = new JTextField();
    phonetxt.setBounds(150, 75, 150, 25);
    
    emailadr = new JLabel("Email Address");
    emailadr.setBounds(325, 75, 150, 25);
    emailadr.setFont(new Font("Gisha", Font.BOLD, 14));
    
    emailtxt =new JTextField();
    emailtxt.setBounds(430, 75, 150, 25);
    
    clearr = new JButton("Clear");
    clearr.setBounds(180, 130, 150, 30);
    clearr.addActionListener(this);
    
    submit = new JButton("Add Member");
    submit.setBounds(350,130,150,30);
    submit.addActionListener(this);
 
    
   mshiko=con;
   
   all.add(infolble);
   all.add(names);
   all.add(nameztxt);
   all.add(kpaid);
   all.add(kpatxt);
   all.add(telephone);
   all.add(phonetxt);
   all.add(emailadr);
   all.add(emailtxt);
   all.add(clearr);
   all.add(submit);
   
   
   
   setVisible(true);
   }
    
//    public static void main(String[] args) {
//    newmember log= new newmember();
//    log.setVisible(true);
//}
    
   

    

    @Override
    public void actionPerformed(ActionEvent emn) {
        Object memba = emn.getSource();
        
        if(memba==clearr){
        clearall();   
        }
        else if(memba==submit)
        {          
            String name,kpa,namba,email;
            name=nameztxt.getText();
            kpa=kpatxt.getText();
            namba =phonetxt.getText();
            email=emailtxt.getText();
            ImageIcon alert = new ImageIcon(getClass().getResource("images/success.png"));


                            if(name.equals(""))
                            {
                            JOptionPane.showMessageDialog(null, "Members Name not filled", "ALERT", JOptionPane.WARNING_MESSAGE, null);
                            nameztxt.requestFocus();
                             }
                            else if(kpa.equals("")){                 
                              JOptionPane.showMessageDialog(null, "KPA Number not filled", "ALERT", JOptionPane.WARNING_MESSAGE, null);
                              kpatxt.requestFocus();
                            }
                            else if(namba.length()>10){                 
                              JOptionPane.showMessageDialog(null, "The phone number you input is incorect \n use format 07...", "ALERT", JOptionPane.WARNING_MESSAGE, null);
                              kpatxt.requestFocus();
                            }
                           else if(namba.equals("")){
                            
                              JOptionPane.showMessageDialog(null, "Phone number not filled", "Alert", JOptionPane.WARNING_MESSAGE, null);
                              phonetxt.requestFocus();
                            }                            
                           else{
                                try{
                                 int count =0;   
                               Statement sttmn = mshiko.createStatement();
                               ResultSet rs = sttmn.executeQuery("SELECT names FROM members_table WHERE k_p_a_id =("+kpa+")");
                               
                               while(rs.next()){
                               count++;
                               System.out.print(count);
                               }
                               if(count > 0){
                               JOptionPane.showMessageDialog(null, "The Member you are adding already exists", "Alert", JOptionPane.WARNING_MESSAGE);
                               
                               }
                               else{
                               int namber = Integer.parseInt(kpa);
                              int kill =sttmn.executeUpdate("INSERT INTO members_table VALUES('"+namber+"','"+name+"','"+namba+"','"+email+"','"+null+"','"+date+"')");
                              
                              if(kill==1)
                              {JOptionPane.showMessageDialog(null, "Member successfully added", "SUCCESS", JOptionPane.PLAIN_MESSAGE,alert);}
                              else{
                               JOptionPane.showMessageDialog(null, "Member was not added", "ALERT", JOptionPane.WARNING_MESSAGE, null);
                              }
                               }
                                    
                                    
                                   }
                            catch(Exception uu){
                            
                            System.out.print(uu);
                            
                            }
                               
                            
                                clearall();
                           
                           }
        
        }
        
        
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusLost(FocusEvent fe) {

       
    }
    
    private void nambatxtKeyTyped(KeyEvent ke) {
        char cr=ke.getKeyChar();  
       if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }
    }
    
    private void nondigits(KeyEvent ke) {
        char cr=ke.getKeyChar();  
       if (!( Character.isLetter(cr) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER)||(cr == KeyEvent.VK_SPACE)||(cr==KeyEvent.VK_CAPS_LOCK))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "character not accepted");
             }
    }
    
    private void clearall(){
    nameztxt.setText(null);
    kpatxt.setText(null);
    phonetxt.setText(null);
    emailtxt.setText(null);
    
    }
    
  
    
}
