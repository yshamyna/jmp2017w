<!DOCTYPE html>
<html>
    <head>
        <title>Delete persons</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <c:choose>
            <c:when test="${empty persons}">
                <div class="alert alert-info"><strong>Info!</strong> There are no persons to delete.</div>
            </c:when>
            <c:otherwise>
                <form name="RemovePersonsForm" action="${pageContext.request.contextPath}/persons/delete" method="POST">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Age</th>
                                <th>Passport Number</th>
                                <th>&nbsp;</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="person" items="${persons}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <th scope="row">${count}</th>
                                    <td>${person.firstName}</td>
                                    <td>${person.lastName}</td>
                                    <td>${person.age}</td>
                                    <td>${person.passportNumber}</td>
                                    <td><input type="checkbox" name="ids" value="${person.id}" /></td>
                                    <th scope="row">${count}</th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" class="btn btn-info center-block" value="Submit">
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>