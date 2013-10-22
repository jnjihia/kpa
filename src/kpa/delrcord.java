/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class delrcord  extends JInternalFrame implements ActionListener {

  	public static JScrollPane CusTableJSP = new JScrollPane();

	public static JPanel JPContainer= new JPanel();
        
        public JLabel kpano;

	public static JTable JTCusTable;
        
        
        
	JLabel JLPicture1 = new JLabel(new ImageIcon(getClass().getResource("images/adm.png")));
	JLabel JLHelpText = new JLabel("To display the member you want to delete,fill in the kpa number and search the record");
       

	JFrame JFParentFrame;

        ImageIcon chap = new ImageIcon(getClass().getResource("images/NEW.PNG"));
        
      
	JButton JBSearch = new JButton("Search",new ImageIcon("src/kpa/images/search.png"));
	JButton JBDelete = new JButton("Delete",new ImageIcon("src//kpa/images/delete.png"));
	JButton JBReload = new JButton("Reload",new ImageIcon("src/kpa/images/reload.png"));

        

	public static Statement stCus;
        public static Connection conne;

	public static ResultSet rsCus;

	public  static String strSQL ="SELECT names,k_p_a_id,phone_no,reg_date FROM members_table ORDER BY k_p_a_id ASC";
        
        public  JTextField enterdtxt;    
        public static int rowNum = 0;
        public static int total = 0;
        
        
        
        
	public static String Content[][];

	Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
        
    @SuppressWarnings("LeakingThisInConstructor")
        public delrcord() throws SQLException {
        super("Delete Member",false,true,false,true);
         JPContainer.setLayout(null);


                JLPicture1.setBounds(5,5,48,48);
		JPContainer.add(JLPicture1);
                
		//-- Add the JLHelpText
		JLHelpText.setBounds(55,5,727,48);
		JLHelpText.setFont(new Font("Candara", Font.BOLD, 13));
                JLHelpText.setOpaque(true);
                JLHelpText.setBackground(Color.WHITE);
		JPContainer.add(JLHelpText);

		//-- Add the CusTable
		JTCusTable=CreateTable();
		CusTableJSP.getViewport().add(JTCusTable);
		CusTableJSP.setBounds(5,100,727,160);
		JPContainer.add(CusTableJSP);

               //search place
               kpano = new JLabel("Enter KPA No."); 
               kpano.setBounds(120,60, 100, 25);
               kpano.setFont(new Font("Candara", Font.BOLD, 14));
               JPContainer.add(kpano);
               
               enterdtxt = new JTextField();
               enterdtxt.setBounds(250, 60, 100, 25);
               JPContainer.add(enterdtxt);
               
                
		//-- Add the JBSearch
		JBSearch.setBounds(370,60,99,25);
		JBSearch.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBSearch.setMnemonic(KeyEvent.VK_S);
		JBSearch.addActionListener(this);
		JPContainer.add(JBSearch);


		//-- Add the JBDelete
		JBDelete.setBounds(250,282,105,25);
		JBDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBDelete.setMnemonic(KeyEvent.VK_D);
		JBDelete.addActionListener(this);
		JPContainer.add(JBDelete);

		//-- Add the JBReload
		JBReload.setBounds(450,282,105,25);
		JBReload.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBReload.setMnemonic(KeyEvent.VK_R);
		JBReload.addActionListener(this);
		JPContainer.add(JBReload);

		//End initialize variables

		//Start set the form properties
		getContentPane().add(JPContainer);
		setSize(747,350);
		setLocation((((screen.width - 747)/2)-30),((screen.height-450)/2)-45);
                 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        }
  

    
    
//  public static void main(String[] args) throws SQLException {
//    delrcord log= new delrcord();
//    log.setVisible(true);
//}

    private  static JTable CreateTable() {
   String ColumnHeaderName[] = {"Names","KPA No.","Phone No.","Reg Date"};
      
          try{
       Class.forName("com.mysql.jdbc.Driver");
       Connection conne =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root");
       stCus = conne.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);       
       rsCus = stCus.executeQuery(strSQL);
       
        total = 0;
        
        //Move to the last record
        rsCus.afterLast();
        
        //Get the current record position
        if(rsCus.previous())total = rsCus.getRow();
        
        //Move back to the first record;
        rsCus.beforeFirst();  
        
        if(total > 0){
            Content = new String[total][4];
            while(rsCus.next()){
                    Content[rowNum][0] = "" + rsCus.getString("names");
                    Content[rowNum][1] = "" + rsCus.getString("k_p_a_id");
                    Content[rowNum][2] = "0" + rsCus.getString("phone_no");      //
                    Content[rowNum][3] = "" + rsCus.getString("reg_date");
                    rowNum++;
            }
    }else{
            Content = new String[0][4];
            Content[0][0] = " ";
            Content[0][1] = " ";
            Content[0][2] = " ";
            Content[0][3] = " ";
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
		NewTable.getColumnModel().getColumn(0).setMaxWidth(200);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object obb=e.getSource();
       if(obb==JBDelete){
	if(total != 0){
					try{
						if(JTCusTable.getValueAt(JTCusTable.getSelectedRow(),JTCusTable.getSelectedColumn()) != null){
							String ObjButtons[] = {"Yes","No"};
							int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to delete the selected record?","Delete Record",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,ObjButtons,ObjButtons[1]);
							if(PromptResult==0){
                                                                String del="DELETE  FROM members_table WHERE k_p_a_id = "+ JTCusTable.getValueAt(JTCusTable.getSelectedRow(),1);
                                                                System.out.println(del);
								stCus.execute(del);
								reloadRecord();
								JOptionPane.showMessageDialog(null,"Record has been successfully deleted.","Comfirm Delete",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}catch(Exception sqlE){
						if(sqlE.getMessage()!=null){
							JOptionPane.showMessageDialog(null,"Please select a record in the list to deleted.","No Record Selected",JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null,"Please select a record in the list to deleted.","No Record Selected",JOptionPane.INFORMATION_MESSAGE);
						}
					}
        
                    }
       }
      else if(obb==JBReload)
      {reloadRecord();}
        //Close the Form
                            
    }
    public static void reloadRecord(String srcSQL){
			strSQL = srcSQL;
			CusTableJSP.getViewport().remove(JTCusTable);
			JTCusTable=CreateTable();
			CusTableJSP.getViewport().add(JTCusTable);
			JPContainer.repaint();
	}
	public static void reloadRecord(){
                                
				CusTableJSP.getViewport().remove(JTCusTable);
				JTCusTable=CreateTable();
				CusTableJSP.getViewport().add(JTCusTable);
				JPContainer.repaint();
	}

    
}
