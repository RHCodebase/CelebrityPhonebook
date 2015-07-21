package org.dcu;

import java.sql.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayStaffImageServlet")
public class DisplayStaffImageServlet extends HttpServlet
{
  	   private static final long serialVersionUID = 1L;
      
       public DisplayStaffImageServlet()
       {
              super();  
       }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
		       String id = request.getParameter("id");
		       Connection conn;
		      
		       try
		       {					  					 
					   conn = DbUtil.getConnection();
					   // System.out.println("Connection Successful..... creating statement....");
					   Statement stmt = conn.createStatement();
					   
					   stmt.executeQuery("SELECT staffPhoto FROM staffdetails where staffId="+id);
					   ResultSet rs = stmt.getResultSet();
		
				   
					   if (rs.next())
					   {         
					          try
					          {  								   
						        	 Blob bl = rs.getBlob("staffPhoto");
								     byte[] pict = bl.getBytes(1,(int)bl.length());
								     
								     response.setContentType ("image/jpeg");    // make sure not to use jpg
								     OutputStream o = response.getOutputStream();
								     o.write(pict);
								     o.flush();
								     o.close();  
							    }
							    catch (Exception e)
							    {
							              e.printStackTrace();							             
							    }
							    finally
							    {
							          
							    }  
	                     }
					     conn.close();
                 }
                 catch (SQLException e) 
                 {
                       e.printStackTrace();
                 } 
	     }

}
/*
CREATE TABLE IF NOT EXISTS `staffdetails`
(
      `staffId`         int(11)       NOT NULL AUTO_INCREMENT,
      `staffTitle`      varchar(10)   DEFAULT NULL,
      `staffName`       varchar(30)   DEFAULT NULL,
      `department`      varchar(30)   DEFAULT NULL,
      `role`            varchar(30)   DEFAULT NULL,
      `phoneNumber`     varchar(30)   DEFAULT NULL,
      `emailAddress`    varchar(30)   DEFAULT NULL,
      `room`            varchar(30)   DEFAULT NULL,
      `staffPhoto`      longblob      DEFAULT NULL,
       PRIMARY KEY (`staffId`)
)  
*/
