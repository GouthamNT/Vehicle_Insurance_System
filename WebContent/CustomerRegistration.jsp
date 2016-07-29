<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Registration</title>
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


<div class=boxcontentdef>
<H2>CUSTOMER REGISTRATION</H2>
<form method=post action=CustomerRegisterController>
<div class="cr">
<table>
<tr>
<td>Name</td>
<td>:</td>
<td><input type=text class=tb5  name=CusName value='${requestScope.customer.cusName}'></td>
<td><font color="red"><c:if  test='${requestScope.err.username ne null}'>
${requestScope.err.username}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Password</td>
<td>:</td>
<td><input type=password class=tb5 name=CusPassword value='${requestScope.customer.cusPassword}'></td>
<td><font color="red"><c:if  test='${requestScope.err.pwd ne null}'>
${requestScope.err.pwd}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Re-Type Password</td>
<td>:</td>
<td><input type=password class=tb5 name=CusRe_password value='${requestScope.customer.cusRe_password}'></td>
<td><font color="red"><c:if  test='${requestScope.err.pwd2 ne null}'>
${requestScope.err.pwd2}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Address</td>
<td>:</td>
<td><textarea rows="3" cols="20" class=tbta name=CusAddress >${requestScope.customer.cusAddress}</textarea></td>
<td><font color="red"><c:if  test='${requestScope.err.address ne null}'>
${requestScope.err.address}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>City</td>
<td>:</td>
<td><input type=text class=tb5 name=CusCity value='${requestScope.customer.cusCity}'></td>
<td><font color="red"><c:if  test='${requestScope.err.city ne null}'>
${requestScope.err.city}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>State</td>
<td>:</td>
<td><input type=text class=tb5 name=CusState value='${requestScope.customer.cusState}'></td>
<td><font color="red"><c:if  test='${requestScope.err.state ne null}'>
${requestScope.err.state}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Country</td>
<td>:</td>
<td><input type=text class=tb5 name=CusCountry value='${requestScope.customer.cusCountry}'></td>
<td><font color="red"><c:if  test='${requestScope.err.country ne null}'>
${requestScope.err.country}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Pincode</td>
<td>:</td>
<td><input type=text class=tb5 name=CusPincode value='${requestScope.customer.cusPincode}'></td>
<td><font color="red"><c:if  test='${requestScope.err.pin ne null}'>
${requestScope.err.pin}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Email</td>
<td>:</td>
<td><input type=text class=tb5 name=CusEmail value='${requestScope.customer.cusEmail}'></td>
<td><font color="red"><c:if  test='${requestScope.err.email ne null}'>
${requestScope.err.email}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Gender</td>
<td>:</td>
<c:choose>
<c:when test="${requestScope.customer.cusGender =='Male'}">
<td><input type="radio" name=CusGender value=Male checked>Male
<input type="radio" name=CusGender value=Female >Female </td>
</c:when>
<c:when test="${requestScope.customer.cusGender =='Female'}">
<td><input type="radio" name=CusGender value=Male >Male
<input type="radio" name=CusGender value=Female checked>Female </td>
</c:when>
<c:otherwise>
<td><input type="radio" name=CusGender value=Male >Male
<input type="radio" name=CusGender value=Female >Female </td>
</c:otherwise>
</c:choose>
<td><font color="red"><c:if  test='${requestScope.err.Contact ne null}'>
${requestScope.err.gender}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Contact No.</td>
<td>:</td>
<td><input type=text class=tb5 name=CusContact_no value='${requestScope.customer.cusContact_no}'></td>
<td><font color="red"><c:if  test='${requestScope.err.Contact ne null}'>
${requestScope.err.Contact}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td>Date of Birth</td>
<td>:</td>
<td><input type=date class=tb5 name=CusDate_of_birth value='${requestScope.customer.cusDate_of_birth}'></td>
<td>
<font color="red">
<c:if  test='${requestScope.err.age ne null}'>
${requestScope.err.age}
</c:if></font></td>
</tr>
<tr></tr>
<tr>
<td></td>
<td></td><td><input type=submit class=myButtonNormal value=REGISTER>
<a href=CustomerRegistration.jsp class=myButtonNormal >RESET</a></td>
</tr>
</table>
</div>
</form>

</div>

</body>
</html>
