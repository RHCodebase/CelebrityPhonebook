package org.dcu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil 
{
    public static Connection getConnection()
    {
    	    Connection connection = null;

            try 
            {    	          	
            	 String userName   =  "";
            	 String password   =  "";
            	 String url        =  "jdbc:mysql://rhrdsdbinstance.cmfxqpbjblje.eu-west-1.rds.amazonaws.com:3306/rhrdstestdb"; 
            	 
            	 // String url        =  "jdbc:mysql://aa66dkyl4fl98s.cmfxqpbjblje.eu-west-1.rds.amazonaws.com:3306/rhrdstestdb";
            	 // the db for the endpoint above has been deleted 
            	 
            	 // String url        =  "jdbc:mysql://aa1viyrwwvlycgx.cmfxqpbjblje.eu-west-1.rds.amazonaws.com:3306/rhrdstestdb";
            	 

                 Class.forName ("com.mysql.jdbc.Driver"); 
                 connection = DriverManager.getConnection (url, userName, password);
            } 
            catch (ClassNotFoundException e) 
            {
                e.printStackTrace();
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
            } 
            return connection;
    }
    //------------------------------------------------------------------------------------------------------------
}