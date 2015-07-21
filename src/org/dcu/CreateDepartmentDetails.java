package org.dcu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CreateDepartmentDetails")
public class CreateDepartmentDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
   			         response.setContentType("text/html");
			         PrintWriter out = response.getWriter();
			         
			         String  req_staffDepartParam  =  request.getParameter("staffDepartParam");
			         String  departmentStringCapitalFirstLetter      =  req_staffDepartParam.substring(0, 1).toUpperCase() + req_staffDepartParam.substring(1); ;
			         System.out.println("departmentStringCapitalFirstLetter  is   "+departmentStringCapitalFirstLetter         );
			         
			       
			         ArrayList<String> arrayListOfStaffIdsThatWorkInThisDepartment =  new ArrayList<String>(); 
			         arrayListOfStaffIdsThatWorkInThisDepartment  =  DbMethods.getStaffIdsForADepartment(req_staffDepartParam);

			         String html_row_code = getRowsForDepartmentTable(arrayListOfStaffIdsThatWorkInThisDepartment); 
			        
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
			         
		             html_code = html_code + "<div class=\"mainRightCenterSectionForTableResults\">"+ 
                                                       "<div class=\"mainRightHeaderSection\">"+ 
								                   	          "<div class=\"mainRightHeaderSectionDepartmentText\">"+ 
								                   	             departmentStringCapitalFirstLetter+
									                          "</div>"+ 
                                                       "</div>"+ 
                                                       "<div class=\"mainRightBelowHeaderSection\">"+   
			        
				                                       	         "<table id=\"phonebookBlueTable\">"+ 
				        		                                    "<tr>"+
										 		                          "<th class=\"phonebookByDepartFirstCol\">Name</th>"+              
										 		                          "<th class=\"phonebookByDepartSecondCol\">Role</th>"+
										 		                          "<th class=\"phonebookByDepartThirdCol\">Room</th>"+
										 		                          "<th class=\"phonebookByDepartFourthCol\">Tel</th>"+
					 		                                         "</tr>"+
										 		                      html_row_code+
										 		               "</table>"+
									 		            "</div>"+
			                                        "</div>";
									 		         
									 		                     
			          out.println(html_code);
			          out.close();                 
			   }
		//===================================================================================================================================
			  private  String getRowsForDepartmentTable(ArrayList<String>  listOfIds)
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
					         PreparedStatement s = conn.prepareStatement("select staffTitle,staffName,role,room,phoneNumber from staffdetails where staffId="+staffId);
					         
					         ResultSet rs = s.executeQuery();

					         while ( rs.next() ) 
					         {                    
					                 String  staffTitle          =   rs.getObject(1).toString();
					                 String  staffName           =   rs.getObject(2).toString();
					                 String  staffRole           =   rs.getObject(3).toString();
					                 String  staffRoom           =   rs.getObject(4).toString();			             
					                 String  staffPhoneNumber    =   rs.getObject(5).toString();
					                            
					                 
							 	     if (i % 2 == 0)
							 	     {
							 	    	  htmlCodeString = htmlCodeString + 
							 	     	  "<tr>"+
							 	    	         "<td class=\"phonebookByDepartFirstCol\">"+"<a href=\"javascript:displayAStaffMembersDetails('"+staffId+"');\">"+staffTitle+" "+staffName+"</a></td>"+  	
							 	     	         "<td class=\"phonebookByDepartSecondCol\">"+staffRole+"</td>"+
								 			     "<td class=\"phonebookByDepartThirdCol\">"+staffRoom+"</td>"+
								 			     "<td class=\"phonebookByDepartFourthCol\">"+staffPhoneNumber+"</td>"+	
								 		  "</tr>";   	  
							 	      }
							 	      else
							 	      {
							 	   	      htmlCodeString = htmlCodeString + 
							 	   	      "<tr class=\"alt\">"+
							 	   	              "<td class=\"phonebookByDepartFirstCol\">"+"<a href=\"javascript:displayAStaffMembersDetails('"+staffId+"');\">"+staffTitle+" "+staffName+"</a></td>"+  
							 	    	          "<td class=\"phonebookByDepartSecondCol\">  "+staffRole+"</td>"+
								 			      "<td class=\"phonebookByDepartThirdCol\">   "+staffRoom+"</td>"+
								  		          "<td class=\"phonebookByDepartFourthCol\">  "+staffPhoneNumber+"</td>"+	
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
