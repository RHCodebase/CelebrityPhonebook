package org.dcu;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.util.*;


@WebServlet("/ValidateAdminUsingMySQL")
public class  ValidateAdminUsingMySQL extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public ValidateAdminUsingMySQL()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		          response.setContentType("text/html");
	           
		          PrintWriter out = response.getWriter();
	           	  	            		 
	              String req_adminUsernameParam = request.getParameter("adminUsernameParam");
	              String req_adminPasswordParam = request.getParameter("adminPasswordParam");
	            		  
	              Admin admin_obj = validateAdmin(req_adminUsernameParam , req_adminPasswordParam);
				 
	              if ( admin_obj  !=  null ) 
				  {      
	            	         HttpSession session = request.getSession(true);
				             session.setAttribute("theAdmin", admin_obj);   // put the OBJECT on the session
				         
				         
				    	     // login succeeded				                                           
                              String html_code = AdminHeaderCode.getAdminHeaderCode(admin_obj)+                            
            	                                     "<div class=\"mainRightCenterSectionForTableResults\">"+ 
                                                               "<div class=\"mainRightHeaderSection\">"+                                                                  
                                                               "</div>"+ 
                                                               "<div class=\"mainRightBelowHeaderSection\">"+                 	                                                   
		                                                       "</div>"+
                                                      "</div>";
	            	         
		                      out.println(html_code);
                              out.close();                 						     
				   }
				   else 
				   {   
				    	     // login failed
					         response.sendRedirect( "/CelebrityPhonebook/InvalidLoginServlet");
	               }
                   out.close();		   
}
//=======================================================================================================================================
private Admin validateAdmin(String usernameParam,String passwordParam)
{
	 Connection conn;
	 
	 try 
	 {  
	    conn = DbUtil.getConnection();
	   
	    PreparedStatement s = conn.prepareStatement("select * from administratorTable");
  
        ResultSet rs = s.executeQuery();

        while ( rs.next() ) 
        {                
	           int    adminId              =   Integer.parseInt(rs.getObject(1).toString());
		          
		       String  admin_firstName     =   rs.getObject(2).toString();
		       String  admin_surName       =   rs.getObject(3).toString();
		       String  admin_userName      =   rs.getObject(4).toString();
		       String  admin_passWordHash  =   rs.getObject(5).toString();       
		           
		       System.out.println("-----------------------------------------------------------");
		      
		       String passwordParamHash = hashPasswordUsingSHA256(passwordParam);
				     
		       if ( ( admin_userName.equals(usernameParam) &&  ( admin_passWordHash.equals(passwordParamHash) )   ) ) 
		       {
					      String  admin_canAddStaffDetails_Str      =   rs.getObject(6).toString();
					      boolean admin_canAddStaffDetails          =   Boolean.parseBoolean( admin_canAddStaffDetails_Str);
					      
			              String  admin_canEditStaffDetails_Str      =   rs.getObject(7).toString();
			              boolean admin_canEditStaffDetails          =   Boolean.parseBoolean(admin_canEditStaffDetails_Str);
			              
			              String  admin_canRemoveStaffDetails_Str    =   rs.getObject(8).toString();
			              boolean admin_canRemoveStaffDetails        =   Boolean.parseBoolean(admin_canRemoveStaffDetails_Str);
			              
				          Admin admin_obj =  new Admin( adminId, admin_firstName, admin_surName, admin_userName, admin_passWordHash, admin_canAddStaffDetails, admin_canEditStaffDetails, admin_canRemoveStaffDetails );
				        	
				          conn.close();
				          return admin_obj;
			     }
           }    
           conn.close();
	  }
      catch (SQLException e) 
      {
           e.printStackTrace();
      }
      return null;
 }
//=======================================================================================================================================
private String hashPasswordUsingSHA256(String passwordParam) 
{
	    // http://www.mkyong.com/java/java-sha-hashing-example/
	    StringBuffer hexString = null;

	    try
	    {
		     MessageDigest md = MessageDigest.getInstance("SHA-256");
             md.update(passwordParam.getBytes());

             byte byteData[] = md.digest();

             hexString = new StringBuffer();
	        
			 for (int i=0;i<byteData.length;i++) 
	         {
		         String hex = Integer.toHexString(0xff & byteData[i]);
	     	    
			     if(hex.length()==1)
		         { 
			         hexString.append('0');
 		         } 
	     	     hexString.append(hex);
             }
	     }
	     catch (Exception ex)
	     {
              System.out.println("NoSuchAlgorithmException " + ex.toString());
	     }
	     return hexString.toString();
}
//=======================================================================================================================================
}
/*
CREATE TABLE IF NOT EXISTS `administratorTable` 
(
    `adminId`                int(11)       NOT NULL AUTO_INCREMENT,
    `firstName`              varchar(30)   DEFAULT NULL,
    `surName`                varchar(30)   DEFAULT NULL,
    `userName`               varchar(30)   DEFAULT NULL,
    `passWordHash`           varchar(300)  DEFAULT NULL,
    `canAddStaffDetails`     varchar(30)   DEFAULT NULL,
    `canEditStaffDetails`    varchar(30)   DEFAULT NULL,
    `canRemoveStaffDetails`  varchar(30)   DEFAULT NULL,
     
     PRIMARY KEY (`adminId`)
)
###################################################################################################################################################

>java SHAHashingExample sen10radm1n

passwordHashed is 1a391deee223a7b5ea857e2b489945a1ccb9d34811babcd4de5ed3b12225fb2a

>java SHAHashingExample jun10radm1n

passwordHashed is aae76c712d4df44b8cd767438c26da0f796bf92ee1c2632d4ff0861f721f6611

###################################################################################################################################################
INSERT INTO administratorTable ( firstName, surName, userName, passWordHash, canAddStaffDetails, canEditStaffDetails, canRemoveStaffDetails ) values 
("John", "Doe", "doej", "1a391deee223a7b5ea857e2b489945a1ccb9d34811babcd4de5ed3b12225fb2a", "true", "true", "true");

INSERT INTO administratorTable ( firstName, surName, userName, passWordHash, canAddStaffDetails, canEditStaffDetails, canRemoveStaffDetails ) values 
("Mary", "Smith",  "smithm", "aae76c712d4df44b8cd767438c26da0f796bf92ee1c2632d4ff0861f721f6611", "true", "true", "false");

###################################################################################################################################################

*/

