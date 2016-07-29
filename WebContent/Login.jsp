<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
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
<h2>LOGIN</h2>

<form method=post action=LoginController>
<font color="red">
<c:if  test='${requestScope.errmsg ne null}'>
<b>${requestScope.errmsg}</b>
</c:if>
</font>
<c:if  test='${requestScope.message ne null}'>
<b>${requestScope.message}</b>
</c:if>
<table>
<tr>
<td>USER ID.</td>
<td>:</td>
<td><input type=text class=tb5 name=uname></td>
</tr>
<tr></tr>
<tr>
<td>PASSWORD</td>
<td>:</td>
<td><input type=password class=tb5 name=pwd></td>
</tr>
<tr></tr>
<tr>
<td></td>
<td></td>
<td><input type=submit value=Login class=myButtonNormal name=login></td>
</tr></table>
</form>
</div>
<div class=boxright>
<br>
<br>
New User?? Register here
<br>
<br>
<a href=CustomerRegistration.jsp class=dpbutton>Register</a>
</div>
</body>
</html>
