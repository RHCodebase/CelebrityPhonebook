package org.dcu;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DbMethods 
{
//-----------------------------------------------------------------------------------------------------------------------------------
	public static ArrayList<String>  getDepartmentArrayList()
	{
		    ArrayList<String> arrayListOfDepartments = new ArrayList<String>();
	        Connection conn;
	    	 
	     	try 
	     	{  
	     	       conn = DbUtil.getConnection();
	     	  
	     	       PreparedStatement s = conn.prepareStatement("select DISTINCT department from staffdetails");
	        
	               ResultSet rs = s.executeQuery();

	               while ( rs.next() ) 
	               {
			            	 String  depart              =   rs.getObject(1).toString();
			              
			                 // System.out.println("depart is "+depart);
			                      
			                 String departLowerCase   =   depart.toLowerCase();
			          
			                 arrayListOfDepartments.add(departLowerCase);
		 	        
	               }     
	               conn.close();
	     	}
	        catch (SQLException e) 
	        {
	              e.printStackTrace();
	        } 
	     	return arrayListOfDepartments;	     	
	 }
//-----------------------------------------------------------------------------------------------------------------------------------	
	public static ArrayList<String>  getStaffIdsForNamesStartingQueryString(String queryString)
     { 
    	 ArrayList<String> arrayListOfStaffIdsThatStartWithQueryString = new ArrayList<String>();
         Connection conn;
    	 
     	 try 
     	 {  
     	       conn = DbUtil.getConnection();
     	  
     	       PreparedStatement s = conn.prepareStatement("select staffId, staffName from staffdetails");
        
               ResultSet rs = s.executeQuery();

        
               while ( rs.next() ) 
               {
	            	 String  staffId             =   rs.getObject(1).toString();
	                 String  staffName           =   rs.getObject(2).toString();
	                    
	                 String staffNameLowerCase   =   staffName.toLowerCase();
	                 String queryStringLowerCase =   queryString.toLowerCase(); 
		 	            
		 	         String[] nameArray = staffNameLowerCase.split(" ");		 	            
		 	         String firstName = nameArray[0];
		 	         String surtName = nameArray[1].trim();
		 	            
		 	      
		 	         if(firstName.startsWith(queryStringLowerCase)) 
		 	         {
		 	        	    arrayListOfStaffIdsThatStartWithQueryString.add(staffId);
		 	         }
		 	            
		 	         if(surtName.startsWith(queryStringLowerCase)) 
		 	         {
		 	        	    arrayListOfStaffIdsThatStartWithQueryString.add(staffId);
		 	         }	   
		 	         if(staffNameLowerCase.equals(queryStringLowerCase)) 
		 	         {
		 	        	    arrayListOfStaffIdsThatStartWithQueryString.add(staffId);
		 	         }	   
               }    
               conn.close();
     	    }
            catch (SQLException e) 
            {
                  e.printStackTrace();
            } 
     	
         	return arrayListOfStaffIdsThatStartWithQueryString;	     	
    }    	
//-----------------------------------------------------------------------------------------------------------------------------------
	public static ArrayList<String>  getStaffDetailsForAStaffId(String staffIdParameter)
	{	 
	    	 ArrayList<String> staffDetailsArrayList = new ArrayList<String>();
	         Connection conn;
	    	 
	     	 try 
	     	 {  	     	 
	     	     conn = DbUtil.getConnection();
	     	  
	     	     PreparedStatement s = conn.prepareStatement("select * from staffdetails where staffId='"+staffIdParameter+"'");
	        
	             ResultSet rs = s.executeQuery();

	   
	             while ( rs.next() ) 
	             {             
	            	 String  staffId             =   rs.getObject(1).toString();	                
	                 String  staffTitle          =   rs.getObject(2).toString();
	                 String  staffName           =   rs.getObject(3).toString();
	                 String  staffDepartment     =   rs.getObject(4).toString();
	                 String  staffRole           =   rs.getObject(5).toString();
	                 String  staffPhoneNumber    =   rs.getObject(6).toString();
	                 String  staffEmailAddress   =   rs.getObject(7).toString();
	                 String  staffRoom           =   rs.getObject(8).toString();
	                 
	                 staffDetailsArrayList.add(staffId);
	                 staffDetailsArrayList.add(staffTitle);
	                 staffDetailsArrayList.add(staffName);
	                 staffDetailsArrayList.add(staffDepartment);
	                 
	                 staffDetailsArrayList.add(staffRole);
	                 staffDetailsArrayList.add(staffPhoneNumber);
	                 staffDetailsArrayList.add(staffEmailAddress);
	                 staffDetailsArrayList.add(staffRoom);
	          	                 
	                 System.out.println("-----------------------------------------------------------");

	             }      
	             conn.close();
	     	}
	        catch (SQLException e) 
	        {
	              e.printStackTrace();
	        } 	     	
	     	return staffDetailsArrayList;	     	
	  }   
//-----------------------------------------------------------------------------------------------------------------------------------
	public static ArrayList<String>  getStaffIdsForADepartment(String departmentString)
    { 
    	 ArrayList<String> arrayListOfStaffIdsThatWorkInADepartment = new ArrayList<String>();
         Connection conn;
    	 
     	 try 
     	 {  
     	       conn = DbUtil.getConnection();
     	  
     	       PreparedStatement s = conn.prepareStatement("select staffId from staffdetails where department='"+departmentString+"'");
     	                                                
               ResultSet rs = s.executeQuery();

               while ( rs.next() ) 
               {
            	       String  staffId             =   rs.getObject(1).toString();
            
                       // System.out.println("staffId is "+staffId);
             
                       arrayListOfStaffIdsThatWorkInADepartment.add(staffId);				 	        
               }     
           	   conn.close();
     	}
        catch (SQLException e) 
        {
              e.printStackTrace();
        } 
     	return arrayListOfStaffIdsThatWorkInADepartment;	     	
    }  
//-----------------------------------------------------------------------------------------------------------------------------------
	public static ArrayList<String>  getStaffNamesArrayList()
    {
	       ArrayList<String> arrayListOfStaffNames = new ArrayList<String>();
	    		 
	       Connection conn;
	    	 
	       try 
	       {  
	             conn = DbUtil.getConnection();
	     	  
	     	     PreparedStatement s = conn.prepareStatement("select DISTINCT staffName from staffdetails");
	        
	             ResultSet rs = s.executeQuery();
	
	             while ( rs.next() ) 
	             {
	                                 
	                 String  temp_staffName   =  rs.getObject(1).toString();
	                
	                 arrayListOfStaffNames.add(temp_staffName); 		   
	             }  
	             conn.close();
	     	}
	        catch (SQLException e) 
	        {
	              e.printStackTrace();
	        }
	     	return arrayListOfStaffNames;
     }  
//-----------------------------------------------------------------------------------------------------------------------------------
	 public static InputStream        getStaffImageInputStreamForAStaffId(String staffIdParameter)
	 {
	      Connection conn;
	      InputStream staffPhotoInputStream = null;
	      // System.out.println("staffIdParameter is "+staffIdParameter);
	      
	       try
	       {	  
				   conn = DbUtil.getConnection();
				   
				   // System.out.println("Connection Successful..... creating statement....");
				   Statement stmt = conn.createStatement();
				   
				   stmt.executeQuery("SELECT staffPhoto FROM staffdetails where staffId="+staffIdParameter);
				   ResultSet rs = stmt.getResultSet();
	
				   if (rs.next())
				   {         								   
					        	 Blob staffPhotoBlob        = rs.getBlob("staffPhoto");
					        	 // long staffPhotoBlobLength  = staffPhotoBlob.length();
					         	 // System.out.println("staffPhotoBlobLength is "+staffPhotoBlobLength);
					        	
					        	 staffPhotoInputStream  = staffPhotoBlob.getBinaryStream();
					        	
					        	 /*
					        	    if (staffPhotoInputStream != null)
					        	 
					        	 {
					        		 System.out.println("staffPhotoInputStream is not null");
					        	 }
					        	 else
					        	 {
					        		 System.out.println("staffPhotoInputStream is null");
					        	 }
					             */
					        	 
					        	 // return staffPhotoInputStream;					        	 							   					
                     } 		
				     staffPhotoInputStream.close();
				     conn.close();
            }
            catch (IOException io) 
            {
            	  io.printStackTrace();
            } 
            catch (SQLException e) 
            {
                  e.printStackTrace();
            } 
	        return staffPhotoInputStream;	      
	}
//-------------------------------------------------------------------------------------------------------------------------------------------
	public static InputStream        getNoImageAvailableInputStream()
	{
	       Connection  conn;
	       InputStream staffPhotoInputStream = null;
	       // System.out.println("staffIdParameter is "+staffIdParameter);
	      
	       try
	       {	  
				   conn = DbUtil.getConnection();
				   
				   // System.out.println("Connection Successful..... creating statement....");
				   Statement stmt = conn.createStatement();
				   
				   stmt.executeQuery("SELECT staffPhoto FROM NoImageAvailableTable where staffId=1");
				   ResultSet rs = stmt.getResultSet();
	
				   if (rs.next())
				   {         								   
					        	 Blob staffPhotoBlob        = rs.getBlob("staffPhoto");
					        	 // long staffPhotoBlobLength  = staffPhotoBlob.length();
					         	 // System.out.println("staffPhotoBlobLength is "+staffPhotoBlobLength);
					        	
					        	 staffPhotoInputStream  = staffPhotoBlob.getBinaryStream();
					        	 
					        	/*  if (staffPhotoInputStream != null)
					        	 {
					        		 // System.out.println("staffPhotoInputStream is not null");
					        	 }
					        	 else
					        	 {
					        		 // System.out.println("staffPhotoInputStream is null");
					        	 }
					            */ 
					        	//  return staffPhotoInputStream;					        	 							   					
                     } 	
				     staffPhotoInputStream.close();
				     conn.close();
            }
	        catch (IOException io) 
            {
                  io.printStackTrace();
            } 
            catch (SQLException e) 
            {
                  e.printStackTrace();
            } 
	        return staffPhotoInputStream;	      
	}
	//-----------------------------------------------------------------------------------------------------------------------------------
}

