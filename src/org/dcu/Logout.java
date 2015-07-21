package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet 
{	
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
    {
           response.setContentType("text/html");
           PrintWriter out = response.getWriter();
  
           HttpSession session = request.getSession(false); 
         
           if ( session == null )
           {
                response.sendRedirect( "/CelebrityPhonebook/InvalidLoginServlet");
           }
           else 
           {    
        	    Admin admin_obj = (Admin) session.getAttribute("theAdmin");
        	    
        	    if ( admin_obj  !=  null ) 
			    {  
      
                   String html_code =    "<div class=\"mainRightCenterSectionForTableResults\">"+ 
                                                "<div class=\"mainRightHeaderSection\">"+                                                    
                                                "</div>"+ 

                                               "<div class=\"mainRightBelowHeaderSection\">"+         	
                                                     "<h2> You have been logged out as an administrator </h2>"+  
                                               "</div>"+
                                         "</div>";
         	  
                   out.println(html_code);
         	
       	           session.invalidate();       	                	        
			}
          }
          out.close();
      }
}