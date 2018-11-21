<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hory314
  Date: 21.11.18
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="newProposition" method="post">
    <div style="color: red;">
        <form:errors path="*"/>
    </div>
    <form:input path="title" placeholder="Podaj tytul"/>
    <form:input path="description" placeholder="Podaj description"/>
    <form:input path="pages" placeholder="Podaj liczbę stron"/>
    <form:input path="rating" placeholder="Podaj ocenę"/>
    <form:select path="publisher">
        <form:option value="-" label="-- Wybierz wydawcę --"/>
        <form:options items="${publishers}" itemLabel="name" itemValue="id"/>
    </form:select>
    <%--<form:select items="${publishers}" path="publisher" itemLabel="name" itemValue="id"/>--%>
    <form:select items="${authors}" path="authors" itemLabel="fullName" itemValue="id" multiple="true"/>
    <form:hidden path="proposition" />
    <input type="submit">
</form:form>
</body>
</html>
