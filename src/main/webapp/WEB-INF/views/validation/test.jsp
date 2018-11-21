<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hory314
  Date: 21.11.18
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Błędy walidacji:
<c:forEach items="${validationErrors}" var="error">
    <p>${error.getPropertyPath()} : ${error.getMessage()}</p>
</c:forEach>
</body>
</html>
