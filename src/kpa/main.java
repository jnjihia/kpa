package kpa;

import javax.swing.UIManager;

public class main {
    
    public static void main(String args []){
               
            try {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
              }
             }
                } catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
                }
    login start = new login();
    start.setVisible(true);
    
    }
    
    
}
