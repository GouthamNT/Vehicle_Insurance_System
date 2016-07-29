<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Claim Policy</title>
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
<div class=boxcontent>
<h2><font color="red">Claimed processed successfully</font></h2>
<table>
<tr>
<td><font color="red">Amount</font></td>
<td>:</td>
<td>${sessionScope.amount}</td>
</tr>
<tr></tr>
<tr>
<td><font color="red">ClaimID</font></td>
<td>:</td>
<td>${sessionScope.ID}</td>
</tr>
</table>
</div>
</body>
</html>