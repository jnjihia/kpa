
package kpa;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class check_status  extends JFrame implements Action{    
  
private JScrollPane moneyrecords = new JScrollPane();

private JTable Jmoneyrecord;


private JLabel picture, name1, name ,contact1,contact,enter,staatuz,label1;

private JTextField enteredtxt;
private TitledBorder bodaa;

private String Content [] [];
private String sqlstr;
private String teext, jiina,naamba;
private int total;

private Statement state;
private ResultSet rss;

 public static int rowNum = 0;


private JPanel profile,yearlydeatils;   
Font fonti = new Font("Cambira", Font.BOLD, 18);
private JButton check;

public check_status(String naame, String phoone, String namba){
    
    super ("Status");
    teext =namba;
    jiina = naame;
    naamba = phoone;
    bodaa = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Profile");
    sqlstr = "Select * from finances where member = '"+teext+"'";
   
    setSize (650, 550);
    Container holder= getContentPane();
    holder.setLayout(null);
    
    
    label1 = new JLabel("Enter the Members Number");
    label1.setBounds(10, 10, 200, 30);
    holder.add(label1);
    
    enteredtxt  = new JTextField();
    enteredtxt.setBounds(220, 10, 100, 25);
    enteredtxt.addKeyListener(new KeyAdapter() {

        @Override
         public void keyTyped(KeyEvent e) {
                nambatxtKeyTyped(e);
            }

    });
    teext = enteredtxt.getText();
    holder.add(enteredtxt);
    
   
    
    check = new JButton( "Check",null);
    check.setBounds(350, 10, 100, 25);
    check.addActionListener(this);
    holder.add(check);
    
    profile = new JPanel(null);
    profile.setBounds(10, 40, 600, 210);
    profile.setBorder(bodaa);
    holder.add(profile);
    
    picture = new JLabel( new ImageIcon(getClass().getResource("images/admin.png")));
    picture.setBounds(5, 10, 160, 200);
    profile.add(picture);
    
    name1  = new JLabel("Name: ");
    name1. setBounds(220, 30, 80, 25);
    name1.setFont(fonti);
    profile.add(name1);
    
     
    name = new JLabel(jiina);
    name.setBounds(300, 30, 150, 25);
    name.setOpaque(true);
    name.setBackground(Color.LIGHT_GRAY);
    profile.add(name);
    
    contact1  = new JLabel("Phone :");
    contact1. setBounds(220,70, 80, 25);
    contact1.setFont(fonti);
    profile.add(contact1);
    
     
    contact = new JLabel(naamba);
    contact.setBounds(300, 70, 150, 25);
    contact.setOpaque(true);
    contact.setBackground(Color.LIGHT_GRAY);
    profile.add(contact);
    
    Jmoneyrecord = createmeza();
    moneyrecords.getViewport().add(Jmoneyrecord);
    moneyrecords.setBounds(10, 250, 600, 200);
    holder.add(moneyrecords);
    
    
    
    
    
    
    
    
    
    setLocation((((Toolkit.getDefaultToolkit().getScreenSize().width  -getWidth()) / 2/3)),(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2/3);
    setDefaultCloseOperation(EXIT_ON_CLOSE);


}


private void nambatxtKeyTyped(KeyEvent ke) {
        char cr=ke.getKeyChar();  
       if (!( Character.isDigit(ke.getKeyChar()) || (cr == KeyEvent.VK_BACK_SPACE)||(cr == KeyEvent.VK_ENTER))){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Please enter digits only");
             }

    }

    @Override
    public Object getValue(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putValue(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent vie) {
     Object obj =  vie.getSource();
     
     if (obj == check){
         try{
     Class.forName("com.mysql.jdbc.Driver");
     Connection conne =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
     Statement stt = conne.createStatement();
     String yy = enteredtxt.getText();
     String membaas = "select names, phone_no from members_table where k_p_a_id = '"+yy+"'";
     ResultSet rzz = stt.executeQuery(membaas);
     
     int mtu = 0;
     
     while(rzz.next())
     {mtu++;}  
     System.out.println(yy);
      if(mtu == 1)     
      {
       
  
      
      }
      else{
      
      }
         }
         catch(Exception ee){
         
         }
     
     
     }
        
    }

    private JTable createmeza() {
String ColumnHeaderName[] = {"Year","National Fee","Branch Fee","Agm Fee","Welfare Fee"};
      
          try{
       Class.forName("com.mysql.jdbc.Driver");
       Connection conne =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
       state = conne.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);       
       rss = state.executeQuery(sqlstr);
       
        total = 0;
        
        //Move to the last record
        rss.afterLast();
        
        //Get the current record position
        if(rss.previous())total = rss.getRow();
        
        //Move back to the first record;
        rss.beforeFirst();  
        
        if(total > 0){
            Content = new String[total][5];
            while(rss.next()){
                    Content[rowNum][0] = "" + rss.getString("year");
                    Content[rowNum][1] = "" + rss.getString("national_fee");
                    Content[rowNum][2] = "" + rss.getString("branch_fee");      //
                    Content[rowNum][3] = "" + rss.getString("agm_fee");
                    Content[rowNum][4] = "" + rss.getString("welfare");
                    rowNum++;
            }
    }else{
            Content = new String[0][5];
            Content[0][0] = " ";
            Content[0][1] = " ";
            Content[0][2] = " ";
            Content[0][3] = " ";
            Content[0][4] = " ";
    }




       }
       catch(Exception ee)
       {
       System.out.println(ee);
       }
   
   JTable NewTable = new JTable (Content, ColumnHeaderName) {
			public boolean isCellEditable (int iRows, int iCols) {
				return false;
			}
		};

		NewTable.setPreferredScrollableViewportSize(new Dimension(720, 220));
		NewTable.setBackground(Color.lightGray);

		//Start resize the table column
		NewTable.getColumnModel().getColumn(0).setMaxWidth(100);
		NewTable.getColumnModel().getColumn(0).setMinWidth(0);
		NewTable.getColumnModel().getColumn(0).setWidth(0);
		NewTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		NewTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		NewTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		NewTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		//End resize the table column

		//Disposed variables
		ColumnHeaderName=null;
		Content=null;

		rowNum = 0;

		return NewTable;     
    }

    
    }

    
