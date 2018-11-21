<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="newPublisher">
    <form:input path="name" placeholder="Podaj nazwę"/>
    <form:input path="nip" placeholder="Podaj NIP"/>
    <form:input path="regon" placeholder="Podaj REGON"/>
    <input type="submit">
</form:form>
</body>
</html>
