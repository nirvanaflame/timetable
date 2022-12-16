<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h1>Error</h1>
<p>The error is occurred.</p>
<p>Below are the exceptions occurred and its traced: </p>
<p><% exception.printStackTrace(response.getWriter()); %></p>
</body>
</html>