<!DOCTYPE html>
<html>
    <head>
        <title>Create employee</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="CreateEmployeeForm" action="${pageContext.request.contextPath}/employee/create" method="POST">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input id="fname" class="form-control" type="text" name="firstName">
            </div>
            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input id="lname" class="form-control" type="text" name="lastName">
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input id="age" class="form-control" type="text" name="age">
            </div>
            <div class="form-group">
                <label for="status">Select Status:</label>
                <select id="status" class="form-control" name="status">
                    <option selected="selected" value="ACTIVE">ACTIVE</option>
                    <option value="INACTIVE">INACTIVE</option>
                </select>
            </div>
            <div class="form-group">
                <label for="street">Street:</label>
                <input id="street" class="form-control" type="text" name="street">
            </div>
            <div class="form-group">
                <label for="flatNumber">Flat Number:</label>
                <input id="flatNumber" class="form-control" type="text" name="flatNumber">
            </div>
            <div class="form-group">
                <label for="houseNumber">House Number:</label>
                <input id="houseNumber" class="form-control" type="text" name="houseNumber">
            </div>
            <div class="form-group">
                <label for="unit">Select Unit:</label>
                <select id="unit" class="form-control" name="unit">
                    <c:forEach var="unit" items="${units}">
                        <option value="${unit.id}">${unit.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>