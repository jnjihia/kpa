/*
 * Created by JFormDesigner on Sat Sep 07 00:00:09 EAT 2013
 */

package kpa;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 * @author Jeremy Njihia
 */
public class updet extends JDialog {
Connection Cnnct;
String StrQry;
Statement sttmn;
ResultSet rset;


    public updet(JFrame parentt,Connection Conne,String Sqlqry){                                         
        super();
        initComponents();
        Cnnct = Conne;
        Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
       
   try{
	sttmn = Cnnct.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
     }catch( SQLException sqlEx){
			System.out.println("\nERROR IN frm_add_edit_Student(frm_add_edit_Student):" + sqlEx + "\n");
     }
   try{
				rset = sttmn.executeQuery(Sqlqry);
				rset.next();					
					yeartxt.setText("" + rset.getString("year"));
					natfeetext.setText("" + rset.getString("national_fee"));
					branchfeetxt.setText("" + rset.getString("branch_fee"));
					agmfeetxt.setText ("" + rset.getString("agm_fee"));	
					welfaretxt.setText("" + rset.getString("welfare"));
					
			}catch(SQLException sqlEx){
				System.out.println(sqlEx.getMessage());
			}
        
            setLocation((screen.width - 650)/2,((screen.height-550)/2));
    
    }


    private void natfeetextKeyTyped(KeyEvent e) {
        // TODO add your code here
        char cr=e.getKeyChar();         
         if (!( Character.isDigit(e.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            e.consume();
            JOptionPane.showMessageDialog(this, "Numbers Only");
             }
    }

    private void yeartxtKeyTyped(KeyEvent e) {
        // TODO add your code here
                char cr=e.getKeyChar();         
         if (!( Character.isDigit(e.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            e.consume();
             }
    }
    
    
    
    
    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jeremy Njihia
        yearlabel = new JLabel();
        yeartxt = new JTextField();
        Natfeelabel = new JLabel();
        natfeetext = new JTextField();
        branchfeelabel = new JLabel();
        branchfeetxt = new JTextField();
        agmfeelabel = new JLabel();
        agmfeetxt = new JTextField();
        label3 = new JLabel();
        welfaretxt = new JTextField();
        update = new JButton();

        //======== this ========
        setTitle("Update Record");
        setResizable(false);
        setAlwaysOnTop(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- yearlabel ----
        yearlabel.setText("Year");
        yearlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(yearlabel);
        yearlabel.setBounds(30, 30, 60, 30);

        //---- yeartxt ----
        yeartxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                yeartxtKeyTyped(e);
            }
        });
        contentPane.add(yeartxt);
        yeartxt.setBounds(125, 35, 85, yeartxt.getPreferredSize().height);

        //---- Natfeelabel ----
        Natfeelabel.setText("National Fee");
        Natfeelabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(Natfeelabel);
        Natfeelabel.setBounds(10, 70, 85, 25);

        //---- natfeetext ----
        natfeetext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                natfeetextKeyTyped(e);
            }
        });
        contentPane.add(natfeetext);
        natfeetext.setBounds(125, 70, 85, natfeetext.getPreferredSize().height);

        //---- branchfeelabel ----
        branchfeelabel.setText("Branch Fee");
        branchfeelabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(branchfeelabel);
        branchfeelabel.setBounds(10, 105, 75, 19);

        //---- branchfeetxt ----
        branchfeetxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                natfeetextKeyTyped(e);
            }
        });
        contentPane.add(branchfeetxt);
        branchfeetxt.setBounds(125, 100, 85, branchfeetxt.getPreferredSize().height);

        //---- agmfeelabel ----
        agmfeelabel.setText("AGM Fee");
        agmfeelabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(agmfeelabel);
        agmfeelabel.setBounds(20, 135, 60, 19);

        //---- agmfeetxt ----
        agmfeetxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                natfeetextKeyTyped(e);
            }
        });
        contentPane.add(agmfeetxt);
        agmfeetxt.setBounds(125, 130, 85, agmfeetxt.getPreferredSize().height);

        //---- label3 ----
        label3.setText("Welfare Fee");
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(label3);
        label3.setBounds(10, 165, 75, 20);

        //---- welfaretxt ----
        welfaretxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                natfeetextKeyTyped(e);
            }
        });
        contentPane.add(welfaretxt);
        welfaretxt.setBounds(125, 160, 85, welfaretxt.getPreferredSize().height);

        //---- update ----
        update.setText("UPDATE");
        update.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(update);
        update.setBounds(95, 215, 85, 28);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(290, 320);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jeremy Njihia
    private JLabel yearlabel;
    private JTextField yeartxt;
    private JLabel Natfeelabel;
    private JTextField natfeetext;
    private JLabel branchfeelabel;
    private JTextField branchfeetxt;
    private JLabel agmfeelabel;
    private JTextField agmfeetxt;
    private JLabel label3;
    private JTextField welfaretxt;
    private JButton update;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



}
 