<%--
  Created by IntelliJ IDEA.
  User: hory314
  Date: 20.11.18
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form:form method="post" modelAttribute="newSpringPerson">
        <form:input path="login" placeholder="Podaj login"/>
        <form:input path="email" placeholder="Podaj email"/>
        <form:input path="password" placeholder="Podaj password"/>
        <input type="submit">
    </form:form>
</body>
</html>
