<!DOCTYPE html>
<html>
    <head>
        <title>Update employee</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="UpdateEmployeeForm" action="${pageContext.request.contextPath}/employee/update" method="POST">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input id="fname" class="form-control" type="text" name="firstName" value="${employee.firstName}">
            </div>
            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input id="lname" class="form-control" type="text" name="lastName" value="${employee.lastName}">
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input id="age" class="form-control" type="text" name="age" value="${employee.personalInfo.age}">
            </div>
            <div class="form-group">
                <label for="status">Select Status:</label>
                <select id="status" class="form-control" name="status">
                    <c:forEach var="status" items="${statuses}">
                        <option value="${status}" ${status == employee.status ? 'selected="selected"' : ''}>${status}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="street">Street:</label>
                <input id="street" class="form-control" type="text" name="street" value="${employee.address.street}">
            </div>
            <div class="form-group">
                <label for="flatNumber">Flat Number:</label>
                <input id="flatNumber" class="form-control" type="text" name="flatNumber" value="${employee.address.flatNumber}">
            </div>
            <div class="form-group">
                <label for="houseNumber">House Number:</label>
                <input id="houseNumber" class="form-control" type="text" name="houseNumber" value="${employee.address.houseNumber}">
            </div>
            <div class="form-group">
                <label for="unit">Select Unit:</label>
                <select id="unit" class="form-control" name="unit">
                    <c:forEach var="unit" items="${units}">
                        <option value="${unit.id}" ${unit.id == employee.unit.id ? 'selected="selected"' : ''}>${unit.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="id" value="${employee.id}">
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>