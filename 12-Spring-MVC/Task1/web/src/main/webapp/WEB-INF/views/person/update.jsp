<!DOCTYPE html>
<html>
    <head>
        <title>Update person</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="UpdatePersonFrom" action="${pageContext.request.contextPath}/persons/edit" method="POST">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input id="fname" class="form-control"  type="text" name="firstName" value="${person.firstName}">
            </div>
            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input id="lname" class="form-control"  type="text" name="lastName" value="${person.lastName}">
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input id="age" class="form-control"  type="text" name="age" value="${person.age}">
            </div>
            <div class="form-group">
                <label for="passportNumber">Passport Number:</label>
                <input id="passportNumber" class="form-control"  type="text" name="passportNumber" value="${person.passportNumber}">
            </div>
            <input type="hidden" name="id" value="${person.id}">
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>