<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <c:forEach items="${list}" var="e">
            <tr>
                <td><c:out value="${e}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>