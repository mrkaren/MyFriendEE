<%@ page import="am.itspace.myfriendee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="am.itspace.myfriendee.model.FriendRequest" %>
<%@ page import="am.itspace.myfriendee.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
%>
Welcome <%=currentUser.getName()%> | <a href="/logout">Logout</a>
<img src="/getImage?imageName=<%=currentUser.getImageName()%>" width="50">
<ul>
    <li><a href="/users">View All Users</a></li>
    <li><a href="/myFriendRequests">View My Requests</a></li>
    <li><a href="/mySentFriendRequests">View My Sent Requests</a></li>
    <li><a href="/myFriends">View My Friends</a></li>
    <li><a href="/myMessages">View My Messages</a></li>
    <li><a href="/mySentMessages">View My Sent Messages</a></li>
</ul>
<div>
    <form action="/sendMessage" method="post">
        <input type="hidden" name="to_id" value="<%=request.getParameter("to_id")%>">
        <textarea name="message"></textarea>
        <input type="submit" value="send">
    </form>
</div>
</body>
</html>
