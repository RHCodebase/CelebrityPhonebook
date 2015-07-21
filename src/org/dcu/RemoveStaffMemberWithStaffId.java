package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveStaffMemberWithStaffId")
public class RemoveStaffMemberWithStaffId extends HttpServlet 
{
	   private static final long serialVersionUID = 1L;
       
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
		     response.setContentType("text/html");	         
	         
	         String  req_staffIdParam  =  request.getParameter("staffIdParam");
	         // System.out.println("req_staffIdParam  is   "+req_staffIdParam         );
	         
             ArrayList<String> staffInfo =  new ArrayList<String>(); 
	         staffInfo =  DbMethods.getStaffDetailsForAStaffId(req_staffIdParam);
	         
	         HttpSession session = request.getSession(false); 
	         Admin admin_obj = null;
        	 String message = "";
           
             if ( session != null )
             {   
            	    admin_obj = (Admin) session.getAttribute("theAdmin");
            	    if ( admin_obj  !=  null ) 
  				    {  
            	       Connection conn;
               	 
                	   try 
                	   {  
                	       conn = DbUtil.getConnection();
                	  
                	       PreparedStatement statement = conn.prepareStatement("DELETE FROM `staffdetails` WHERE `staffdetails`.`staffId` = "+req_staffIdParam);
             
                           int row = statement.executeUpdate();
				         
                           if (row > 0) 
				           {
				            	message =  "<br><br><b>The details for "+staffInfo.get(1)+" "+staffInfo.get(2)+" have now been successfully REMOVED from the Phonebook database</b><br>";				
				           }
				           else
				           {
					            message = "<br><br><b>The details for "+staffInfo.get(1)+" "+staffInfo.get(2)+ " have NOT been removed to the Phonebook database</b><br>";
				          }
                          conn.close();
			           } 
	                   catch (SQLException ex)
	                   {
				          message = "ERROR: " + ex.getMessage();
				          ex.printStackTrace();
	                  }
  				  }
            	  else
                  {
                 	   message =  "<br><br><b>Only an administrator can remove a staff members details from the Phonebook database</b><br>";
                 	   
                  }
                  request.setAttribute("Message", message);

                  getServletContext().getRequestDispatcher("/serverMessage.jsp").include(request, response);     	  
           }
               	  
	   }
}
