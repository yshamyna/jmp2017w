<!DOCTYPE html>
<html>
    <head>
        <title>Delete units</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <c:choose>
            <c:when test="${empty units}">
                <div class="alert alert-info"><strong>Info!</strong> There are no units to delete.</div>
            </c:when>
            <c:otherwise>
                <form name="DeleteUnitForm" action="${pageContext.request.contextPath}/unit/delete" method="POST">
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
                            <c:forEach var="unit" items="${units}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <th scope="row">${count}</th>
                                    <td>${unit.name}</td>
                                    <td><input type="checkbox" name="ids" value="${unit.id}" /></td>
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