<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Cart</title>
<link href="<c:url value="/resources/css/mix.css" />" rel="stylesheet">

</head>
<body>

<jsp:include page="shop_logo.jsp">
	<jsp:param name="active_main_button" value="true" />
	<jsp:param name="active_catalogue_button" value="false" />
</jsp:include>
	
	<br>

	<table border="0" width="100%">
    <tr>
        <td width="100%" valign="top">
			<c:forEach var="field" items="${cart}">
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
							<form action="rent_item" method="POST">
								<input type="hidden" name="itemID" value="${field.id}" />
								<input type="hidden" name="userID" value="${user.id}" />
								<p>срок проката (дней)</p>
								<select name="days" size="1">
									<option value="30">30</option>
									<option value="60">60</option>
									<option value="90">90</option>
								</select><br>
								<input class="new" type="submit" name="cart" value="Оформить" /> 
							</form>
							<form action="delete_item" method="POST">
								<input type="hidden" name="itemID" value="${field.id}" />
								<input type="hidden" name="client_id" value="${user.id}" />
								<input class="new" type="submit" name="cart" value="X" />
							</form>
						</td>
					</tr>
				</table>
				<br>
			</c:forEach>
			</td>
			
		<jsp:include page="right_menu.jsp">
        	<jsp:param name="linked_page" value="show_cart" />
        </jsp:include>
        
    </tr>
    
</table>

</body>
</html>