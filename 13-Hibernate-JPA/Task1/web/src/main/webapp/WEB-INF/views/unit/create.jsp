<!DOCTYPE html>
<html>
    <head>
        <title>Create unit</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="CreateUnitForm" action="${pageContext.request.contextPath}/unit/create" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" class="form-control" type="text" name="name">
            </div>
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>