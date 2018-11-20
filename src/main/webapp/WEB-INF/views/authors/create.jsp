<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="newAuthor">
    <form:input path="firstName" placeholder="Podaj imię"/>
    <form:input path="lastName" placeholder="Podaj nazwisko"/>
    <input type="submit">
</form:form>
</body>
</html>
