package com.util; 
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection myConnection = null;
    private static String myURL=myURL="jdbc:mysql://localhost:3306/lab7";
    
    DBConnection(){
    }
    
    public static Connection getConnection() throws ClassNotFoundException{
        if (myConnection != null){
            return myConnection;
        }
        else 
            try{
                //establish and open mysql database connection
                Class.forName("com.mysql.jdbc.Driver");
                myConnection = DriverManager.getConnection(myURL, "root", "admin");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        return myConnection;
    }
    
    public void closeConnection() throws ClassNotFoundException{
        try{
            myConnection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
