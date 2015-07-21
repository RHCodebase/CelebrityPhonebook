<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Phonebook</title>

    
    <link href="css/bodyCenterPageHeaderMainLeft.css"   rel="stylesheet">  
	<link href="css/mainRight.css"                      rel="stylesheet">
	
 	<link href="css/usefulNumbersTableStyle.css"        rel="stylesheet">
    <link href="css/searchStaffStyle.css"               rel="stylesheet">  
    <link href="css/phonebookBlueTableStyle.css"        rel="stylesheet">
    <link href="css/textButtonsStyle.css"               rel="stylesheet">
    <link href="css/adminButtonStyle.css"               rel="stylesheet">
 
    <link href="jquery/jquery-ui.css" rel="stylesheet"> 

    <script src="http://code.jquery.com/jquery-1.7.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>

	<STYLE TYPE="text/css" media="all">
	.ui-autocomplete 
	{ 
	    position: absolute; 
	    cursor:   default; 
	    height:     235px;       /* 100px;  200px;  */
	    overflow-y: scroll; 
	    overflow-x: hidden;
	}
	</STYLE>


<script type="text/javascript">
$(document).ready(function() {
    $("input#autoText").autocomplete({
				        width:          300,
				        max:             10,
				        delay:          100,
				        minLength:        1,
				        autoFocus:     true,
				        cacheLength:      1,
				        scroll:         true,
				        highlight:     false,
				        source: function(request, response) 
				        {
				            $.ajax(
							      {
							             url:          "/CelebrityPhonebook/StaffNameAndDepartmentCompletion",
							             dataType:     "json",
							             data:         request,
							             success:      function( data, textStatus, jqXHR) 
							                           {
							                                     console.log( data);
							                                     var items = data;
							                                     response(items);
							                            },
							             error:         function(jqXHR, textStatus, errorThrown)
							                            {
							                                     console.log( textStatus);
							                            }
				                    }
				                    );
				        }
     });
});
       
</script>

<style>
	.demoHeaders 
	{
		margin-top: 2em;
	}
	#dialog-link 
	{
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon 
	{
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons 
	{
		margin: 0;
		padding: 0;
	}
	#icons li
	 {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon
	 {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay 
	{
		position: absolute;
	}
	select 
	{
		width: 200px;
	}
</style>
</head>

<body>
		<div class="centerPage"> 
			       <div class="headerToTheLeft"> 
                        Phonebook
	              </div> 

			  <div class="mainLeft">
			       <div class="mainLeftFirstBox">
			            Search by Name or Department
 			       </div>

				   <div class="mainLeftSecondBox">
				         <div class="searchTextPosition">
			                  <span id="spanautoText">		                       
								       <input id="autoText"  maxlength="20" name="staffname" value="" class="searchTextInput">
		                      </span>
					     </div>
				         <div class="searchButtonPosition">
							   <span id="firstTextButton" class="searchButton">
							         Search
							   </span>
					     </div>
 			       </div>

				   <div class="mainLeftThirdBox">
				        <div class="mainLeftThirdBoxTextArea">
						     All University Extensions starting    <br/>
							 with "5", "6", "7" or "8" may be      <br/>
						     called directly from outside by the   <br/>
							 prefix "700". The full international  <br/>
							 code for extension 5000 is thus       <br/>
							 +353 1 7005000.                       <br/>
					     </div>					  
 			       </div>

				   <div class="mainLeftFourthBox"> 
			              <span id="secondTextButton" class="showPhonebookButton">Administrator Login</span> 
 			       </div>                                                       
 			 </div>

			  <div class="mainRight">
			              		
                         <span id="txtHint">
                               <jsp:include page="AllStaffDetails.jsp"></jsp:include>
                         </span>
                         
 			 </div>
		</div>
   <script>

document.getElementById("firstTextButton").onclick = function() { displayStaffOrDisplayDepartThatMatchStringEntered() };

document.getElementById("secondTextButton").onclick = function() { displayAdminLogin() };


var xmlHttp

/****************************************************************************************************************************/
function displayStaffOrDisplayDepartThatMatchStringEntered()
{       
	var str =  document.getElementById("autoText").value;
	
    if (str.length >= 1)
    { 
	       	var url="/CelebrityPhonebook/CreateTableOfStaff?sid=" + Math.random() + "&staffNameOrDepartmentEntered=" + str
	        xmlHttp=GetXmlHttpObject(stateChanged)
	        xmlHttp.open("GET", url , true)
	        xmlHttp.send(null)
    } 
    else
    { 
        document.getElementById("txtHint").innerHTML="<br><br><br><br><h1>&nbsp;&nbsp;&nbsp;&nbsp; Enter a name in the text box and then click the Search button </h1>";
                                                                                                    
    } 
} 
/****************************************************************************************************************************/
function displayAStaffMembersDetails(parameterStaffId)
{                  
     var url="/CelebrityPhonebook/CreateStaffDetails?sid=" + Math.random() + "&staffIdParam=" + parameterStaffId
     xmlHttp=GetXmlHttpObject(stateChanged)
     xmlHttp.open("GET", url , true)
     xmlHttp.send(null)
} 
/****************************************************************************************************************************/
function displayStaffMembersInADepartment(parameterStaffDepartment)
{       
     var url="/CelebrityPhonebook/CreateDepartmentDetails?sid=" + Math.random() + "&staffDepartParam=" + parameterStaffDepartment
     xmlHttp=GetXmlHttpObject(stateChanged)
     xmlHttp.open("GET", url , true)
     xmlHttp.send(null)
} 
/****************************************************************************************************************************/
function displayAdminLogin()
{       
    document.getElementById('txtHint').innerHTML = "<div class=\"mainRightCenterSection\">"+
                                                         "<form  method=\"POST\" action=\"javascript:displayValidateAdminUsingMySQL()\" name=\"myform\">"+ 
                                                                       
                                                                "<div class=adminUsernameLabel>"+
                                                                      "Username: "+
                                                                "</div>"+
                               
                                                                "<div class=adminUsernameText>"+  
                                                                      "<input id=\"adminUsernameTextId\"  maxlength=\"15\" name=\"adminusername\" value=\"\" class=\"adminLoginTextInput\">"+
                                                                "</div>"+
                                                                      
                                                                "<div class=adminPasswordLabel>"+
                                                                        "Password: "+
                                                                "</div>"+
                                                                 "<div class=adminPasswordText>"+
                                                                        "<input id=\"adminPasswordTextId\" type=\"password\" name=\"adminpassword\"  value=\"\" class=\"adminLoginTextInput\"/>"+
                                                                "</div>"+
                                                                 "<div class=adminSubmitButtonDiv>"+     
                                                                        "<input type=\"submit\" value=\"Login\" class=\"adminSubmitButton\"  />"+
                                                                 "</div>"+                                                       
                                                          "</form>"+  
                                                "</div>";  

} 
/****************************************************************************************************************************/
function displayValidateAdminUsingMySQL()
{      
	 var adminUsernameParam =  document.getElementById("adminUsernameTextId").value
	 
	 var adminPasswordParam =  document.getElementById("adminPasswordTextId").value;
	
	 var url="/CelebrityPhonebook/ValidateAdminUsingMySQL?sid=" + Math.random()+"&adminUsernameParam="+adminUsernameParam+"&adminPasswordParam="+adminPasswordParam
	
     xmlHttp=GetXmlHttpObject(stateChanged)
     xmlHttp.open("POST", url , true)
	 xmlHttp.send(null) 
} 
/****************************************************************************************************************************/
function displayAddAStaffMembers()
{    
	  var url="/CelebrityPhonebook/AddANewStaffMember?sid=" + Math.random()
			
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
} 
/****************************************************************************************************************************/
function displayEditAStaffMemberInstructions()
{    
	  var url="/CelebrityPhonebook/EditAStaffMemberInstructions?sid=" + Math.random()
	
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
} 
/****************************************************************************************************************************/
function displayRemoveAStaffMemberInstructions()
{    
	  var url="/CelebrityPhonebook/RemoveAStaffMemberInstructions?sid=" + Math.random()
	
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
}
/****************************************************************************************************************************/
function displayLogout()
{    
	  var url="/CelebrityPhonebook/Logout?sid=" + Math.random()
	
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
}
/****************************************************************************************************************************/
function removeStaffMemberWithStaffId(parameterStaffId)
{    
	  var url="/CelebrityPhonebook/RemoveStaffMemberWithStaffId?sid=" + Math.random() + "&staffIdParam=" + parameterStaffId
	
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
}
/****************************************************************************************************************************/
function editStaffMemberWithStaffId(parameterStaffId)
{    
	  var url="/CelebrityPhonebook/EditStaffMemberWithStaffId?sid=" + Math.random() + "&staffIdParam=" + parameterStaffId
	
      xmlHttp=GetXmlHttpObject(stateChanged)
	  xmlHttp.open("POST", url , true)
      xmlHttp.send(null)  
}
/****************************************************************************************************************************/
function stateChanged() 
{ 
	  if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
	  { 
	 	  document.getElementById("autoText").value = "";
	      document.getElementById("txtHint").innerHTML=xmlHttp.responseText    //  in the servlet /myapp/Search it has out.println(responsetext);
	  } 
} 
/****************************************************************************************************************************/
function GetXmlHttpObject(handler)
{ 
	 var objXmlHttp=null
	 // Just going to deal with MSIE and Mozilla for the sake of brevity
	 if (navigator.userAgent.indexOf("MSIE")>=0)
	 { 
	     var strName="Msxml2.XMLHTTP"
	     if (navigator.appVersion.indexOf("MSIE 5.5")>=0)
	     {
	         strName="Microsoft.XMLHTTP"
	     } 
	     try
	     { 
	         objXmlHttp=new ActiveXObject(strName)
	         objXmlHttp.onreadystatechange=handler 
	         return objXmlHttp
	     } 
	     catch(e)
	     { 
	         /** This gets caught if Scripting for ActiveX isn't enabled as a possibility **/
	         return 
	     } 
	 }
	
	 if (navigator.userAgent.indexOf("Mozilla")>=0)
	 {
	     objXmlHttp=new XMLHttpRequest()
	     objXmlHttp.onload=handler
	     objXmlHttp.onerror=handler 
	     return objXmlHttp
	 }
}

</script>
</body>
</html>
