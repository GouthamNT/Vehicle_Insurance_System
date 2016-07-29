<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import='java.util.Calendar,java.util.Date'%>
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
<li><a href=Index.jsp class="myButton">HOME</a></li>
<li><a href=Login.jsp class="myButton">USER</a></li>
<li><a href=AdminLogin.jsp class="myButton">ADMIN</a></li>
<li><a href=AboutUs.jsp class="myButton">ABOUT US</a></li>
<li><a href=ContactUs.jsp class="myButton">CONTACT US</a></li>
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
<H2>Choose preferred Bank</H2>
<form method=post action="CardController">

<input type=hidden name=policyid value='${requestScope.policyid}'> 
<input type=hidden name=amount value='${requestScope.amount}'> 
<input type=hidden name=mode value='${requestScope.mode}'>
<input type=hidden name=pay_type value='${requestScope.pay_type}'>
<input type=hidden name=actualamount value='${requestScope.actualamount}'>
<table>
<tr></tr>
<tr>
<td>Bank</td>
<td>:</td>

<td><div class=rd><input type="radio" id="radio01" name=Bank value="SBI">
<label for="radio01"><span></span>SBI</label></div></td>
<tr>
<tr>
<td></td>
<td></td>
<td><div class=rd><input type="radio" id="radio02" name=Bank value="HDFC"> <label for="radio02"><span></span>HDFC</label></div></td>
</tr>
<tr>
<td></td>
<td></td>
<td><div class=rd><input type="radio" id="radio03" name=Bank value="ICICI"><label for="radio03"><span></span>ICICI</label></div></td>
</tr>
<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.bankerr ne null}'>
${requestScope.bankerr}
</c:if></td>
</tr>
<tr>
<td></td>
<td></td>
<td><input type=submit class=myButtonNormal value=PROCEED></td>
</tr>
<tr></tr>
</table>
</form>
</div>
</body>
</html>
