/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import sun.awt.geom.Crossings;

/**
 *
 * @author jeremy
 */
public class kpa_home extends JFrame implements ActionListener,FocusListener {
    
    Container cont = this.getContentPane();
    private  JDesktopPane screen= new JDesktopPane();
    Border ro =BorderFactory.createLineBorder(Color.yellow);
    String user ="";
    
    
    
    private JMenuBar bar;
    private JToolBar tulbaar;
    private JMenu file,options,accounts,help;                                               //the menu options at the top
    private JMenuItem newmember,delmember,quitapp;                                          //file menuitems
    private JMenuItem makepay,enquire,members;                                              //accounts menuitems
    private JMenuItem aboutapp,shortcutkeys; 
    private JMenuItem configu;                                                               //options menuitems
    
    private java.util.Date currDate = new java.util.Date ();				    //Creating Object.
    private SimpleDateFormat sdf = new SimpleDateFormat ("hh:mm", Locale.getDefault());	    //Changing Format.
    private String date = sdf.format (currDate);	
    
    
    private  JPanel panel1;
    private JLabel clock,uza,status;
    Connection conne;
    
    public kpa_home(int clr,String username,Connection conn){                                  //int clr,String username,Connection conn
    
    super("K.P.A CENTRAL PROVINCE DATA RECORDS MANAGEMENT SYSTEM");
    
    //setting up size
    setSize(900,600);
    
    //setting location
    
    setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2,
               (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
    
    
     //setting admin icons
 ImageIcon newuser = new ImageIcon(getClass().getResource("images/nuser.jpg"));
 ImageIcon remove = new ImageIcon(getClass().getResource("images/duser1.png"));
 ImageIcon exit= new ImageIcon(getClass().getResource("images/exit.png"));
 ImageIcon about = new ImageIcon(getClass().getResource("images/abou.jpg"));
 ImageIcon key = new ImageIcon(getClass().getResource("images/key.jpg"));
 ImageIcon pay = new ImageIcon(getClass().getResource("images/payment.jpg"));
 ImageIcon userd = new ImageIcon(getClass().getResource("images/userd.png"));
 ImageIcon config = new ImageIcon(getClass().getResource("images/config.png"));

    
    
    
   
    //setting default closing event
    
    addWindowListener (new WindowAdapter () {                           //Attaching the WindowListener to Program.
			public void windowClosing (WindowEvent we) {	//Overriding the windowClosing Function.
				quitApp ();				//Call the Function to Perform the Closing Action.
			}
		}
		);
    
    bar = new JMenuBar();
    setJMenuBar(bar);
    
    
    
    file = new JMenu("File");
    file.setMnemonic('F');
    options = new JMenu("Options");
    options.setMnemonic('O');
    accounts =new JMenu("Accounts");
    accounts.setMnemonic('A');
    help = new JMenu("Help");
    help.setMnemonic('H');
    
    
    newmember =new JMenuItem("Add Member", newuser);
    newmember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,Event.CTRL_MASK));
    newmember.setBackground(Color.WHITE);
    newmember.setMnemonic('M');
    newmember.addActionListener(this);
    
    delmember = new JMenuItem("Delete Member", remove);
    delmember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,Event.CTRL_MASK));
    delmember.setMnemonic('D');
    delmember.setBackground(Color.WHITE);
    delmember.addActionListener(this);
    
    quitapp=new JMenuItem("Exit", exit);
    quitapp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.CTRL_MASK));
    quitapp.setMnemonic('x');
    quitapp.setBackground(Color.WHITE);
    quitapp.addActionListener(this);
    
    //options
    configu=new JMenuItem("Configurations", config);
    configu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK));
    configu.setMnemonic('g');
    configu.setBackground(Color.WHITE);
    configu.addActionListener(this);
    
    
    
    //accounts
    makepay =new JMenuItem("Make Payment", pay);
    makepay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,Event.CTRL_MASK));
    makepay.setMnemonic('P');
    makepay.setBackground(Color.white);
    makepay.addActionListener(this);
    
    enquire = new JMenuItem("Check Status", userd);
    enquire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK));
    enquire.setMnemonic('C');
    enquire.setBackground(Color.WHITE);
    enquire.addActionListener(this);
    
    //help
    aboutapp =new JMenuItem("About System", about);
    aboutapp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
    aboutapp.setMnemonic('S');
    aboutapp.setBackground(Color.WHITE);
    aboutapp.addActionListener(this);
 
    shortcutkeys =new JMenuItem("Shortcut Keys", key);
    shortcutkeys.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,Event.CTRL_MASK));
    shortcutkeys.setMnemonic('K');
    shortcutkeys.setBackground(Color.WHITE);
    shortcutkeys.addActionListener(this);
    
    
    
    bar.add(file);
    bar.add(options);
    bar.add(accounts);
    bar.add(help);
    
    file.add(newmember);
    file.addSeparator();
    file.add(delmember);
    file.addSeparator();
    file.add(quitapp);
    
    options.add(configu);
    
    accounts.add(makepay);
    accounts.add(enquire);
    
    help.add(aboutapp);
    help.add(shortcutkeys);
    
 
   panel1 = new JPanel();
   panel1.setLayout(new BorderLayout());
   
   conne =conn;
   user=username;
  
   
   uza = new JLabel("K.P.A Record Management System . Crafed by ZEECODERS", JLabel.LEFT);
   uza.setFont(new Font("candara", Font.BOLD, 14));
   status =new JLabel("Welcome Mr. "+user+ " you signed in at  "+date,Label.RIGHT);
   status.setFont(new Font("candara", Font.BOLD, 14));
   
   panel1.add(uza,BorderLayout.WEST);
   panel1.add(status,BorderLayout.EAST);
   
   cont.add(panel1, BorderLayout.SOUTH);
   cont.add(screen);
  
   
   setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent emain) {
       Object links = emain.getSource();

       
 if(links==quitapp){
               quitApp();
               }
 else if(links==newmember)
               {
                 boolean b=openChildWIndow("Add New Record");
                 if(b==false){
                 newmember numember= new newmember(conne);
                screen.add(numember);
                numember.setVisible(true);
              }
           }
               
else if(links==delmember){
                    boolean  b=openChildWIndow("Delete Member");
                     if(b==false){
                     try {
                               loaddel();
                   } catch (SQLException ex) {
                               Logger.getLogger(kpa_home.class.getName()).log(Level.SEVERE, null, ex);
                           }
                     }
         }

else if (links==configu){
               JFrame parent = (JFrame) getParent();
                JDialog Edit = new status_choice( parent,conne);
                Edit.show();


               }
else if(links == enquire){
                boolean b=openChildWIndow("Add New Record");
                 if(b==false){
                 newmember numember= new newmember(conne);
                screen.add(numember);
                numember.setVisible(true);
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
    
//    public static void main(String[] args){
//    kpa_home loghere=new kpa_home();
//    loghere.setVisible(true);
//    }
    
    private void quitApp () {

		try {
			//Show a Confirmation Dialog.
		        	int reply = JOptionPane.showConfirmDialog (this, 
				"Do you really want to exit the System?",
				"EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			//Check the User Selection.
			if (reply == JOptionPane.YES_OPTION) {				//If User's Choice Yes then.
				setVisible (false);					//Hide the Frame.
				dispose();            					//Free the System Resources.
				System.exit (0);        				//Close the Application.
			}
			else if (reply == JOptionPane.NO_OPTION) {			//If User's Choice No then.
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//Do Nothing Return to Program.
			}
		} 

		catch (Exception e) {}

	}
    
    
    
    protected void loaddel() throws SQLException{
     
        delrcord delete= new delrcord();
         screen.add(delete);
         delete.setVisible(true);

    }
    
        private boolean openChildWIndow(String title) {         
        JInternalFrame [] child = screen.getAllFrames();
        for(int i=0; i<child.length; i++){
        if(child[i].getTitle().equalsIgnoreCase(title)){
        child[i].show();
        return true;
        }
        }
        return false;
     
    }
    
    
    
    
    
    
    
    
}

