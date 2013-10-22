
package kpa;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class chekztatus2  extends JFrame implements Action{    
  
private JScrollPane moneyrecords = new JScrollPane();
private Connection con;

private JTable Jmoneyrecord;

private JFrame parent;


private JLabel picture, name1, name ,contact1,contact,enter,staatuz,label1;

private JTextField enteredtxt;
private TitledBorder bodaa;

private String Content [] [];
private String ColumnHeaderName[] = {"Year","National Fee","Branch Fee","Agm Fee","Welfare Fee"};
private String sqlstr;
private String teext, jiina,simu;
private int total;

private Statement state;
private ResultSet rss;
public static int rowNum = 0;


private JPanel profile,yearlydeatils;   
Font fromdb = new Font("candara", Font.PLAIN, 15);
Font fonti = new Font("Cambira", Font.BOLD, 18);
private JButton check, update;

public chekztatus2 (){
    
    super ("Status");
    
    bodaa = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Profile");
   
    parent = (JFrame) this.getParent();
   
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
    
     
    name = new JLabel();
    name.setFont(fromdb);
    name.setBounds(300, 30, 150, 25);
    name.setOpaque(true);
    
    name.setBackground(Color.white);
    profile.add(name);
    
    contact1  = new JLabel("Phone :");
    contact1. setBounds(220,70, 80, 25);
    contact1.setFont(fonti);
    profile.add(contact1);
    
     
    contact = new JLabel();
    contact.setBounds(300, 70, 150, 25);
    contact.setFont(fromdb);
    contact.setOpaque(true);
    contact.setBackground(Color.white);
    profile.add(contact);
    
 
   
    moneyrecords.setBounds(10, 250, 600, 200);
    holder.add(moneyrecords);
    
    update = new JButton("Update");
    update.setBounds(220, 460, 200, 30);
    update.addActionListener(this);
    holder.add(update);
    
    


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
         
    //if not filled
         if(enteredtxt.getText().equals("")){
         JOptionPane.showMessageDialog(null, "No number entered");
         }
         
    //if number is more than 5 digits
         else if(  enteredtxt.getText().length() >= 5 || enteredtxt.getText().length() < 4){
         JOptionPane.showMessageDialog(null, "Wrong Number Format");
         }
         
   //when the number is okay
     else{
             
    dbconnection checkcl = new dbconnection();
    con = checkcl.getconnection();
           
  

    try{
        try{
     Statement statement = con.createStatement();
     String membaas = "select names, phone_no from members_table where k_p_a_id = '"+enteredtxt.getText()+"'";
     ResultSet rzz = statement.executeQuery(membaas);
     
     int mtu = 0;
     while(rzz.next())
     {mtu++;
     jiina = rzz.getString("names");  
     simu = rzz.getString("phone_no");
     
     
     if(mtu == 1)     
      {
       name.setText(jiina);
       contact.setText("0"+simu);    
       createtable();
      }
     else{
       System.out.println("Member not found");      
          }
     }
        }
     catch(SQLException pp){
     System.out.print(pp);
     }  

    }
 catch(NullPointerException ee)
 {
   ee.printStackTrace();
   System.out.println();
 }

    }
  
     
   }
else if(obj== update){
				if(total != 0){
					try{
                                if(Jmoneyrecord.getValueAt(Jmoneyrecord.getSelectedRow(),Jmoneyrecord.getSelectedColumn()) != null){
								JDialog updet = new updet(parent,con,"SELECT * FROM finances WHERE member ="+teext+" AND year = " + Jmoneyrecord.getValueAt(Jmoneyrecord.getSelectedRow(),0));
								updet.show();
							}

					}catch(Exception sqlE){
						if(sqlE.getMessage() != null){
							System.out.println(sqlE.getMessage());
						}
					}
				}

}
        
}

  

    public static  void main(String args [] ){
    chekztatus2 chek = new chekztatus2();
    chek.show();
    
    }

    
   private  JTable createtable(){
    try{
       sqlstr = "Select * from finances where member = '"+enteredtxt.getText()+"'";
       System.out.print(enteredtxt.getText());
       state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);       
       rss = state.executeQuery(sqlstr);
       
       total = 0;
        
        //Move to the last record
        rss.afterLast();
        
        //Get the current record position
        if(rss.previous()) {
              total = rss.getRow();
          }
        
        //Move back to the first record;
        rss.beforeFirst();  
        
        if(total > 0){
            Content = new String[total][5];
            while(rss.next()){
                    teext = rss.getString("member");
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
 catch(Exception ee){
  System.out.print(ee);  } 
   
 JTable NewTable = new JTable (Content, ColumnHeaderName) {
     
			public boolean isCellEditable (int iRows, int iCols) {
				return false;
			}
		};

		NewTable.setPreferredScrollableViewportSize(new Dimension(720, 220));
		NewTable.setBackground(Color.orange);

		//Start resize the table column
		NewTable.getColumnModel().getColumn(0).setMaxWidth(100);
		NewTable.getColumnModel().getColumn(0).setMinWidth(0);
		NewTable.getColumnModel().getColumn(0).setWidth(0);
		NewTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		NewTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		NewTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		NewTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                NewTable.setRowHeight(20);
		//End resize the table column

		//Disposed variables
		ColumnHeaderName=null;
		Content=null;

		rowNum = 0;    
                
                
          Jmoneyrecord = NewTable;
          moneyrecords.getViewport().add(Jmoneyrecord);
          
   
    return null;
   } 
   
   
   	public  void reloadRecord(){
                                
				moneyrecords.getViewport().remove(Jmoneyrecord);
				Jmoneyrecord=createtable();
				moneyrecords.getViewport().add(Jmoneyrecord);
				moneyrecords.repaint();
	}
   
    }

    

