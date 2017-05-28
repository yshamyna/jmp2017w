<!DOCTYPE html>
<html>
    <head>
        <title>Update project</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="UpdateProjectFrom" action="${pageContext.request.contextPath}/project/update" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" class="form-control" type="text" name="name" value="${project.name}">
            </div>
            <input type="hidden" name="id" value="${project.id}">
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
        <hr/>
        <c:choose>
            <c:when test="${empty assignedEmployees}">
                <div class="alert alert-info"><strong>Info!</strong> There are no assigned employees.</div>
            </c:when>
            <c:otherwise>
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach var="employee" items="${assignedEmployees}">
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
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <hr/>
        <form name="AssignToProjectFrom" action="${pageContext.request.contextPath}/project/assign" method="POST">
            <div class="form-group">
                <select id="employee" class="form-control" name="assignedEmployee">
                    <c:forEach var="employee" items="${allEmployees}">
                        <option value="${employee.id}">${employee.firstName} | ${employee.lastName} | ${employee.personalInfo.age} | ${employee.status} | ${employee.address.street} | ${employee.address.flatNumber} | ${employee.address.houseNumber} | ${employee.unit.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="id" value="${project.id}">
            <input type="submit" class="btn btn-info" value="Assign">
        </form>
        <hr/>
        <form name="UnassignFromProjectFrom" action="${pageContext.request.contextPath}/project/unassign" method="POST">
            <div class="form-group">
                <select id="employee" class="form-control" name="unassignedEmployee">
                    <c:forEach var="employee" items="${assignedEmployees}">
                        <option value="${employee.id}">${employee.firstName} | ${employee.lastName} | ${employee.personalInfo.age} | ${employee.status} | ${employee.address.street} | ${employee.address.flatNumber} | ${employee.address.houseNumber} | ${employee.unit.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="id" value="${project.id}">
            <input type="submit" class="btn btn-info" value="Unassign">
        </form>
    </body>
</html>