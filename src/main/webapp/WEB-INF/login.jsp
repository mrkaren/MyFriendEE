<%--
  Created by IntelliJ IDEA.
  User: karen
  Date: 17.11.24
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%
    if (session.getAttribute("msg") != null) { %>
<h3><%=session.getAttribute("msg")%>
</h3>
<% session.removeAttribute("msg");
}
%>
<form action="/login" method="post">

    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit">

</form>
<a href="/register">register</a>
</body>
</html>
