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
					<img src="VIS.png" alt="logo">
				</div></td>

			<td class=img>
</td>
		</tr>
	</table>
<div class="boxedTab">
	<div class="tabrow">
		<ul>
			<li><a href=Index.jsp class="myButton">HOME</a></li>
			<li><a href=Login.jsp class="myButton">USER</a></li>
			<li><a href=AdminLogin.jsp class="myButton">ADMIN</a></li>
			<li><a href=AboutUs.jsp class="myButton">ABOUT US</a></li>
			<li><a href=ContactUs.jsp class="myButton">CONTACT US</a></li>
		</ul>

	</div>
</div>
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
		<H2>DIRECT PAY</H2>
		<form method=get action=PolicyController>
		<input type="hidden" name=net value="card" >
		
			
			<table>
			<c:choose>
				<c:when test='${requestScope.validid ne null}'>
				<input type=hidden name=check value=1>
				<input type=hidden name=amountgiven value='${requestScope.amountgiven}'>
				<tr>
					<td>Policy Id</td>
					<td>:</td>
					<td><input type=text class=tb5 name=policyid value='${requestScope.policyid}' readonly></td>
				<tr></tr>
				<tr>
					<td>Amount</td>
					<td>:</td>
					<td><input type=text class=tb5 name="amount" value='${requestScope.pay.amount}'></td>
				</tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.Amount ne null}'>
${requestScope.error.Amount}
</c:if></td>
</tr>
<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.amountless ne null}'>
${requestScope.amountless}
</c:if></td>
</tr>
				<tr></tr>
				<tr>
					<td>Mode of Payment</td>
					<td>:</td>
					<c:choose>
<c:when test="${requestScope.mode=='Debit_Card'}">
<td><input type="radio" name=Mode_of_payment value=Debit_Card checked><font class=card></font> Debit card</td>
<tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Credit_Card>Credit Card</tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Net_Banking>Net banking</td>
</tr>
</c:when>
<c:when test="${requestScope.mode=='Credit_Card'}">
<td><input type="radio" name=Mode_of_payment value=Debit_Card><font class=card></font> Debit card</td>
<tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Credit_Card checked>Credit Card</tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Net_Banking>Net banking</td>
</tr>
</c:when>
<c:when test="${requestScope.mode=='Net_Banking'}">
<td><input type="radio" name=Mode_of_payment value=Debit_Card><font class=card></font> Debit card</td>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Credit_Card>Credit Card</tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Net_Banking checked>Net banking</td>
</tr>
</c:when>
<c:otherwise>
<td><input type="radio" name=Mode_of_payment value=Debit_Card><font class=card></font> Debit card</td>
<tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Credit_Card>Credit Card</tr>
<tr>
<td></td>
<td></td>
<td><input type="radio" name=Mode_of_payment value=Net_Banking>Net banking</td>
</tr>
</c:otherwise>
</c:choose>
				</tr>
				<tr></tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.mode ne null}'>
${requestScope.error.mode}
</c:if>
</td></tr>
				<tr>
					<td>Contact No.</td>
					<td>:</td>
					<td><input type=text class=tb5 name=contactno value='${requestScope.pay.contactno}'></td>
				</tr>
				<tr><td></td><td></td><td class=err>
<c:if  test='${requestScope.error.contact ne null}'>
${requestScope.error.contact}
</c:if>
</td></tr>
				<tr></tr>
				<tr>
					<td></td><td></td>
					<td><input type=submit class="myButtonNormal" class="myButtonNormal" value=PAY name="submit"> <input
						type=submit class="myButtonNormal" class="myButtonNormal" value=RESET name="submit"></td>
				</tr>
				</c:when>
				<c:otherwise>
				<input type=hidden name=check value=0>
				<tr>
				</tr>
				<tr>
					<td>Policy Id</td>
					<td>:</td>
					<td><input type=text class=tb5 name=policyid value='${requestScope.pay.policyID}'></td>
					<td><input type=submit class="myButtonNormal" value=GO></td>
				</tr>
				<tr><td></td><td></td><td class=err><c:if test='${requestScope.Invalid ne null}'>
			${requestScope.Invalid}
			</c:if></td></tr>
				
				</c:otherwise>
				</c:choose>
			</table>
		</form>
	</div>
</body>
</html>

