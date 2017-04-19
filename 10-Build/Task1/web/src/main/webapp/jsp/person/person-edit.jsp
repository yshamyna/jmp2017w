<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add person</title>
    </head>
    <body>
        <%@ include file="/includes/menu.html" %>
        <form name="PersonEdit" action="edit" method="POST">
            <label for="fname">First Name:</label>
            <input id="fname" type="text" name="firstName" value="${person.firstName}"><br/>

            <label for="lname">Last Name:</label>
            <input id="lname" type="text" name="lastName" value="${person.lastName}"><br/>

            <label for="bdate">Birth Date:</label>
            <input id="bdate" type="text" name="birthDate" value="${person.birthDate}"><br/>

            <label for="hobbies">Hobbies:</label>
            <input id="hobbies" type="text" name="hobbies" value="${person.hobbies}"><br/>

            <input type="hidden" name="id" value="${person.id}">
            <input type="submit"  value="Submit">
        </form>
    </body>
</html>