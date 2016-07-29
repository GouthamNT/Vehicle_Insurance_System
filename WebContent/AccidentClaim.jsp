<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
            <SCRIPT TYPE="text/javascript">
/* Function called on change event of Dept Drop Down */ 
function populateWeightage() {

var selectedIndexVal = document.getElementById("accidenttype").selectedIndex;
var selectedType =document.getElementById("accidenttype").options[selectedIndexVal].value;
callAjaxFunction(selectedType);
}
/* function called to do Ajax Request */
function callAjaxFunction(selectedType){
var url="AccidentClaimController?weight="+selectedType;
var xmlHttp;
if (window.XMLHttpRequest) { // Mozilla, Safari, ...
var xmlHttp = new XMLHttpRequest();
} else if (window.ActiveXObject) { // IE
var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
xmlHttp.open('GET', url, true);
xmlHttp.onreadystatechange = function() {
if (xmlHttp.readyState == 4) {
updatepage(xmlHttp.responseText);
}
}
xmlHttp.send(url);
}
/* Call back function of Ajax Response*/
function updatepage(response){
//alert(str);
document.getElementById("Weightage").innerHTML = response;
}
</SCRIPT>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACCIDENT CLAIM</title>
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

<div class="boxedTab"><div class="tabrow">
<ul>
<li><a href=IndexController class="myButton">HOME</a></li>
<li><a href=Pay_Register.jsp class="mybutton">PAY PREMIUM</a></li>
<li><a href=ClaimPolicy.jsp class="mybutton">CLAIM POLICY</a></li>
<li><a href=AboutUs class="myButton">ABOUT US</a></li>
<li><a href=ContactUs class="myButton">CONTACT US</a></li>
</ul>
</div></div>

<div class=boxleft>
Benefits of Vehicle Insurance
<ul>
<li>Coverage against loss or damage to the insured vehicle.</li>
<li>Coverage against loss or damage to your vehicle caused by accident, theft, 
fire, explosion, self ignition, lightning, riots, strikes or act of terrorism, natural calamities.</li>
<li>Coverage against financial liability caused by injury/death of a third party or damage to the property.</li>
<li>Personal accident cover.</li>
</ul>
</div>
<div class=boxcontentdef>
<H2>ACCIDENT CLAIM</H2>
<%
String msg="";
 msg=(String)request.getAttribute("errmsg");
%>
<form action="AccidentClaimController" method=post>
<font color="red">
<% if(msg!=null){
out.print(msg);
} %>
</font>
<table>
<tr><td>Username :</td><td>:</td><td><input type=text class=tb5 value='${sessionScope.accident.username}' readonly></td></tr>
</table>
<table>
<tr><td>Total Amount :</td><td>:</td><td><input type=text class=tb5 name =amount value='${sessionScope.accident.amount}' readonly></td></tr>
</table>
<table>
<tr><td>Accident Type : 
<select class=tb name="AccidentType" id="accidenttype" onchange="populateWeightage();">
<option value="default">Select</option>
<option value=Fire>Fire</option>
<option value=Roadaccident>Road accident</option>
<option value=MaliciousActs>Malicious Acts</option>
<option value=Self-destruction>Self-destruction</option>
<option value=PartFailure>Part Failure</option>
</select></td>
</tr>
</table>
<table>
<tr><td>Accident Proof :</td></tr><tr><td><input type="file" name="image"></td></tr>
</table>
<table>
<tr><td>Weightage</td><td>:</td>
<td><div id=Weightage></div></td>
<td></td>
</tr>
</table>
<table>
<tr><td><input type=submit class=myButtonNormal value="Claim" name=claim></td>
<td><input type=reset class=myButtonNormal value="Reset"></td>
</tr>
</table>
</form>
</div>
</body>
</html>