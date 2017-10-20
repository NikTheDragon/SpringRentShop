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
    <title>Error page</title>
    <link href="<c:url value="/resources/css/mix.css"/>" rel="stylesheet">

    <spring:message code="main_button" var="main"/>
    <spring:message code="logo_map" var="logo_map"/>
    <spring:message code="logo_about" var="logo_about"/>

</head>

<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="active_catalogue_button" value="true" />
</jsp:include>

<br>

	<table border="0" width="100%">
		<tr>
			<td>
				<p><h1>Error page</h1></p>
				<p>${message}</p>
			</td>
		</tr>
	</table>

</body>
</html>
