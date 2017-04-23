<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Persons</title>
    </head>
    <body>
        <%@ include file="/jsp/includes/menu.jsp" %>
        <c:choose>
            <c:when test="${isEmpty}">
               <c:out value="There are no records to display." />
            </c:when>
            <c:otherwise>
                <table border="1">
                    <thead>
                        <tr>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Birth date</th>
                            <th>Hobbies</th>
                            <th>Operation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${persons}" var="person">
                          <tr>
                            <td>${person.firstName}</td>
                            <td>${person.lastName}</td>
                            <td>${person.birthDate}</td>
                            <td>${person.hobbies}</td>
                            <td><a href="edit?id=${person.id}">Edit</a></td>
                          </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>