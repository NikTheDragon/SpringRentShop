<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="true"%>
<!DOCTYPE>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sport Equipment Rent Shop</title>
    <link href="<c:url value="/resources/css/mix.css"/>" rel="stylesheet">

    <spring:message code="main_button" var="main"/>
    <spring:message code="logo_map" var="logo_map"/>
    <spring:message code="logo_about" var="logo_about"/>

</head>

<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="active_about_button" value="false" />
</jsp:include>

<br>

	<table border="0" width="100%">
		<tr>
			<td>
				<p><h1>Магазин проката спортивного инвентаря</h1></p>
				<p><h4>Автор: Курлович Николай</h4></p>
				<p>Минск 2017</p>
			</td>
		</tr>
	</table>

</body>
</html>
