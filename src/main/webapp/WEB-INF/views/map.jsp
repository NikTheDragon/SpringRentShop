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
	<jsp:param name="active_map_button" value="false" />
</jsp:include>

<br>

	<table border="0" width="100%">
		<tr>
			<td>
			
			<p>Наш адрес: Ул. Якубова д 14</p>
			<p>Проезд троллейбусом 36 или автобусом 127 до останвки Серебрянка 3</p>
			<p>Телефон 8-029-0000000</p>
			<img src="<c:url value="/resources/img/map.jpg"/>" />
			</td>
		</tr>
	</table>

</body>
</html>
