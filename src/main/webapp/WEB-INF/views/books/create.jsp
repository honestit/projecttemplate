<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create book</title>
</head>
<body>
<spring:form modelAttribute="createBookForm">
    <div>
        <label>Title: <spring:input path="title"/><spring:errors path="title"
                                                                 element="span"/></label>
    </div>
    <div>
        <label>Pages: <spring:input path="pages" type="number" min="0" max="1000"
                                    step="1"/><spring:errors path="pages" element="span"/></label>
    </div>
    <div>
        <label>Main author:
            <spring:select path="mainAuthor.id" items="${authors}" itemValue="id"
                           itemLabel="fullName"/>
            or <spring:input path="mainAuthor.firstName" placeholder="Main author first name"/>
            <spring:input path="mainAuthor.lastName" placeholder="Main author last name"/>
        </label>
    </div>
    <div>
        Additional authors:
        <div>
            <label>First name: <input name="firstName"/> </label>
            <label>Last name: <input name="lastName"/></label>
            <button name="addAuthor">Add author</button>
        </div>
        <c:forEach items="${createBookForm.authors}" var="author" varStatus="status">
            <div>
                <input type="text" name="authors[${status.index}].firstName"
                       value="${createBookForm.authors[status.index].firstName}"
                       placeholder="First name"/>
                <input type="text" name="authors[${status.index}].lastName"
                       value="${createBookForm.authors[status.index].lastName}"
                       placeholder="Last name"/>
                <button name="removeAuthor" value="${status.index}">Remove</button>
            </div>
        </c:forEach>
    </div>
    <div>
        <label>Categories: <spring:select path="categories" items="${categories}" multiple="true"
                                          itemLabel="name" itemValue="id"/></label>
    </div>
    <spring:button name="createBook">Create book</spring:button>
</spring:form>
</body>
</html>
