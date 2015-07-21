package org.dcu;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadEditedStaffDetails")
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB
public class UploadEditedStaffDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{       
		       String req_previousStaffId    =   request.getParameter("previousStaffId"); 
		       //System.out.println("req_previousStaffId is "+req_previousStaffId);
		       
		   
		       String req_staffTitle         =   request.getParameter("staffTitle");
		       String req_staffName          =   request.getParameter("staffName");
		       String req_department         =   request.getParameter("department");
		      
		       String req_staffrole          =   request.getParameter("staffRole");
		       String req_phoneNumber        =   request.getParameter("phoneNumber");
		       String req_emailAddress       =   request.getParameter("emailAddress");
		       String req_room               =   request.getParameter("room");
		       
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
			    	        // System.out.println("photofilePart is null ");
			    }
			   
			    conn = DbUtil.getConnection();
	            String message = "";
	        
	            try 
	            {        
	                    // constructs SQL statement
	            		String sql = "INSERT INTO staffdetails ( staffTitle, staffName, department, role, phoneNumber, emailAddress, room, staffPhoto) values (?, ?, ?, ?, ?, ?, ?, ?)";
	            				
	            		PreparedStatement insert_statement = conn.prepareStatement(sql);
	            		insert_statement.setString(1, req_staffTitle );
	            		insert_statement.setString(2, req_staffName);
	            		insert_statement.setString(3, req_department);
	            		insert_statement.setString(4, req_staffrole);
	            		insert_statement.setString(5, req_phoneNumber);
	            		insert_statement.setString(6, req_emailAddress);
	            		insert_statement.setString(7, req_room);

	            	    if (inputStreamImage != null) 
	            	    {
	            		     // System.out.println("inputStreamImage  is  not null ");
	            		     // fetches input stream of the upload file for the image column
	            		     if (photofilePart.getSize() > 0)
	            		     {
	            		    	  // System.out.println("photofilePart.getSize() is "+photofilePart.getSize()+" so they must have choosen a new image");
	            		          insert_statement.setBlob(8, inputStreamImage);
	            		     }
	            		     else
	            		     {
	            		    	  // System.out.println("photofilePart.getSize() is "+photofilePart.getSize()+" so they must NOT have choosen a new image");
	            		    	  // System.out.println("So lets just use the previous image");
	            		    	  InputStream  previousInputStream  =  DbMethods.getStaffImageInputStreamForAStaffId(req_previousStaffId);	
	            		    	  
	            		    	  if (previousInputStream != null) 
	      	            	      {
	            		    		  insert_statement.setBlob(8, previousInputStream);
	      	            	      }
	            		    	  else
	            		    	  {
	            		    		  // System.out.println("previousInputStream is null");
	            		    	  }
	            		     }
	            	    }
	            		else
	            	    {
	            		     System.out.println("inputStreamImage  is null ");	            				    	
	            	    }
	            				
	            		int row_1 = insert_statement.executeUpdate();    // sends the statement to the database server
	            		
	            		
	            		// PreparedStatement delete_statement = conn.prepareStatement("DELETE FROM `phonebookdatabase`.`staffdetails` WHERE `staffdetails`.`staffId` = "+req_previousStaffId);
	            		PreparedStatement delete_statement = conn.prepareStatement("DELETE FROM staffdetails  WHERE staffId = "+req_previousStaffId);
		                int row_2 = delete_statement.executeUpdate();
		                    
	            	    if (row_1 > 0 && row_2 > 0) 
	            	    {
	       						message =  "<br><br><b>The details for "+req_staffTitle +" "+req_staffName + " have now been successfully UPDATED in the Phonebook database</b><br>";			
	            	    }
	            	    else
	            	    {
	            				message = "<br><br><b>The details for "+req_staffTitle +" "+req_staffName + " have NOT been updated in the Phonebook database</b><br>";
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
