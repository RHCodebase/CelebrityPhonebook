package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import net.sf.json.JSONArray;

@WebServlet("/CreateStaffDetails")
public class CreateStaffDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		     response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         
	         String  req_staffIdParam  =  request.getParameter("staffIdParam");
	         // System.out.println("req_staffIdParam  is   "+req_staffIdParam         );
	         
	         ArrayList<String> staffInfo =  new ArrayList<String>(); 
	         
	         staffInfo =  DbMethods.getStaffDetailsForAStaffId(req_staffIdParam);

	         HttpSession session = request.getSession(false); 
	         Admin admin_obj = null;
        	 String html_code = "";
           
             if ( session != null )
             {   
            	    admin_obj = (Admin) session.getAttribute("theAdmin");
            	   
            	    if ( admin_obj  !=  null ) 
  				    {              	    	
            	       html_code = html_code + AdminHeaderCode.getAdminHeaderCode(admin_obj);
  				    }
             }	   
	           
             html_code = html_code + 
                   "<div class=\"mainRightCenterSection\">"+
	                    "<div class=\"mainRightCenterSectionTop\">"+  
			                      "<div class=\"searchStaffNameDiv\">"+staffInfo.get(1)+" "+staffInfo.get(2)+"</div>"+ 
			                      "<div class=\"searchStaffDepartmentDiv\">"+staffInfo.get(3)+"</div>"+ 
			                      "<div class=\"searchStaffImageDiv\">"+  
	                                 "<img src=\"/CelebrityPhonebook/DisplayStaffImageServlet?sid=" + Math.random() + "&id=" + staffInfo.get(0) +"\"  alt=\"Staff Image\" height=\"170\" width=\"175\" >"+ 
			                      "</div>"+ 
			                      "<div class=\"searchStaffDetailsDiv\">"+   
							           "<table id=\"staffContactTable\">"+ 
										   "<tr>"+ 							
											    "<td class=\"firstColOddRow\">                Name:                 </td>"+ 
											    "<td class=\"secondColOddRow\">               "+staffInfo.get(1)+" "+staffInfo.get(2)+"         </td>"+ 
										    "</tr>"+ 
										    "<tr>"+ 
											    "<td class=\"firstColEvenRow\">               Department:                                       </td>"+ 
										// 	    "<td class=\"secondColEvenRow\">              "+staffInfo.get(3)+"                               </td>"+ 
	                                            "<td class=\"secondColEvenRow\"><a href=\"javascript:displayStaffMembersInADepartment('"+staffInfo.get(3)+"');\">"+staffInfo.get(3)+"</a></td>"+		
										    "</tr>"+ 
										    "<tr>"+ 
											    "<td class=\"firstColOddRow\">                Role:                 </td>"+ 
											    "<td class=\"secondColOddRow\">               "+staffInfo.get(4)+"         </td>"+ 
										    "</tr>"+ 
										    "<tr>"+ 
											    "<td class=\"firstColEvenRow\">               Phone Number:         </td>"+ 
											    "<td class=\"secondColEvenRow\">              "+staffInfo.get(5)+"              </td>"+ 
										    "</tr>"+ 
										    "<tr>"+ 								
											    "<td class=\"firstColOddRow\">                Email Address:          </td>"+ 
											    "<td class=\"secondColOddRow\">               "+staffInfo.get(6)+"       </td>"+ 
										    "</tr>"+ 
										     "<tr>"+ 
											    "<td class=\"firstColEvenRow\">               Room:                    </td>"+ 
											    "<td class=\"secondColEvenRow\">              "+staffInfo.get(7)+"                 </td>"+ 
											  
										    "</tr>"+ 
                                       "</table>"+  	  
			                     "</div>";
														        							           
							    if (admin_obj != null) 
								{   																					 											   
										   html_code = html_code +
										   "<div class=\"searchStaffAdminOptionEditDiv\">"+  
											        "<a href=\"javascript:editStaffMemberWithStaffId('"+staffInfo.get(0)+"');\"><img src=\"images/Edit.png\" alt=\"Edit Image\" /></a>"+      								            	     
										    "</div>"+
										   "<div class=\"searchStaffAdminOptionRemoveDiv\">"+  
									                "<a href=\"javascript:removeStaffMemberWithStaffId('"+staffInfo.get(0)+"');\"><img src=\"images/Remove.png\" alt=\"Remove Image\" /></a>"+      								            	     
								           "</div>";										   																				   
								}
								     
				                  
html_code = html_code +  "</div>"+    
				          "<div class=\"mainRightCenterSectionBottom\">"+
				          "</div>"+
				   "</div>"; 

               System.out.println("html_code is \n"+html_code);
	         
	           out.println(html_code);
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

