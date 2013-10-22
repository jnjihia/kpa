/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpa;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jeremy
 */
public class status_choice  extends JDialog{
   
JTextField memberno = new JTextField();

private String jina,simu;


Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();

JButton viewStatus = new JButton("View Status");
JLabel EnterNo = new JLabel("Enter Number");
JTextField wekahapa = new JTextField();

Connection cone;
Statement check;
ResultSet jibu;

public status_choice(JFrame parent,Connection dbcon){
    
super(parent);
    
JPanel JPContainer = new JPanel();
JPContainer.setLayout(null);

EnterNo.setBounds(10, 5, 100, 25);
EnterNo.setFont(new Font("candara", Font.BOLD, 14));
JPContainer.add(EnterNo);

wekahapa.setBounds(10, 35, 200, 25);
wekahapa.addKeyListener(new KeyAdapter() {
        @Override
         public void keyTyped(KeyEvent e) {
                nambatxtKeyTyped(e);
            }
});

JPContainer.add(wekahapa);

viewStatus.setBounds(40, 70, 150, 25);
viewStatus.setFont(new Font("candara", Font.BOLD, 14));
viewStatus.addActionListener(acshon);
viewStatus.setActionCommand("view");
JPContainer.add(viewStatus);



cone =dbcon;
getContentPane().add(JPContainer);
setSize(230,200);
setResizable(false);
setLocation((screen.width - 325)/2,((screen.height-383)/2));

}
  ActionListener acshon = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent acce) {
            
        String act = acce.getActionCommand();
        
        if( "view".equals(act)){
            
           
            
               String yy = wekahapa.getText().toString();
                 if(RequiredFieldEmpty()==false){
                 if(formatnumber()==false){
                     
                 String membaas = "select names, phone_no from members_table where k_p_a_id = '"+yy+"'";  
                            try{   
                             check = cone.createStatement();   
                             jibu = check.executeQuery(membaas);

                             int mtu = 0;

                         while(jibu.next())
                         {
                          jina = jibu.getString("names");  
                          simu = jibu.getString("phone_no");
                          
                             
                             mtu++;
                         }  
                         System.out.println(yy);
                         //JOptionPane.showMessageDialog(null, "That entered number doesn't exist on the datatbase");
                          if(mtu == 1)     
                          {
                           
                           check_status check = new check_status(jina, simu, yy);
                           check.setVisible(true);

                          }

                          else{
                           JOptionPane.showMessageDialog(null, "Member Number doesnt exist in the database \n please make sure that the member is registered");
                          }



                              }
                            catch(Exception exzz){

                            System.out.println(exzz );
                            }
                          }
                 
                 
                 }
//        
//                 else if(yy.length()>6){
//                            JOptionPane.showMessageDialog(null, "That please check the format you entered if its correct");
//
//                            }

//                  else{

                }
                    
        }
    };   
 
 private void nambatxtKeyTyped(KeyEvent ke) {
        char cr=ke.getKeyChar();  
       if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

    }
 	private boolean RequiredFieldEmpty(){
		if(wekahapa.getText().equals("") ){
			JOptionPane.showMessageDialog(null,"please fill the number.","Alert",JOptionPane.WARNING_MESSAGE);
			wekahapa.requestFocus();
			return true;
		}else{
			return false;
		}
	}
         	private boolean formatnumber(){
		if(wekahapa.getText().length()>5 ){
			JOptionPane.showMessageDialog(null,"The input number format is incorrect","Alert",JOptionPane.WARNING_MESSAGE);
			wekahapa.requestFocus();
			return true;
		}else{
			return false;
		}
	}
    
    
}
