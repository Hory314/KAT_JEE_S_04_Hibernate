<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="personDTO">
    <form:input path="login" placeholder="Podaj login"/><br>
    <form:input path="password" placeholder="Podaj password"/><br>
    <form:input path="email" placeholder="Podaj email"/><br>
    <form:input path="firstName" placeholder="Podaj firstName"/><br>
    <form:input path="lastName" placeholder="Podaj lastName"/><br>
    <label>Sex:</label><br>
    M<form:radiobutton path="gender" value="M" checked="checked"/><br>
    F<form:radiobutton path="gender" value="F"/><br>
    <form:select path="country" items="${countries}"/><br>
    <form:textarea path="notes" placeholder="Notes"/><br>
    <label>Mailing list</label><br>
    <form:checkbox path="mailingList"/><br>
    <label>Programming skills:</label><br>
    <form:select path="programmingSkills" items="${skills}" multiple="true"/><br>
    <label>Hobbies:</label><br>
    <form:checkboxes path="hobbies" items="${hobbies}"/><br>
    <input type="submit">
</form:form>
</body>
</html>
