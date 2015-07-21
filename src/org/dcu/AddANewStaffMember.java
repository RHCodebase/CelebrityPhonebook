package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddANewStaffMember")
public class AddANewStaffMember extends HttpServlet 
{
       private static final long serialVersionUID = 1L;
    
       public void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException 
       {
             res.setContentType("text/html");
             PrintWriter out = res.getWriter();
           
             HttpSession session = req.getSession(false); 
           
             if ( session == null )
             {   
                  // not logged in or timed out
                  res.sendRedirect( "/CelebrityPhonebook/InvalidLoginServlet");
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
                     //  "<iframe src=\"http://localhost:8080/PhonebookJDBC_SQL/uploadANewStaffMember.jsp\" style=\"border:none\" width=\"100%\" height=\"100%\"></iframe>"+  
                         "<iframe src=\"/CelebrityPhonebook/uploadANewStaffMember.jsp\" style=\"border:none\" width=\"100%\" height=\"100%\"></iframe>"+ 
                                                     "</div>"+
                                             "</div>";
   	                                           
                        out.println(html_code);
				  }
             }
             out.close();
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
