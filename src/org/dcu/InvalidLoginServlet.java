package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

@WebServlet("/InvalidLoginServlet")
public class InvalidLoginServlet extends HttpServlet 
{
 private static final long serialVersionUID = 112L;
       
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
    	     res.setContentType("text/html");
		     PrintWriter out = res.getWriter();
		     
		     String html_table_code = "<div class=\"mainRightCenterSectionForTableResults\">"+ 
                                                 "<div class=\"mainRightHeaderSection\">"+ 
	                   	                                  
	                   	                                      "<p style=\"color:#2587ab;font-family:Arial,sans-serif; font-size:25px; font-weight:lighter;\">"+    
	                   	                                       "Invalid Username or Password <br/>"+ 
	                   	                                       "Click the Administrator Login button to try again "+
		                                                      "</p>"+ 
	                   	                                   
                                                 "</div>"+ 
                                                 "<div class=\"mainRightBelowHeaderSection\">"+
                                                 "</div>"+
		                               "</div>";
		                                              
									        
                     			            
										              
              out.println(html_table_code);
              out.close();        
    }    
}

