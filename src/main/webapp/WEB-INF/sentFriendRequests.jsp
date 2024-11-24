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
    List<FriendRequest> sentFriendRequestList = (List<FriendRequest>) request.getAttribute("sentFriendRequestList");
%>
Welcome <%=currentUser.getName()%> | <a href="/logout">Logout</a>
<%if (currentUser.getImageName() != null && !currentUser.getImageName().isEmpty()) {%>
<img src="/getImage?imageName=<%=currentUser.getImageName()%>" width="50">
<%}%>
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
            <th>date</th>
            <th>action</th>
        </tr>
        <%
            for (FriendRequest friendRequest : sentFriendRequestList) { %>
        <tr>
            <td>
                <%if (friendRequest.getToUser().getImageName() != null && !friendRequest.getToUser().getImageName().isEmpty()) {%>
                <img src="/getImage?imageName=<%=friendRequest.getToUser().getImageName()%>" width="40">
                <%}%>
            </td>
            <td><%=friendRequest.getToUser().getName() + " " + friendRequest.getToUser().getSurname()%>
            </td>
            <td><%=DateUtil.fromDateToSqlDateTimeString(friendRequest.getDate())%>
            </td>
            <td><a href="/deleteFriendRequest?requestId=<%=friendRequest.getId()%>">Delete</a></td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
