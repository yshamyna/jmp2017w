<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add person</title>
    </head>
    <body>
        <%@ include file="/includes/menu.html" %>
        <form name="PersonAdd" action="add" method="POST">
            <label for="fname">First Name:</label>
            <input id="fname" type="text" name="firstName"><br/>

            <label for="lname">Last Name:</label>
            <input id="lname" type="text" name="lastName"><br/>

            <label for="bdate">Birth Date:</label>
            <input id="bdate" type="text" name="birthDate"><br/>

            <label for="hobbies">Hobbies:</label>
            <input id="hobbies" type="text" name="hobbies"><br/>

            <input type="submit"  value="Add">
        </form>
    </body>
</html>