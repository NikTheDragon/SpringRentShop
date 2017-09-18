<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu page</title>
</head>
<body>



	<td valign="top" align="center">
        <p align="center">Здравствуйте</p>
        <p align="center">${user.name}</p>
        <p align="center">&nbsp;</p>
        <p align="center">Меню:</p>
        
        <form action="Controller" method="post">
            <p><input class="new" type="submit" name="B1" value="Личный кабинет" style="width: 120Px"></p>
        </form> 
        
        <c:choose>
			<c:when test="${param.linked_page == 'client_items'}">
				<p><input class="old" type="submit" name="B2" value="Мои товары" style="width: 120Px"></p>
			</c:when>
			<c:otherwise>
				<form action="user_items" method="post">
					<input type="hidden" name="client_id" value="${user.id}" />
					<p><input class="new" type="submit" name="B2" value="Мои товары" style="width: 120Px"></p>
				</form>
			</c:otherwise>
		</c:choose> 
		
		<c:choose>
			<c:when test = "${param.linked_page == 'show_cart'}">
            	<p><input class="old" type="submit" name="B3" value="Корзина" style="width: 120Px"></p>
        	</c:when>
        	<c:otherwise>
            	<form action="user_cart" method="POST">
    	        	<p><input class="new" type="submit" name="B3" value="Корзина" style="width: 120Px"></p>
        		</form>
	        </c:otherwise>
        </c:choose>
        
        <form action="<c:url value='/logout' />" method="POST">
            <p><input class="new" type="submit" value="Выход" style="width: 120Px"></p>
        </form>
	</td>

</body>
</html>