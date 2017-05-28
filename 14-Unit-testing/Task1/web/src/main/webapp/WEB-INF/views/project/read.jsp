<!DOCTYPE html>
<html>
    <head>
        <title>Projects</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <c:choose>
            <c:when test="${empty projects}">
                <div class="alert alert-info"><strong>Info!</strong> There are no projects to display.</div>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach var="project" items="${projects}">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <th scope="row">${count}</th>
                                <td>${project.name}</td>
                                <td><a href="${pageContext.request.contextPath}/project/update/${project.id}">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>