<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Client page</title>
	<link href="<c:url value="/resources/css/mix.css"/>" rel="stylesheet">
</head>
<body>

	<jsp:include page="shop_logo.jsp">
		<jsp:param name="active_main_button" value="false" />
	</jsp:include>

	<br>

	<table border="0" bgcolor="ffffff" width="100%">
		<tr align="center">
			<td>Категория:
				<form action="user_page" method="get">
					<select name="line" size="1">
						<option value="%">*</option>
						<c:forEach var="line" items="${category}">
							<option value="${line}">${line}</option>
						</c:forEach>
					</select> 
					<input class="new" type="submit" value="выбрать" />
				</form>
			</td>
		</tr>
	</table>

	<table border="0" width="100%">
    <tr>
        <td width="100%" valign="top">
			<c:forEach var="field" items="${equipment}">
				<table border="2" width="95%" bordercolor="D0D0D0">
					<tr align="center">
						<td rowspan="2" width="20%"><img src="<c:url value="/resources/img/${field.img}"/>"></td>
						<td width="20%">Тип: ${field.type}</td>
						<td width="20%">Название: ${field.name}</td>
						<td width="20%">Производитель: ${field.manufacturer}</td>
						<td width="20%">Цена за 30 дней: ${field.price} руб.</td>
					</tr>
					<tr align="center">
						<td colspan="3" width="80%">${field.description}</td>
						<td>
							<form action="add_to_cart" method="POST">
							<c:if test = "${field.owner == '0'}">
								<input type="image" name="itemID" value="${field.id}" src="<c:url value="/resources/img/icon_cart.gif"/>"width="32" height="32">
							</c:if>
							<c:if test = "${field.owner != '0'}">
								 <p align="center">Товар недоступен</p>
							</c:if>
							</form>
						</td>
					</tr>
				</table>
				<br>
			</c:forEach>
			</td>
			
        <jsp:include page="right_menu.jsp">
        	<jsp:param name="linked_page" value="client_page" />
        </jsp:include>
        
    </tr>
</table>
	

</body>
</html>