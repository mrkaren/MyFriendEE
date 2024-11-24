<%@ page import="am.itspace.myfriendee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
    List<User> myFriends = (List<User>) request.getAttribute("myFriends");
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
            <th>Friends image</th>
            <th>Friends name surname</th>
            <th>action</th>
        </tr>
        <%
            for (User user : myFriends) { %>
        <tr>
            <td>
                <img src="/getImage?imageName=<%=user.getImageName()%>" width="40">
            </td>
            <td><%=user.getName() + " " + user.getSurname()%>
            </td>
            <td><a href="/sendMessage?to_id=<%=user.getId()%>">Send Message</a></td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
