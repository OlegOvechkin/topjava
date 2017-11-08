<%--
  Created by IntelliJ IDEA.
  User: legoprog
  Date: 11/7/17
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">resp: ${title}</h1>
<table border="1" align="center">
    <tr>
        <th>Date</th>
        <th>descripyion</th>
        <th>Calories</th>
        <th>isExceed</th>
    </tr>
    <c:forEach items="${mealList}" var="meal">
        <c:choose>
            <c:when test="${meal.exceed}">
                <c:set var="varStyle" value="color: red"/>
            </c:when>
            <c:when test="${!meal.exceed}">
                <c:set var="varStyle" value="color: green"/>
            </c:when>
            <c:otherwise>
                <c:set var="varStyle" value="color: black"/>
            </c:otherwise>
        </c:choose>
        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
        <tr style="${varStyle}">
            <td>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
            </td>
            <td>
                <c:out value="${meal.description}"/>
            </td>
            <td>
                <c:out value="${meal.calories}"/>
            </td>
            <td>
                <c:out value="${meal.exceed}"/>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
