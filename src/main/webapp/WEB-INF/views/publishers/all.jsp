<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Nazwa</th>
        <th>Operacje</th>
    </tr>
    <c:forEach items="${publishers}" var="publisher">
        <tr>
            <td>${publisher.id}</td>
            <td>${publisher.name}</td>
            <td><a href="/publishers/edit/${publisher.id}">Edytuj</a> | <a href="/publishers/delete/confirm/${publisher.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
