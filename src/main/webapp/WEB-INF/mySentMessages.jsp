<%@ page import="am.itspace.myfriendee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="am.itspace.myfriendee.model.FriendRequest" %>
<%@ page import="am.itspace.myfriendee.util.DateUtil" %>
<%@ page import="am.itspace.myfriendee.model.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
    List<Message> messages = (List<Message>) request.getAttribute("messages");
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
    <table>
        <tr>
            <th>to image</th>
            <th>to name</th>
            <th>message</th>
            <th>date</th>
        </tr>
        <%
            for (Message message : messages) { %>
        <tr>
            <td>
                <img src="/getImage?imageName=<%=message.getToUser().getImageName()%>" width="40">
            </td>
            <td><%=message.getToUser().getName() + " " + message.getToUser().getSurname()%>
            </td>
            <td><%=message.getMessage()%>
            </td>
            <td><%=DateUtil.fromDateToSqlDateTimeString(message.getDate())%>
            </td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
