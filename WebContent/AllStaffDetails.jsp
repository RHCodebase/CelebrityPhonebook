<!doctype html>
<html lang="us">
<head>
	 <meta charset="utf-8">
	 <title>jQuery UI Example Page</title>
	
	 <link href="css/usefulNumbersTableStyle.css"               rel="stylesheet">
	 <link href="css/bodyCenterPageHeaderMainLeftMainRight.css" rel="stylesheet">  
  
    
     <script src="http://code.jquery.com/jquery-1.7.js" type="text/javascript"></script>
     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
   
     <link   href="jquery/jquery-ui.css"    rel="stylesheet">
     <script src="jquery/external/jquery/jquery.js"></script>

     <script src="jquery/jquery-ui.js"></script>


	<style>
	.UsefulTelephoneNumbersBox
    {
		    width:        60%;
			height:       40px;    
		    margin:       auto;
			background:   white; 
			color:        rgb(37, 154, 187);    /* #2587ab; */
			position:     absolute;
			left:         1%;
			top:          10px;
			
			font-family:  Arial,sans-serif;  
            font-size:    32px;
            font-weight:  100;   
    }
	
	.mainRightCenterSectionAccordion 
    {
		    width:        97%;
			height:       700px;    
		    margin:       auto;
			background:   white;        /* pink; */
			color:        black;
			position:     absolute;
			left:         1%;
			top:          90px;
    }
    
   
	</style>
</head>
<body>

      <div class="mainRightCenterSection">
          <div class="UsefulTelephoneNumbersBox"> 
            Useful Telephone Numbers
         </div>
         <div class="mainRightCenterSectionAccordion">  		 
		  <div id="accordion">
			  <h3>Contact</h3>  	
			  <div>
		   	      <table id="usefulNumbersTable"> 
		          <tr>
		                <td class="firstCol">Main Reception:</td>
		                <td class="secondCol">5000 (External: 01 700 5000)</td>
				  </tr>
				  </table>
			   </div>
			   <h3>Emergency</h3>
			   <div>
				  <table id="usefulNumbersTable"> 
				      <tr>
					          <td class="firstCol">DCU Security Emergency:</td>
					          <td class="secondCol">5999 (External: 01 700 5999)</td>
					  </tr>
					  <tr class="alt">
					          <td class="firstCol">DCU Security:</td>
					          <td class="secondCol">8990 (External: 01 700 8990)</td>
					  </tr>
					  <tr>
					          <td class="firstCol">Emergency:</td>
					          <td class="secondCol">999 or 112</td>
					  </tr>
					   <tr class="alt">
					          <td class="firstCol">Garda&iacute; - Santry:</td>
					          <td class="secondCol">01 666 4000</td>
					   </tr>
					   <tr>
					          <td class="firstCol">Garda&iacute; - Ballymun:</td>
					          <td class="secondCol">01 666 4401</td>
					   </tr>
				   </table>
				</div>
			    <h3>Medical</h3>
			    <div>
					<table id="usefulNumbersTable"> 
					    <tr>
				                <td class="firstCol">DCU Medical Centre:</td>
				                <td class="secondCol">5143 (External: 01 700 5143)</td>
				        </tr>
				        <tr class="alt">
				                <td class="firstCol">Beaumont Hospital:</td>
				                <td class="secondCol">01 821 3844</td>
				        </tr>
				        <tr>
				                <td class="firstCol">Mater Hospital:</td>
				                <td class="secondCol">01 830 1122</td>
				        </tr>
				      </table>
				  </div>
			      <h3>Support</h3>
			      <div>
					  <table id="usefulNumbersTable"> 
					        <tr>
				                <td class="firstCol">Victim Support:</td>
				                <td class="secondCol">01 679 8673</td>
				           </tr>
				           <tr class="alt">
				                <td class="firstCol">Samaritans:</td>
				                <td class="secondCol">01 872 7700</td>
				           </tr>
				           <tr>
				                <td class="firstCol">Rape Crisis Centre:</td>
				                <td class="secondCol">01 661 4911</td>
				           </tr>
				           <tr class="alt">
				               <td class="firstCol">Drugs Advisory Centre:</td>
				               <td class="secondCol">01 677 1122</td>
				           </tr>
				           <tr>
				               <td class="firstCol">Gingerbread:</td>
				               <td class="secondCol">01 671 2091</td>
				           </tr>
						    <tr class="alt">
				               <td class="firstCol">Women's Aid Help Line:</td>
				               <td class="secondCol">01 872 3756</td>
				           </tr>
				         </table>
				    </div>
				</div>
       </div>
   </div>

<script>

// $( "#accordion" ).accordion();

 $(function() {
    $( "#accordion" ).accordion({
      heightStyle: "content"
    });
  });


// Hover states on the static widgets
$( "#dialog-link, #icons li" ).hover(
	function() {
		$( this ).addClass( "ui-state-hover" );
	},
	function() {
		$( this ).removeClass( "ui-state-hover" );
	}
);
</script>
</body>
</html>
