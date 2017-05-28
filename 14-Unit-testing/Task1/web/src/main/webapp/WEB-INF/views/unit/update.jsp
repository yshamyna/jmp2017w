<!DOCTYPE html>
<html>
    <head>
        <title>Update unit</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="UpdateUnitFrom" action="${pageContext.request.contextPath}/unit/update" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" class="form-control" type="text" name="name" value="${unit.name}">
            </div>
            <input type="hidden" name="id" value="${unit.id}">
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>