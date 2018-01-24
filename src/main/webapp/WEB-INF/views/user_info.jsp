<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>User info page</title>
	<link href="<c:url value="/resources/css/mix.css" />" rel="stylesheet">
	
	<spring:message code="client_name" var="client_name"/>
    <spring:message code="client_surname" var="client_surname"/>
    <spring:message code="client_email" var="client_email"/>
    <spring:message code="client_phone" var="client_phone"/>
    <spring:message code="login_text" var="client_login"/>
    <spring:message code="password_text" var="client_password"/>
    <spring:message code="registration" var="registration"/>

</head>
<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="active_catalogue_button" value="false" />
</jsp:include>

<table border="0" width="100%">
<tr>
<td width="100%">
<form:form action="update_user" commandName="user" method="POST">
<table border="0" width="100%">
	<tr align="left">
            <td width="200">
            	<form:input path="name" size="26"/></td>
            <td width="200">
            	<form:label path="name">${client_name}</form:label></td>
            <td align="center" rowspan="8">
            <form:errors path="message" />
            	<p>${message}</p>
            </td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="name" cssClass="error"></form:errors></td>
        </tr>
        
        <tr align="left">
            <td width="200">
                <form:input path="surname" size="26"/></td>
            <td width="200">
            	<form:label path="surname">${client_surname}</form:label></td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="surname" cssClass="error"></form:errors></td>
        </tr>
        
        <tr align="left">
            <td width="200">
                <form:input path="email" size="26"/></td>
            <td width="200">
            	<form:label path="email">${client_email}</form:label></td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="email" cssClass="error"></form:errors></td>
        </tr>
        
        <tr align="left">
            <td width="200">
                <form:input path="phone" size="26"/></td>
            <td width="200">
            	<form:label path="phone">${client_phone}</form:label></td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="phone" cssClass="error"></form:errors></td>
        </tr>
        
        <tr align="left">
            <td width="200">
                <form:input path="login" size="26"/></td>
            <td width="200">
            	<form:label path="login">${client_login}</form:label></td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="login" cssClass="error"></form:errors></td>
        </tr>
        
        <tr align="left">
            <td width="200">
                <form:input path="password" size="26"/></td>
            <td width="200">
            	<form:label path="password">${client_password}</form:label></td>
        </tr>
        <tr align="left">
			<td width="200">
            	<form:errors path="password" cssClass="error"></form:errors></td>
        </tr>
        
    </table>

    <br>

    <input class="new" type="submit" value="Обновить данные" style="width: 200Px">
</form:form>
</td>
		<jsp:include page="right_menu.jsp">
        	<jsp:param name="linked_page" value="user_info" />
        </jsp:include>
        </tr>
        </table>

</body>
</html>