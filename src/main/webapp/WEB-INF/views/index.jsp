<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sport Equipment Rent Shop</title>
    <link href="<c:url value="/resources/css/mix.css"/>" rel="stylesheet">

    <spring:message code="registration" var="reg_button"/>
    <spring:message code="login" var="login_button"/>
    <spring:message code="login_text" var="login_text"/>
    <spring:message code="password_text" var="password_text"/>
    <spring:message code="main_button" var="main"/>
    <spring:message code="logo_map" var="logo_map"/>
    <spring:message code="logo_about" var="logo_about"/>

</head>

<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="linked_page" value="index_page" />
</jsp:include>

<br>

<table border="0" width="100%">
    <tr>
        <td width="100%" align="center">
            <c:if test="${message == 'login_incorrect'}">
                <p>пользователи с таким логином и паролем не найдены</p>
            </c:if>
        </td>

        <td bgcolor="d0d0d0">
            <form action="Controller" method="post">
                <table border="0" bgcolor="d0d0d0" width="100%">
                    <tr align="center">
                        <td width="100%"><input type="text" name="login" placeholder="login" value="" size="20"><br>${login_text}</td>
                    </tr>
                    <tr align="center">
                        <td width="100%"><input type="password" name="password" placeholder="password" value="" size="20"><br>${password_text}
                        </td>
                    </tr>
                </table>


                <table border="0" bgcolor="d0d0d0" width="100%">
                    <tr align="center">
                        <td>
                            <input type="hidden" name="command" value="login_client"/>
                            <input class="new" type="submit" value="${login_button}" style="width: 120Px">
                        </td>
                    </tr>
                </table>
            </form>

            <form:form action="reg_client" method="POST">
                <table border="0" bgcolor="d0d0d0" width="100%">
                    <tr align="center">
                        <td align="center">
                            <input class="new" type="submit" value="${reg_button}" style="width: 120Px">
                        </td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>

</body>
</html>
