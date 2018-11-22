<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="newBook">
    <div style="color: red;">
        <form:errors path="*"/>
    </div>
    <form:input path="title" placeholder="Podaj tytul"/>
    <form:input path="description" placeholder="Podaj description"/>
    <form:input path="pages" placeholder="Podaj liczbę stron"/>
    <form:input path="rating" placeholder="Podaj ocenę"/>
    <form:select items="${publishers}" path="publisher" itemLabel="name" itemValue="id"/>
    <form:select items="${authors}" path="authors" itemLabel="fullName" itemValue="id" multiple="true"/>
    <form:select items="${categories}" path="category" itemLabel="name" itemValue="id"/>
    <input type="submit">
</form:form>
</body>
</html>
