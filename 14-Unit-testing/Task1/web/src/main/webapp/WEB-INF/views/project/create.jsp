<!DOCTYPE html>
<html>
    <head>
        <title>Create project</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/includes/header.jsp" %>
        <form name="CreateProjectForm" action="${pageContext.request.contextPath}/project/create" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" class="form-control" type="text" name="name">
            </div>
            <input type="submit" class="btn btn-info" value="Submit">
        </form>
    </body>
</html>