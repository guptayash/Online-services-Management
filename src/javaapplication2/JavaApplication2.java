/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.sql.*;

/**
 *
 * @author Yash
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn=null;
        try {
            String driverName="oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            String serverName="myGlobe";
            String serverPort ="1521";
            String sid="XE";
            String url="jdbc:oracle:thin:@" + serverName + ":" +serverPort + ":" + sid;
            String username="DBMS";
            String password="DBMS";
            conn=DriverManager.getConnection(url,username,password);
            System.out.println("Successfully Connected to the database");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " +e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
            
        }
        
    }
    
}
