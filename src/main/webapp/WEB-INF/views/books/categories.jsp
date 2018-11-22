<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hory314
  Date: 22.11.18
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Lista kategorii:
<ol>
    <c:forEach var="category" items="${categories}">
        <li><a href="/books/categories/${category.id}">${category.name}</a></li>
    </c:forEach>
</ol>
</body>
</html>
