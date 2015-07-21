package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveAStaffMemberInstructions")
public class RemoveAStaffMemberInstructions extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException 
    {
          res.setContentType("text/html");
          PrintWriter out = res.getWriter();
  
          HttpSession session = req.getSession(false); 
         
          if ( session == null )
          {                  
               res.sendRedirect( "/CelebrityPhonebook/InvalidLoginServlet");     // not logged in or timed out
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
										     "To remove a member of staff, first search for that staff member and go to their staff details page"+  
										     ",then on that page click the remove button similar the one shown below. <br/> <img src=\"images/Remove.png\" alt=\"Remove Image\" />"+  
										 "</div>"+
                                   "</div>";  
                  out.println(html_code);
			  }
          }
          out.close();
      }
}
