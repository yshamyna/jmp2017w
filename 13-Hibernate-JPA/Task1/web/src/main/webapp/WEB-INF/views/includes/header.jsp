<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/css/bootstrap.min.css">
        <script src="${pageContext.request.contextPath}/frontend/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/frontend/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Customwebsite</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Unit <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/unit">All</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/unit/create">Create</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/unit/delete">Delete</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Project <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/project">All</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/project/create">Create</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/project/delete">Delete</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Employee <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/employee">All</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/employee/create">Create</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/employee/delete">Delete</a></li>
                    </ul>
                </li>
            </ul>
          </div>
        </nav>
    </body>
</html>