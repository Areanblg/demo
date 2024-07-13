<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="item" items="${admins}">
    id:${item.id}  <br>

    username:${item.username} <br>
    password:${item.password}


</c:forEach>

</body>
</html>
