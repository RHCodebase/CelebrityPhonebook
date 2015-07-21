package org.dcu;

import java.sql.Connection;

public class AdminHeaderCode 
{
	  public static String getAdminHeaderCode(Admin admin_obj_param)
	  {
		  String html_code = "<div class=\"adminModeheader\">"+
                                   "<div class=\"adminOptionHeaderText\">"+
                	                     "Welcome " + admin_obj_param.getFirstname() + " " + admin_obj_param.getSurname() +".  You are now logged in as an administrator!"+ 
                                   "</div>"+ 
	                             
	                                "<div class=\"adminOptionAddAStaffMember\">"+
	                                       "<a href=\"javascript:displayAddAStaffMembers();\">"+
	                                            "<img src=\"images/AddaStaffMember.png\" alt=\"AddaStaffMember Image\" />"+
	                                       "</a>"+
	                               "</div>"+ 

                                   "<div class=\"adminOptionEditAStaffMember\">"+
                                          "<a href=\"javascript:displayEditAStaffMemberInstructions();\"> "+
                                                "<img src=\"images/EditaStaffMember.png\" alt=\"EditaStaffMember Image\" />"+
                                          "</a>"+
                                   "</div>"+ 
                      
				                   "<div class=\"adminOptionRemoveAStaffMember\">"+
					                     "<a href=\"javascript:displayRemoveAStaffMemberInstructions();\"> "+
								               "<img src=\"images/RemoveaStaffMember.png\" alt=\"RemoveaStaffMember Image\" />"+
					                     "</a>"+
				                   "</div>"+ 
					      
				                   "<div class=\"adminOptionLogout\">"+
						                 "<a href=\"javascript:displayLogout();\"> "+
							                 "<img src=\"images/Logout.png\" alt=\"Logout Image\" />"+
						                 "</a>"+
					               "</div>"+ 							
                               "</div>";
		  
		     return html_code;
	  }
}
