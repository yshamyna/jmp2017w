<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>remove persons</title>
    </head>
    <body>
        <%@ include file="/jsp/includes/menu.jsp" %>
        <c:choose>
            <c:when test="${isEmpty}">
               <c:out value="There are no records to remove." />
            </c:when>
            <c:otherwise>
                <form name="PersonRemoveForm" action="remove" method="POST">
                    <input type="submit" value="Remove Selected">
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
                                <td><input type="checkbox" name="ids" value="${person.id}" /></td>
                              </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>