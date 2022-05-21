<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create book</title>
</head>
<body>
<spring:form modelAttribute="createBookForm">
    <label>Title
        <spring:input path="title"/></label>
    <spring:button>Add</spring:button>

</spring:form>
</body>
</html>
