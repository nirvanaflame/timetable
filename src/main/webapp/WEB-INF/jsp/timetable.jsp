<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <h2 class="today">${param.today}</h2>
</header>
<section>
    <div class="calendar">
    <c:forEach var="day" items="${paramValues.days}">
       <button class="day-picker"><c:out value="${day}"/></button>
    </c:forEach>
    </div>
</section>
<aside>
    <p>Group</p>
    <p>Teacher</p>
    <p>Student</p>
</aside>

</body>
</html>