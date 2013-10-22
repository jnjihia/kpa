
package kpa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jeremy
 */
public class make_pay extends JFrame implements ActionListener,ChangeListener,FocusListener{
     private JLabel national,branch,agm,welfare,investments,year,namba,who,confirmation,balance;
     private JTextField nationaltxt,branchtxt,agmtxt,welfaretxt,investmentxt,nambatxt,bal1txt,bal2txt,bal3txt,bal4txt;
     private JCheckBox nationale,branche,agme,welfaree,invess;
     private JComboBox yearco;
     private JButton confirm,makepay,cancel;
     private JPanel allpanel= new JPanel();
     private JPanel nationo,brnc,agmp,welfar;
     private String namez,savedno;
    private int ako =0; 
     
    TitledBorder tb1 =BorderFactory.createTitledBorder("NATIONAL");
    TitledBorder tb2 =BorderFactory.createTitledBorder("BRANCH");
    TitledBorder tb3 =BorderFactory.createTitledBorder("AGM");
    TitledBorder tb4 =BorderFactory.createTitledBorder("WELFARE");
    
    Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
     
     
     
     private make_pay(){
         
     super("MAKE PAYMENT");
     allpanel.setLayout(null);
     
     who = new JLabel("PAYMENT FOR MEMBER NO:");
     who.setFont(new Font("Candara", Font.PLAIN+ Font.BOLD, 13));
     who.setBounds(20, 30, 180, 25);
     allpanel.add(who);
     
     nambatxt = new JTextField();
     nambatxt.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent ke) {
             char cr=ke.getKeyChar();    
           
         if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 return;
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 return;
             }
         });
     nambatxt.setBounds(210, 30, 100, 23);

     
     allpanel.add(nambatxt);
     
     confirm= new JButton("CONFIRM");
     confirm.setBounds(340, 30, 100, 23);
     confirm.addActionListener(this);
     allpanel.add(confirm);
     
     confirmation = new JLabel();
     confirmation.setBounds(20, 65, 200, 25);
     confirmation.setOpaque(true);
     confirmation.setFont(new Font("Candara", Font.PLAIN+ Font.BOLD, 15));
     allpanel.add(confirmation);
     
     
     Font cambi = new Font("cambira", Font.PLAIN, 15);
     year =new JLabel("Payment for the year");
     year.setBounds(240, 65, 160, 25);
     year.setFont(cambi);
     allpanel.add(year);
     
     
     String [] items = { " ", "2012" , "2013" , "2014" , "2015" , "2016" , "2017" };
     yearco= new JComboBox(items); 
     yearco.setBounds(410, 65, 100, 25);
     yearco.setSelectedItem(0);
     yearco.addItemListener( new ItemListener() {

             @Override
             public void itemStateChanged(ItemEvent e) {
               String selected =(String) yearco.getSelectedItem();
               try{      
              int mwaka=0;
              Class.forName("com.mysql.jdbc.Driver");
              Connection cnect =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
              Statement confirm = cnect.createStatement();
              ResultSet checkyear = confirm.executeQuery("Select year from finances where member = '"+nambatxt.getText()+"' AND year = '"+selected+"'");
              
              while(checkyear.next()) {
              mwaka++;
              }
              if(mwaka<1){
              System.out.println("hauyuko");
              }
              else{
              JOptionPane.showMessageDialog(null, "There exists a record  for "+namez+" for the year "+selected+". \n If its a subsequent pay, use the check status feature to update this record.", "Alert",JOptionPane.PLAIN_MESSAGE);
              yearco.setSelectedItem(" ");
              }
               
               }
               catch(Exception ee){
               System.out.println("getting year"+ee);
               
               }
             }
         });
     allpanel.add(yearco);

     //panel ya national
     
     nationo = new JPanel();
     nationo.setBounds(10, 130, 550, 70);
     nationo.setLayout(null);
     nationo.setBorder(tb1);  
     allpanel.add(nationo);
    
     Font checks = new Font("cambira", Font.PLAIN,15);
     nationale = new JCheckBox("National Fee");
     nationale.setBounds(10,20, 110, 35);
     nationale.addChangeListener(this);
     nationale.addActionListener(this);
     nationo.add(nationale);
     
     national = new JLabel("Amount:");
     national.setBounds(130, 20, 100, 35);
     national.setFont(new Font("cambira", Font.PLAIN, 15));
     nationo.add(national);
     
     nationaltxt = new JTextField();
     nationaltxt.setBounds(200, 20, 100, 25);
     nationaltxt.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent ke) {
             char cr=ke.getKeyChar();    
           
         if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 return;
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 return;
             }
         });
     nationaltxt.setEnabled(false);
     
     nationo.add(nationaltxt);
     
     balance =new JLabel("Balance");
     balance.setBounds(310, 20, 80, 25);
     balance.setFont(new Font("cambira", Font.PLAIN, 15));
    nationo.add(balance);
      
    bal1txt = new JTextField();
    bal1txt.setBounds(410, 20, 100, 25);
    bal1txt.setEnabled(false);
    nationo.add(bal1txt);
     
     //branch
     brnc = new JPanel();
     brnc.setBounds(10, 210, 550, 70);
     brnc.setLayout(null);
     brnc.setBorder(tb2);  
     allpanel.add(brnc);
    
     branche = new JCheckBox("Branch Fee");
     branche.setBounds(10,20, 110, 35);
     branche.addChangeListener(this);
     branche.addActionListener(this);
     brnc.add(branche);
     
     branch = new JLabel("Amount:");
     branch .setBounds(130, 20, 100, 35);
     branch .setFont(new Font("cambira", Font.PLAIN, 15));
     brnc.add(branch);
     
     branchtxt = new JTextField();
     branchtxt .setBounds(200, 20, 100, 25);
     branchtxt.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent ke) {
             char cr=ke.getKeyChar();    
           
         if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 return;
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 return;
             }
         });
     branchtxt .setEnabled(false);
     brnc.add(branchtxt );
     
     balance =new JLabel("Balance");
     balance.setBounds(310, 20, 80, 25);
     balance.setFont(new Font("cambira", Font.PLAIN, 15));
     brnc.add(balance);
      
     bal2txt = new JTextField();
     bal2txt.setBounds(410, 20, 100, 25);
     bal2txt.setEnabled(false);
     brnc.add(bal2txt);
     
     
     
     
     //AGM
     
     agmp = new JPanel();
     agmp.setBounds(10, 290, 550, 70);
     agmp.setLayout(null);
     agmp.setBorder(tb3);  
     allpanel.add(agmp);
    
     agme = new JCheckBox("AGM Fee");
     agme.setBounds(10,20, 110, 35);
     agme.addActionListener(this);
     agme.addChangeListener(this);
     agmp.add(agme);
     
     agm = new JLabel("Amount:");
     agm .setBounds(130, 20, 100, 35);
     agm .setFont(new Font("cambira", Font.PLAIN, 15));
     agmp.add(agm);
     
     agmtxt = new JTextField();
     agmtxt .setBounds(200, 20, 100, 25);
     agmtxt.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent ke) {
             char cr=ke.getKeyChar();    
           
         if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 return;
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 return;
             }
         });
     agmtxt .setEnabled(false);
     agmp.add(agmtxt );
     
     balance =new JLabel("Balance");
     balance.setBounds(310, 20, 80, 25);
     balance.setFont(new Font("cambira", Font.PLAIN, 15));
     agmp.add(balance);
      
     bal3txt = new JTextField();
     bal3txt.setBounds(410, 20, 100, 25);
     bal3txt.setEnabled(false);
     agmp.add(bal3txt);
     
     
     //welfare    
     welfar = new JPanel();
     welfar.setBounds(10, 370, 550, 70);
     welfar.setLayout(null); 
     welfar.setBorder(tb4);  
     allpanel.add(welfar);
    
     welfaree = new JCheckBox("Welfare fee");
     welfaree.setBounds(10,20, 110, 35);
     welfaree.addChangeListener(this);
     welfaree.addActionListener(this);
     welfar.add(welfaree);
     
     welfare = new JLabel("Amount:");
     welfare .setBounds(130, 20, 100, 35);
     welfare .setFont(new Font("cambira", Font.PLAIN, 15));
     welfar.add(welfare);
     
     welfaretxt = new JTextField();
     welfaretxt .setBounds(200, 20, 100, 25);
     welfaretxt.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent ke) {
             char cr=ke.getKeyChar();    
           
         if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 return;
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 return;
             }
         });
     welfaretxt .setEnabled(false);
     welfar.add(welfaretxt);
     
     balance =new JLabel("Balance");
     balance.setBounds(310, 20, 80, 25);
     balance.setFont(new Font("cambira", Font.PLAIN, 15));
     welfar.add(balance);
      
     bal4txt = new JTextField();
     bal4txt.setBounds(410, 20, 100, 25);
     bal4txt.setEnabled(false);
     welfar.add(bal4txt);
     
     cancel= new JButton("Cancel", null);
     cancel.setBounds(180, 490, 100, 25);
     cancel.addActionListener(this);
     allpanel.add(cancel);
     
     makepay= new JButton("Make Pay", null);
     makepay.setBounds(330, 490, 100, 25);
     makepay.addActionListener(this);
     allpanel.add(makepay);
     
     
     getContentPane().add(allpanel);
     setSize(600,600);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       boolean ready;
       Object pay = e.getSource();
       
       if(pay==confirm){
       try{
              int memba=0;
              Class.forName("com.mysql.jdbc.Driver");
              Connection cnect =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
              Statement confirm = cnect.createStatement();
              ResultSet payfor = confirm.executeQuery("Select names from members_table where k_p_a_id = '"+nambatxt.getText()+"'");
              
              while(payfor.next()){
              namez = payfor.getString("names");
              memba++;
              }
              if(memba==1){
              savedno = nambatxt.getText();
              confirmation.setText("Member is "+ namez);
              confirmation.setBackground(Color.orange);
              ako=1;
              }
              else{
              JOptionPane.showMessageDialog(this, "The number entered doesnt exist in the database", "Alert", JOptionPane.WARNING_MESSAGE);
              
              }
       }
       catch(Exception exp){
      System.out.println(exp);
       }        
       }
        else  if (pay==makepay)
         {
             if(ako==1){
                          String nat=nationaltxt.getText();
                          String bra = branchtxt.getText();
                          String  ag= agmtxt.getText();
                          String werfre = welfaretxt.getText();

                          System.out.println("ishafinywa");


                           if(nat.length()<=1 && bra.length()<=1 && ag.length()<=1)
                           {
                                  JOptionPane.showMessageDialog(null, "You must fill the money to be paid", "Alert", JOptionPane.WARNING_MESSAGE);   
                           }
                    else{
                   try{
                              Connection cnect =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
                              Statement confirm = cnect.createStatement();
                              int payy = confirm.executeUpdate("INSERT INTO finances VALUES('"+savedno+"','"+nat+"','"+bra+"','"+ag+"','"+werfre+"','"+yearco.getSelectedItem()+"')"); 

                              if(payy==1)
                              {JOptionPane.showMessageDialog(null, "Payment successfuly made", "SUCCESS", JOptionPane.PLAIN_MESSAGE,null);}
                             else
                              {JOptionPane.showMessageDialog(null, "Problem making payment", "ALERT", JOptionPane.WARNING_MESSAGE, null);}
                       }
                   catch(Exception ee){
                               System.out.print(ee);

                       }
                          }

              

              }
             else{
               JOptionPane.showMessageDialog(null, "You have not confirmed whether if the member exists or has paid");
             }
 
                  }
 
                  else{

                  }
  
       
    }
    
    
     public static void main(String[] args) {
    make_pay log= new make_pay();
    log.setVisible(true);
}

    @Override
    public void stateChanged(ChangeEvent evnt) {
        boolean nationalselected = nationale.isSelected();
        boolean branchselected  = branche.isSelected();
        boolean agmselected     = agme.isSelected();
        boolean welfareselected = welfaree.isSelected();
        
        
     if(nationalselected==true){
        nationaltxt.setEnabled(true);
        bal1txt.setEnabled(true);
        
        }
     else{
       nationaltxt.setEnabled(false);
       nationaltxt.setText(" ");
       bal1txt.setEnabled(false);
       bal1txt.setText(" ");
        }  
   
    if(branchselected==true){
        branchtxt.setEnabled(true);
        bal2txt.setEnabled(true);
        }
     else{
       branchtxt.setEnabled(false);
       branchtxt.setText(" ");
       bal2txt.setText(" ");
       bal2txt.setEnabled(false);

        }  
      if(agmselected==true){
        agmtxt.setEnabled(true);
        bal3txt.setEnabled(true);
        }
     else {
       agmtxt.setEnabled(false);
       agmtxt.setText(" ");
       bal3txt.setEnabled(false);
       bal3txt.setText(" ");
        }  
      if(welfareselected==true){
        welfaretxt.setEnabled(true);
        bal4txt.setEnabled(true);
        }
     else {
       welfaretxt.setEnabled(false);
       welfaretxt.setText(" ");
       bal4txt.setEnabled(false);
       bal4txt.setText(" ");
        }  
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
}
