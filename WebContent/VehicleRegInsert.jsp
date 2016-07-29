<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle Registration successful!</title>
  <link rel="icon" href="icon2.png" type="image/x-icon">
</head>
<body>
<table class="one">
<tr>
<td><div class="logo">
<img src="VIS.png"alt="logo" >
</div></td>

<td><div class="title">
<h1>Vehicle Insurance System</h1>
</div></td>
<td><div class="logout">
<a href=Index.jsp class="logoutbutton">Logout</a>
</div></td>
</tr>
</table>

<div class="boxedTab"><div class="tabrowAd">
<ul>
<li><a href=AdminIndex.jsp class="mybutton">HOME</a></li>
<li><a href=VehicleRegistration.jsp class="mybuttonVr">VEHICLE REGISTRATION</a></li>
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
<H2>VEHICLE REGISTRATION</H2>
<center><h3>Vehicle Registered Successfully..!</h3></center>

<form method=post action=index.jsp>
<div class="vr">
<table>
<tr>
 <td><b>Customer ID</b></td>
<td>:</td>
<td><input type=text class=tb5 name=cid value=${requestScope.pop[0]} readonly></td><td>
 </tr>
<tr></tr>
<tr>
<td><b>Vehicle registered in the name of</b></td>
<td>:</td>
<td><input type=text class=tb5 name=uname value=${requestScope.pop[1]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle Registration state</b></td>
<td>:</td>
<td><input type=text class=tb5 name=registering_state value=${requestScope.pop[2]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle Class</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[3]} readonly>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Vehicle type</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[4]} readonly>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Manufacturer</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[5]} readonly>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Model</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[6]} readonly>
</td>
</tr>
<tr></tr>
<tr>
<td><b>Engine number</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[7]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>Year of make</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[8]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>Registering location</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[9]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>Price</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[10]} readonly></td>
</tr>
<tr></tr>
<tr>
<td><b>DOP</b></td>
<td>:</td>
<td><input type=text class=tb5 value=${requestScope.pop[11]} readonly></td>
</tr>
<tr></tr>
<tr>
<td>
<b>POLICY NO </b>
</td>
<td>:</td>
<td><b>${requestScope.pop[12]}</b>
</td>
</tr>
<tr>
<td>
<b>PREMIUM AMOUNT</b></td> <td>:</td>
<td>
<b>${requestScope.pop[13]}</b></td>
</tr>
<tr>
<td>
<b>MATURITY DATE </b><td>:</td><td><b>${requestScope.pop[14]}</b></td>
</tr>
<tr></tr>
</table>
</div>
</form>
</div>

</body>
</html>