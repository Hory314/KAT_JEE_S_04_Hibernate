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
        <th>Tytuł</th>
        <th>Wydawca</th>
        <th>Autorzy</th>
        <th>Ocena</th>
        <th>Operacje</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.publisher.name}</td>
            <td>
                <ul>
                <c:forEach var="author" items="${book.authors}">
                    <li>${author.firstName} ${author.lastName}</li>
                </c:forEach>
                </ul>
            </td>
            <td>${book.rating}</td>
            <td><a href="/books/edit/${book.id}">Edytuj</a> | <a href="/books/delete/confirm/${book.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
