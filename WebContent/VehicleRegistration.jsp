<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
          <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle Registration</title>
 <link rel="icon" href="VIS.png" type="image/x-icon">
 
 <link href="main.css" rel="stylesheet">
 
 <SCRIPT TYPE="text/javascript">
/* Function called on change event of Dept Drop Down */ 
function populateManufacturer() {

var selectedIndexVal = document.getElementById("vehtype").selectedIndex;
var selectedManufacturer =document.getElementById("vehtype").options[selectedIndexVal].value;
callAjaxFunction(selectedManufacturer);
}
/* function called to do Ajax Request */
function callAjaxFunction(selectedManufacturer){
var url="PopulateController?manuVal="+selectedManufacturer;
var xmlHttp;
if (window.XMLHttpRequest) { // Mozilla, Safari, ...
var xmlHttp = new XMLHttpRequest();
} else if (window.ActiveXObject) { // IE
var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
xmlHttp.open('GET', url, true);
xmlHttp.onreadystatechange = function() {
if (xmlHttp.readyState == 4)
{
updatepage(xmlHttp.responseText);
}
}
xmlHttp.send(url);
}
/* Call back function of Ajax Response*/
function updatepage(response){
//alert(str);
document.getElementById("popmanufact").innerHTML = response;
}


function populateModel() {
	

	var selectedIndexVal1 = document.getElementById("fillmodel").selectedIndex;
	var selectedModel =document.getElementById("fillmodel").options[selectedIndexVal1].value;
	callAjaxFunction1(selectedModel);
	}
	/* function called to do Ajax Request */
	function callAjaxFunction1(selectedModel)
	{
	var url="PopulateController?modelVal="+selectedModel;
	var xmlHttp;
	if (window.XMLHttpRequest) { // Mozilla, Safari, ...
	var xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
	var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open('POST', url, true);
	xmlHttp.onreadystatechange = function() {
	if (xmlHttp.readyState == 4)
	{
	updatepage1(xmlHttp.responseText);
	}
	}
	xmlHttp.send(url);
	}
	/* Call back function of Ajax Response*/
	function updatepage1(response){
	//alert(str);
	document.getElementById("popmodelfact").innerHTML = response;
	}
	
    function populatestate() {
        var selectedIndexVal2 = document.getElementById("location");
        var selectedlocation =document.getElementById("location").value; 
        callAjaxFunction2(selectedlocation);

        }
        /* function called to do Ajax Request */
        function callAjaxFunction2(selectedlocation){
        var url="VehicleRegister?location="+selectedlocation;
        var xmlHttp;
        if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        var xmlHttp = new XMLHttpRequest();
        } else if (window.ActiveXObject) { // IE
        var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.open('GET', url, true);
        xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
        updatepage2(xmlHttp.responseText);
        }
        }
        xmlHttp.send(url);
        }
        function updatepage2(response)
        {
        document.getElementById("regstate").innerHTML = response;
       }
</script>


 
 
 
</head>
<body>

<table class="one">
<tr>
<td><div class="logo">
<img src="VIS.png"alt="logo" >
</div></td>

<td class=img>
</td>


<td>
<a href=LogoutController class="logoutbutton">Logout</a>
</td>

</tr>
</table>
<div class="boxedTab">
	<div class="tabrowAd">
<ul>
<li><a href=IndexController class="myButton">HOME</a></li>
<li><a href=AdminVehController class="mybuttonVr">VEHICLE REGISTRATION</a></li>
<li><a href=AboutUs class="myButton">ABOUT US</a></li>
<li><a href=ContactUs class="myButton">CONTACT US</a></li>
</ul>
</div>
</div>
<div class=boxcontentdef>
<H2>VEHICLE REGISTRATION</H2>
<c:if  test='${requestScope.message ne null}'>
${requestScope.message}
</c:if>

<form method=post action=VehicleRegister>
<div class="vr">
<table>
<tr>
<td><b>Customer ID</b></td>
<td>:</td>
<td><input type=text class=tb5 name=cid value='${requestScope.vehicle.cid}'><td>
<font color="red"> 
<c:if  test='${requestScope.err_msg ne null}'>
${requestScope.err_msg}
</c:if>
</font>
<font color="red"> 
<c:if  test='${requestScope.error.cid ne null}'>
${requestScope.error.cid}
</c:if>
</font>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle registered in the name of</b></td>
<td>:</td>
<td><input type=text class=tb5 name=uname value='${requestScope.vehicle.name}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.uname ne null}'>
${requestScope.error.uname}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle Registration Address</b></td>
<td>:</td>
<td><textarea rows="3" cols="20" class=tbta name=registering_address>${requestScope.vehicle.registering_address}</textarea></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.registering_address ne null}'>
${requestScope.error.registering_address}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Registration Location</b></td>
<td>:</td>
<td><input type=text class=tb5 name=rlocation  id=location value='${requestScope.vehicle.registering_location}' autocomplete=off onkeyup="populatestate();" onchange="populatestate();"></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.registering_location ne null}'>
${requestScope.error.registering_location}
</c:if>
</font>
</td>
</tr>
<tr></tr>

<tr>
<td><b>Vehicle Class</b></td>
<td>:</td>
<td><select name=vehicle_class class=tb>
<option value=Default>Select</option>
<option value=OWN>Own Vehicle</option>
<option value=Commercial>Commercial</option>
</select></td>
<td>
<font color="red"> 
<c:if  test='${requestScope.error.cls ne null}'>
${requestScope.error.cls}
</c:if>
</font>
</td>

</tr>
<tr></tr>

<tr>
<td><b>Vehicle type</b></td>
<td>:</td>
<td><select name=vehicle_type class=tb id=vehtype onchange="populateManufacturer();" >
<option value=Default>Select</option>
<option value=TW>Two Wheeler</option>
<option value=FW>Four Wheeler</option>
<option value=OT>Others</option>
</select></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.vehicletype ne null}'>
${requestScope.error.vehicletype}
</c:if>
</font></td>
</tr>
<tr></tr>
<tr>
<td><b>Manufacturer</b></td>
<td>:</td>
<td> <div id="popmanufact"> 
 <select  class=tb><option value="Default">-Select-</option></select>
</div></td> <td>
<font color="red"> 
<c:if  test='${requestScope.error.vehiclemanufact ne null}'>
${requestScope.error.vehiclemanufact}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Model</b></td>
<td>:</td>
<td><div id="popmodelfact"> <select  class=tb><option value="Default">-Select-</option></select>
</div>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Engine number</b></td>
<td>:</td>
<td><input type=text class=tb5 name=enumber value='${requestScope.vehicle.engine_number}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.engine_number ne null}'>
${requestScope.error.engine_number}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>RC number</b></td>
<td>:</td>
<td><input type=text class=tb5 name=rcnumber value='${requestScope.vehicle.rcnumber}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.rcnumber ne null}'>
${requestScope.error.rcnumber}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Year of make</b></td>
<td>:</td>
<td><input type=text class=tb5 name=year value='${requestScope.vehicle.year_of_make}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.year ne null}'>
${requestScope.error.year}
</c:if>
<br>
<c:if  test='${requestScope.err ne null}'>
${requestScope.err}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle Registeration State</b></td>
<td>:</td>
<td><div id="regstate"><input type=text class=tb5 name=registering_state value='${requestScope.vehicle.registering_location}' readonly></div></td>
<td>
<!--
<font color="red"> 
<c:if  test='${requestScope.error.registering_state ne null}'>
${requestScope.error.registering_state}
</c:if>
</font>-->
</td>
</tr>
<tr></tr>
<tr>
<td><b>Price</b></td>
<td>:</td>
<td><input type=text class=tb5 name=price value='${requestScope.vehicle.price}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.price ne null}'>
${requestScope.error.price}
</c:if>
<br>
<c:if  test='${requestScope.errr ne null}'>
${requestScope.errr}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td><b>DOP</b></td>
<td>:</td>
<td><input type=date class=tb5 name=dop value='${requestScope.vehicle.dop}'></td>
<td><font color="red"> 
<c:if  test='${requestScope.error.dop ne null}'>
${requestScope.error.dop}
</c:if>
</font>
</td>
</tr>
<tr></tr>
<tr>
<td></td>
<td></td><td><input type=submit class=myButtonNormal value=REGISTER>
<a href=VehicleRegistration.jsp class=myButtonNormal >RESET</a></td>
</tr>
</table>
</div>
</form>
</div>

</body>
</html>