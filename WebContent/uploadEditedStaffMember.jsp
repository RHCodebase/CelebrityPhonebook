<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Staff Member</title>
</head>
<body>	
       <p style="color:#2587ab;font-family:Arial,sans-serif; font-size:25px; font-weight:lighter;">
	         Edit the details for the staff member below:
      </p>
  
      <form method="POST" action="/CelebrityPhonebook/UploadEditedStaffDetails"  enctype="multipart/form-data">                      
		    <table>                                                           		
						<tr>
						      <td>Staff Title:</td>
					          <td><input type="text" name="staffTitle" size="40" required="required" value="<%=(String)request.getParameter("titleValue")%>" /></td>
						</tr>
					    <tr>
						     <td>Staff Name:</td>                                                    
						     <td><input type="text" name="staffName" size="40" required="required"  value="<%=(String)request.getParameter("nameValue")%>"/></td>
					    </tr>
					    <tr>
							  <td>Department:</td>                                                         
							  <td><input type="text" name="department" size="40" required="required"  value="<%=(String)request.getParameter("departValue")%>" /></td>
					   </tr>
					   
					   <tr>
						      <td>Staff Role:</td>                                                         
					          <td><input type="text" name="staffRole" size="40" required="required"  value="<%=(String)request.getParameter("roleValue")%>"/></td>
						</tr>
					    <tr>
						     <td>Phone number:</td>                                                   
						     <td><input type="text" name="phoneNumber" size="40" required="required"  value="<%=(String)request.getParameter("phoneValue")%>" /></td>
					    </tr>
					    <tr>
							  <td>Email Address:</td>                                                
							  <td><input type="text" name="emailAddress" size="40" required="required" value="<%=(String)request.getParameter("emailValue")%>" /></td>
					   </tr>
					    <tr>
							  <td>Room:</td>                                                                  
							  <td><input type="text" name="room" size="40" required="required"  value="<%=(String)request.getParameter("roomValue")%>"/></td>
					   </tr>
					   <tr>
							  <td></td>                                                                  
							  <td><input type="hidden" name="previousStaffId" size="40"  value="<%=(String)request.getParameter("staffIdValue")%>"/></td>
					   </tr>
					   
					  
				       <tr> 
					      <td>Staff Image: 
				                <div class="searchStaffImageDiv">
	 <img src="/CelebrityPhonebook/DisplayStaffImageServlet?sid=<%=Math.random()%>&id=<%=(String)request.getParameter("staffIdValue")%>"  alt="Staff Image" height="170" width="175" > 
			                     </div>
					      </td> 
					      <!--  <td><input type="file" name="photo" size="50" required="required" /></td>   -->
					      <td><input type="file" name="photo" size="60"  /></td>
				      </tr>
				       
				       
				       
				       <!--  <tr>  --> 
					   <!--        <td>Staff Image:</td> --> 
					        <!--   <td><input type="file" name="photo" size="50" required="required" /></td>   --> 
					    <!--       <td><input type="file" name="photo" size="50"  /></td> --> 
				      <!--  </tr> --> 
				       
				       
						
					   <tr>
							  <td><input type="submit" value="Submit"></td>
							  <td><input type="reset" value="Clear" /></td>
					  </tr>
					  	
		       </table>
	     </form>
          
</body>
</html>