package kpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;




public class dbconnection {
  private Connection conn;
  
    public dbconnection(){ } 
    
    public  Connection getconnection(){
        try{
       Class.forName("com.mysql.jdbc.Driver");
       conn =DriverManager.getConnection("jdbc:Mysql://localhost/kpa_db","root","root"); 
       Statement stt = conn.createStatement();

        }
        catch(Exception ee){
        
        }
        
        return conn;
    }
    
 }   

