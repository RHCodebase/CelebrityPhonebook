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

@WebServlet("/CreateTableOfStaff")
public class CreateTableOfStaff extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public CreateTableOfStaff() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		     response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         
	         String  req_staffNameOrDepartment  =  request.getParameter("staffNameOrDepartmentEntered");  
	         
	         ArrayList<String> arrayListOfDistinctDepartments  =  new ArrayList<String>();   
	         arrayListOfDistinctDepartments                    =  DbMethods.getDepartmentArrayList();
	         
	         
	         if (  arrayListOfDistinctDepartments.contains( req_staffNameOrDepartment ) )
	         {
	        	   String req_Departmen  = req_staffNameOrDepartment;
	        	   response.sendRedirect( "/CelebrityPhonebook/CreateDepartmentDetails?sid=" + Math.random() + "&staffDepartParam=" + req_Departmen ); 
	         }
	         else
	         {
	        	   HttpSession session = request.getSession(false); 
	        	   String html_code = "";
	           
	               if ( session != null )
	               {   
	            	    Admin admin_obj = (Admin) session.getAttribute("theAdmin");
	            	  	 
	            	    if ( admin_obj  !=  null ) 
	  				    {  	            	    	
	            	       html_code = html_code + AdminHeaderCode.getAdminHeaderCode(admin_obj);
	  				    }
	                }	                                     
	        	     
	        	    String req_staffName                   =    req_staffNameOrDepartment;
			        ArrayList<String> arrayListOfStaffIds  =    new ArrayList<String>(); 
			        arrayListOfStaffIds                    =    DbMethods.getStaffIdsForNamesStartingQueryString(req_staffName);  
			        String html_row_code                   =    getCodeForRows(arrayListOfStaffIds); 
			            
			        html_code = html_code + "<div class=\"mainRightCenterSectionForTableResults\">"+ 
			                                    "<div class=\"mainRightHeaderSection\">"+ 
													   "<div class=\"mainRightHeaderSectionSearchResultsText\">"+ 
															"Search results for \""+req_staffName+"\" "+ 
													   "</div>"+ 
				                                "</div>"+ 
											    "<div class=\"mainRightBelowHeaderSection\">"+   
											          "<table id=\"phonebookBlueTable\">"+ 
											                "<tr>"+
														 		 "<th class=\"phonebookByNameFirstCol\">Name</th>"+              
														 		 "<th class=\"phonebookByNameSecondCol\">Department</th>"+
														 		 "<th class=\"phonebookByNameThirdCol\">Room</th>"+
														 		 "<th class=\"phonebookByNameFourthCol\">Tel</th>"+
														     "</tr>"
														      +html_row_code+
													   "</table>"+
											      "</div>"+
											   "</div>";
                                                      
			          out.println(html_code);
			          out.close();   
	         }
	    }
//===================================================================================================================================
	  private  String getCodeForRows(ArrayList<String>  listOfIds)
      {    	
		    Connection conn;
		    String htmlCodeString = ""; 
		    	 
			try 
			{  			     	 
			      conn = DbUtil.getConnection();
			      int i =0;
			      
			      while(i < listOfIds.size())
			      {
			     	  
			    	 String  staffId =  listOfIds.get(i);   
			         PreparedStatement s = conn.prepareStatement("select staffTitle,staffName,department,room,phoneNumber from staffdetails where staffId="+staffId);
			         
			         ResultSet rs = s.executeQuery();

			         while ( rs.next() ) 
			         {                    
			                 String  staffTitle          =   rs.getObject(1).toString();
			                 String  staffName           =   rs.getObject(2).toString();
			                 String  staffDepartment     =   rs.getObject(3).toString();
			                 String  staffRoom           =   rs.getObject(4).toString();			             
			                 String  staffPhoneNumber    =   rs.getObject(5).toString();
			                 
					 	     if (i % 2 == 0)
					 	     {
					 	    	  htmlCodeString = htmlCodeString + 
					 	     	  "<tr>"+
					 	    	         "<td class=\"phonebookByNameFirstCol\">"+"<a href=\"javascript:displayAStaffMembersDetails('"+staffId+"');\">"+staffTitle+" "+staffName+"</a></td>"+ 					 	     	  					 	     	      
					 	     	         "<td class=\"phonebookByNameSecondCol\">"+"<a href=\"javascript:displayStaffMembersInADepartment('"+staffDepartment+"');\">"+staffDepartment+"</a></td>"+ 					 	     	         
						 			     "<td class=\"phonebookByNameThirdCol\">"+staffRoom+"</td>"+
						 			     "<td class=\"phonebookByNameFourthCol\">"+staffPhoneNumber+"</td>"+	
						 		  "</tr>";   	  
					 	      }
					 	      else
					 	      {
					 	   	      htmlCodeString = htmlCodeString + 
					 	   	      "<tr class=\"alt\">"+
					 	   	              "<td class=\"phonebookByNameFirstCol\">"+"<a href=\"javascript:displayAStaffMembersDetails('"+staffId+"');\">"+staffTitle+" "+staffName+"</a></td>"+  					 	   	      					 	    	    
					 	    	          "<td class=\"phonebookByNameSecondCol\">"+"<a href=\"javascript:displayStaffMembersInADepartment('"+staffDepartment+"');\">"+staffDepartment+"</a></td>"+ 					 	    	          
						 			      "<td class=\"phonebookByNameThirdCol\">   "+staffRoom+"</td>"+
						  		          "<td class=\"phonebookByNameFourthCol\">  "+staffPhoneNumber+"</td>"+	
						          "</tr>";							 		  
					 	      }     
					 	      i++;
			         }
			      }
			      conn.close();
			   }
			   catch (SQLException e) 
			   {
	               e.printStackTrace();
			   } 
			   return htmlCodeString;	
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


