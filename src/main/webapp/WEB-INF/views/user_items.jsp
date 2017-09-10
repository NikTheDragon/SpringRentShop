<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>user items page</title>
<link href="<c:url value="/resources/css/mix.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="linked_page" value="index_page" />
</jsp:include>

<br>
	
<table border="0" width="100%">
    <tr>
        <td width="100%" valign="top">
			<c:forEach var="field" items="${items}">
				<table border="1" width="95%">
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
							<form action="Controller" method="post">
							<input type="hidden" name="command" value="return_item" />
							<input type="hidden" name="equipment_id" value="${field.id}" />
							<input type="hidden" name="client_id" value="${user.id}" />   
							<input type="submit" name="return_button" value="Оформить взврат" style="width: 120Px">
							</form>
						</td>
					</tr>
				</table>
			</c:forEach>
			</td>
			
        <jsp:include page="right_menu.jsp">
        <jsp:param name="linked_page" value="client_items" />
        </jsp:include>
        
    </tr>
</table>

</body>
</html>