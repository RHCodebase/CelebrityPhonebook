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

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

@WebServlet("/StaffNameAndDepartmentCompletion")
public class  StaffNameAndDepartmentCompletion extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
       
   
        String query = request.getParameter("term");
        // System.out.println("query is "+query);
        query = query.toLowerCase();
        
        
        ArrayList<String> arrayListOfDistincStaffNames = new ArrayList<String>();
        arrayListOfDistincStaffNames = DbMethods.getStaffNamesArrayList();
        
        ArrayList<String> dropDownStrings = new ArrayList<String>();
        
        for (int i=0; i<arrayListOfDistincStaffNames.size(); i++)
        {
            
            String fullName =   arrayListOfDistincStaffNames.get(i).toLowerCase();
            
            String[] nameArray = fullName.split(" ");
            
            String firstName = nameArray[0];
            String surName = nameArray[1].trim();
            
            //  System.out.println("fullName is "+fullName+",\nfirstName is "+firstName+",\nsurName is "+surName );
            //  System.out.println("-----------------------------------------------------------");
            
            if(firstName.startsWith(query) && !dropDownStrings.contains(firstName) ) 
            {
            	  dropDownStrings.add(fullName);   
            }
            
            if(surName.startsWith(query) && !dropDownStrings.contains(surName) ) 
            {
                  dropDownStrings.add(surName);
            }
        }
        
        ArrayList<String> arrayListOfDistinctDepartments =  new ArrayList<String>();   
        arrayListOfDistinctDepartments = DbMethods.getDepartmentArrayList();
        
        for (int i=0; i<arrayListOfDistinctDepartments.size(); i++)
        {
        	String temp_department = arrayListOfDistinctDepartments.get(i);
        	
        	if ( temp_department.startsWith(query)) 
        	{
                 dropDownStrings.add(temp_department);
        	}
        }
        
        JSONArray arrayObj  = new JSONArray();
        
        for (int i=0; i<dropDownStrings.size(); i++)
        {
        	 arrayObj.add(dropDownStrings.get(i));
        }
       
        out.println(arrayObj.toString());
        out.close();       
    }
  }

