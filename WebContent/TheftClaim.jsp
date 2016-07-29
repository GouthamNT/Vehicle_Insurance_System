<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TheftClaim</title>
<title>jQuery UI Datepicker - Display month &amp; year menus</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
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

<H2>THEFT CLAIM</H2>
<%
String msg="";
 msg=(String)request.getAttribute("mandatory");
%>
<form method=post action="TheftClaimController">
<font color="red">
<% if(msg!=null){
out.print(msg);
} %>
<c:if  test='${requestScope.error.diff ne null}'>
${requestScope.error.diff}
</c:if>
</font>
<br>
<table>
<tr>
<td>Username</td>
<td>:</td>
<td><input type=text class=tb5 value=${sessionScope.theft.username} readonly></td>
</tr>
<tr></tr>
<tr>
<td>Vehicle Manufacturer</td>
<td>:</td>
<td><input type=text class=tb5 value=${sessionScope.theft.manufacturer} readonly></td>
</tr>
<tr></tr>
<tr>
<td>Model</td>
<td>:</td>
<td><input type=text class=tb5 value=${sessionScope.theft.model} readonly></td>
</tr>
<tr></tr>
<tr>
<td>Engine Number</td>
<td>:</td>
<td><input type=text class=tb5 value=${sessionScope.theft.engineno} readonly></td>
</tr>
<tr></tr>
<tr>
<td>Total Amount</td>
<td>:</td>
<td><input type=text class=tb5 value=${sessionScope.theft.amount} readonly></td>
</tr>
<tr></tr>
<tr>
<td>Date of Theft</td>
<td>:</td>
<td><input type=text class=tb5 id="datepicker" name=dtheft><br></td>
<td>
<c:if  test='${requestScope.error.dtheft ne null}'>
${requestScope.error.dtheft}
</c:if>
</td>
</tr>
<tr></tr>
<tr>
<td>Date of Complaint</td>
<td>:</td>
<td><input type=text class=tb5 id="datepicker2" name=dcomplaint ><br></td>
<td>
<c:if  test='${requestScope.error.dcomplaint ne null}'>
${requestScope.error.dcomplaint}
</c:if>
</td>
</tr>
<tr></tr>
<tr>
<td>FIR Number</td>
<td>:</td>
<td><input type=text class=tb5 name=firno ><br></td>
<td>
<c:if  test='${requestScope.error.firno ne null}'>
${requestScope.error.firno}
</c:if>
</td>
</tr>
<tr></tr>
<tr>
<td>Police Station Branch</td>
<td>:</td>
<td><input type=text class=tb5 name=psbranch></td>
<td><c:if  test='${requestScope.error.psbranch ne null}'>
${requestScope.error.psbranch}
</c:if>
</td>
</tr>
<tr></tr>
<tr>

<tr>
<td></td>
<td><input type=submit class=myButtonNormal value=CLAIM name=claim></td>
<td><input type=reset class=myButtonNormal  value=RESET name=reset></td>
</table>
</form>

<script>
  $(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
  });
  
  $(function() {
	    $( "#datepicker2" ).datepicker({
	      changeMonth: true,
	      changeYear: true
	    });
	  });
  </script>

</div>

</body>
</html>