<!DOCTYPE html>
<html>
    <head>
        <title>Delete employees</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <c:choose>
            <c:when test="${empty employees}">
                <div class="alert alert-info"><strong>Info!</strong> There are no employees to delete.</div>
            </c:when>
            <c:otherwise>
                <form name="DeleteEmployeeForm" action="${pageContext.request.contextPath}/employee/delete" method="POST">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Age</th>
                                <th>Status</th>
                                <th>Street</th>
                                <th>Flat Number</th>
                                <th>House Number</th>
                                <th>Unit</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="employee" items="${employees}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <th scope="row">${count}</th>
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.personalInfo.age}</td>
                                    <td>${employee.status}</td>
                                    <td>${employee.address.street}</td>
                                    <td>${employee.address.flatNumber}</td>
                                    <td>${employee.address.houseNumber}</td>
                                    <td>${employee.unit.name}</td>
                                    <td><input type="checkbox" name="ids" value="${employee.id}" /></td>
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