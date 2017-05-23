<!DOCTYPE html>
<html>
    <head>
        <title>Delete projects</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <c:choose>
            <c:when test="${empty projects}">
                <div class="alert alert-info"><strong>Info!</strong> There are no projects to delete.</div>
            </c:when>
            <c:otherwise>
                <form name="DeleteProjectForm" action="${pageContext.request.contextPath}/project/delete" method="POST">
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
                                    <td><input type="checkbox" name="ids" value="${project.id}" /></td>
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