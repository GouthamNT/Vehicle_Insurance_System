<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import='java.util.Calendar,java.util.Date'%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="main.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card Credentials</title>
</head>
<body>
	<table class="one">
		<tr>
			<td><div class="logo">
					<img src="VIS.png" alt="logo">
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
		<H2>ENTER CARD DETAILS</H2>
		<form method=post action="CardController">
		<input type=hidden name=actualamount value='${requestScope.actualamount}'>
		
			<input type=hidden name=policyid value='${requestScope.user.policyID}'> 
			<input type=hidden name=amount value='${requestScope.user.amount}'> 
			<input type=hidden name=mode value='${requestScope.user.modeofpayment}'>
			<input type=hidden name=pay_type value='${requestScope.pay_type}'>
			<table>
				<tr></tr>
				<tr>
					<td>Card Number</td>
					<td>:</td>
					<td><input type=text class=tb5 name=cardno value='${requestScope.CardStr}'></td>
				</tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.cardno ne null}'>
${requestScope.error.cardno}
</c:if>
</td></tr>
				<tr></tr>
				<tr>
					<td>Name as on Card</td>
					<td>:</td>
					<td><input type=text class=tb5 name=cardname value='${requestScope.cardname}'></td>
				</tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.cardname ne null}'>
${requestScope.error.cardname}
</c:if>
</td></tr>
				<tr></tr>
				<tr>
					<td>Card Validity</td>
					<td>:</td>
					<td><select name=month class=tb>
							<!-- Drop down -->
							<%
								Calendar ca = Calendar.getInstance();
								Date d = new Date();
								ca.setTime(d);
								int month = ca.get(Calendar.MONTH);
								int year = ca.get(Calendar.YEAR);
								for (int i = 1; i <= 12; i++) {
							%>
							<option value=<%=i%>><%=i%></option>
							<%
								}
							%>
					</select> <select name=year class=tb>
							<!-- Drop down -->
							<%
								for (int i = year, j = 0; j <= 6; j++, i++) {
							%>
							<option value=<%=i%>><%=i%></option>
							<%} %>
					</select></td>
					<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.exp ne null}'>
${requestScope.error.exp}
</c:if>
</td></tr>
				<tr></tr>
				<tr>
					<td>CVV No.</td>
					<td>:</td>
					<td><input type=password class=tbCVV name=cvv></td>
				</tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.cvv ne null}'>
${requestScope.error.cvv}
</c:if>
</td></tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type=submit class="myButtonNormal" value=PROCEED>
					<a href=Pay_Card.jsp class="myButtonNormal" >RESET</a></td>
				</tr>
				<tr></tr>
			</table>
		</form>
	</div>
</body>
</html>

