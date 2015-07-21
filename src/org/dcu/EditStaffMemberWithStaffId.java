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


@WebServlet("/EditStaffMemberWithStaffId")
public class EditStaffMemberWithStaffId extends HttpServlet 
{
	   private static final long serialVersionUID = 1L;
       
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
		     response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         
	         String  req_staffIdParam  =  request.getParameter("staffIdParam");
	         // System.out.println("req_staffIdParam first is   "+req_staffIdParam         );
	         
             ArrayList<String> staffInfo =  new ArrayList<String>(); 
	         staffInfo =  DbMethods.getStaffDetailsForAStaffId(req_staffIdParam);
	         
	         String parameterString = "?titleValue="+staffInfo.get(1)+
		                              "&nameValue="+staffInfo.get(2)+
	                                  "&departValue="+staffInfo.get(3)+
	                                  "&roleValue="+staffInfo.get(4)+
		                              "&phoneValue="+staffInfo.get(5)+
	                                  "&emailValue="+staffInfo.get(6)+
	                                  "&roomValue="+staffInfo.get(7)+
	                                  "&staffIdValue="+staffInfo.get(0);
	        		 		 
	         
	         HttpSession session = request.getSession(false); 
	           
             if ( session == null )
             {                    
            	 response.sendRedirect( "/CelebrityPhonebook/InvalidLoginServlet");   // not logged in or timed out
             }
             else 
             {          
            	  Admin admin_obj = (Admin) session.getAttribute("theAdmin");
            	  
            	  if ( admin_obj  !=  null ) 
				  {              		                                      
                         String html_code = AdminHeaderCode.getAdminHeaderCode(admin_obj)+                           
            	                               "<div class=\"mainRightCenterSectionForTableResults\">"+ 
                                                            "<div class=\"mainRightHeaderSection\">"+                                                               
                                                             "</div>"+ 
                                                  
                                         
                                                     "<div class=\"mainRightBelowHeaderSection\">"+                                
    //                 "<iframe src=\"http://localhost:8080/PhonebookJDBC_SQL/uploadEditedStaffMember.jsp"+parameterString+"\" style=\"border:none\" width=\"100%\" height=\"100%\"></iframe>"+ 
                       "<iframe src=\"/CelebrityPhonebook/uploadEditedStaffMember.jsp"+parameterString+"\" style=\"border:none\" width=\"100%\" height=\"100%\"></iframe>"+   
                                                     "</div>"+
                                             "</div>";   	                                                       	        
                         out.println(html_code);                                             
				  }

             }
             out.close();
         }
}