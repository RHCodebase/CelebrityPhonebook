package org.dcu;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

@WebServlet("/UploadNewStaffDetails")
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB
public class UploadNewStaffDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Connection conn;
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{       
		       String req_staffTitle    =   request.getParameter("staffTitle");
		       String req_staffName     =   request.getParameter("staffName");
		       String req_department    =   request.getParameter("department");
		      
		       String req_staffrole     =   request.getParameter("staffRole");
		       String req_phoneNumber   =   request.getParameter("phoneNumber");
		       String req_emailAddress  =   request.getParameter("emailAddress");
		       String req_room          =   request.getParameter("room");
		       
		       InputStream inputStreamImage = null;
			
			   //   obtains the upload file part in this multipart request
			   Part photofilePart = request.getPart("photo");
			   
			   if (photofilePart != null)
			   {
				            // System.out.println("photofilePart is not null ");
		       //           debug messages
			   	 	        // System.out.println("photofilePart.getName()        is "+photofilePart.getName());
			   		        // System.out.println("photofilePart.getSize()        is "+photofilePart.getSize());
			    		    // System.out.println("photofilePart.getContentType() is "+photofilePart.getContentType());			    
			    		    // System.out.println("photofilePart.getHeaderNames() is "+photofilePart.getHeaderNames());
			   		      

			 		        // obtains input stream of the upload file
			 		        inputStreamImage = photofilePart.getInputStream();
				}
			    else
			    {
			    	        System.out.println("photofilePart is null ");
			    }
		       
		        // System.out.println("req_staffTitle   is  "+req_staffTitle);
		        // System.out.println("req_staffName    is  "+req_staffName);
		        // System.out.println("req_department   is  "+req_department);
		       
		        // System.out.println("req_staffrole    is  "+req_staffrole);
		        // System.out.println("req_phoneNumber  is  "+req_phoneNumber);
		        // System.out.println("req_emailAddress is  "+req_emailAddress);
		        // System.out.println("req_room         is  "+req_room);
		       
		       conn = DbUtil.getConnection();
		      
		     
		       String message = null;             // message will be sent back to client
		       try
		       {  
					// constructs SQL statement
		    	    String sql = "INSERT INTO staffdetails ( staffTitle, staffName, department, role, phoneNumber, emailAddress, room, staffPhoto) values (?, ?, ?, ?, ?, ?, ?, ?)";
				
		    	    PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, req_staffTitle );
					statement.setString(2, req_staffName);
					statement.setString(3, req_department);
					statement.setString(4, req_staffrole);
					statement.setString(5, req_phoneNumber);
					statement.setString(6, req_emailAddress);
					statement.setString(7, req_room);

				    if (inputStreamImage != null) 
				    {				    	
				    	 if (photofilePart.getSize() > 0)
            		     {
				              System.out.println("inputStreamImage  is  not null ");
						      // fetches input stream of the upload file for the image column
				 		      statement.setBlob(8, inputStreamImage);
					     }
				         else
				         {
				    	     System.out.println("inputStreamImage  is null, so use the No Image Available png file"); 
				    	     InputStream inputStreamNoImageAvailable = DbMethods.getNoImageAvailableInputStream();
				    	     statement.setBlob(8, inputStreamNoImageAvailable);
				         }				    	
				    }
					// sends the statement to the database server
					int row = statement.executeUpdate();
					if (row > 0) 
					{
						message =  "<br><br><b>The details for "+req_staffTitle +" "+req_staffName + " have now been successfully added to the Phonebook database</b><br>";
					
					}
					else
					{
						message = "<br><br><b>The details for "+req_staffTitle +" "+req_staffName + " have NOT been added to the Phonebook database</b><br>";
					}
					conn.close();
				} 
		       catch (SQLException ex)
		       {
					message = "ERROR: " + ex.getMessage();
					ex.printStackTrace();
			   }
		       
		       request.setAttribute("Message", message);

			   getServletContext().getRequestDispatcher("/serverMessage.jsp").include(request, response);
	}
}
/*
 * 
CREATE TABLE IF NOT EXISTS NoImageAvailableTable
(
      `staffId`         int(11)       NOT NULL AUTO_INCREMENT,
      `staffPhoto`      longblob      DEFAULT NULL,
       PRIMARY KEY (`staffId`)
)  

INSERT INTO NoImageAvailableTable ( staffPhoto ) values  ("null");    then I later upload the NoImageAvailable.png file using MySQL Workbench
 
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
