<!DOCTYPE html>
<html>
    <head>
        <title>Create person</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="AddPersonForm" action="${pageContext.request.contextPath}/persons/add" method="POST">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input id="fname" class="form-control"  type="text" name="firstName">
            </div>
            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input id="lname" class="form-control"  type="text" name="lastName">
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input id="age" class="form-control"  type="text" name="age">
            </div>
            <div class="form-group">
                <label for="passportNumber">Passport Number:</label>
                <input id="passportNumber" class="form-control"  type="text" name="passportNumber">
            </div>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>