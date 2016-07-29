<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Direct Pay</title>
</head>
<body>

<table class="one">
<tr>
<td><div class="logo">
<img src="VIS.png"alt="logo" >
</div></td>

<td class=img>
</td>

</tr>
</table>


<div class="boxedTab"><div class="tabrow">
<ul>
<li><a href=Index.jsp class="mybutton">HOME</a></li>
<li><a href=Login.jsp class="mybutton">USER</a></li>
<li><a href=AdminLogin.jsp class="mybutton">ADMIN</a></li>
<li><a href=AboutUs.jsp class="mybutton">ABOUT US</a></li>
<li><a href=ContactUs.jsp class="mybutton">CONTACT US</a></li>
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
<div class=boxcontent>
<form method=get action=PolicyController>
<h3>Payment processed successfully</h3>
<table>
<tr></tr>

<tr>
<td></td>
<td><h4>Payment ID</h4></td>
<td>:</td>
<td><h4>${requestScope.paymentid}</h4></td>
</tr>
<tr><td></td>
<td><h4>Next Due Date</h4></td>
<td>:</td>
<td><h4>${requestScope.duedate}</h4></td>
</tr>
<tr>
</tr>
</table>
</form>
</div>
</body>
</html>

