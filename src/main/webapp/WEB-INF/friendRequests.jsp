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
    List<FriendRequest> friendRequestList = (List<FriendRequest>) request.getAttribute("friendRequestList");
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
            <th>from image</th>
            <th>from name</th>
            <th>date</th>
            <th>action</th>
        </tr>
        <%
            for (FriendRequest friendRequest : friendRequestList) { %>
        <tr>
            <td>
                <img src="/getImage?imageName=<%=friendRequest.getFromUser().getImageName()%>" width="40">
            </td>
            <td><%=friendRequest.getFromUser().getName() + " "+ friendRequest.getFromUser().getSurname()%>
            </td>
            <td><%=DateUtil.fromDateToSqlDateTimeString(friendRequest.getDate())%>
            </td>
            <td><a href="/approveFriendRequest?requestId=<%=friendRequest.getId()%>">Approve</a>
                | <a href="/rejectFriendRequest?requestId=<%=friendRequest.getId()%>">Reject</a></td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
