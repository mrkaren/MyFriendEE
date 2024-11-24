package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.FriendRequest;
import am.itspace.myfriendee.service.FriendRequestService;
import am.itspace.myfriendee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approveFriendRequest")
public class ApproveFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();
    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestId = Integer.parseInt(req.getParameter("requestId"));
        FriendRequest friendRequest = friendRequestService.getFriendRequestById(requestId);
        if (friendRequest == null) {
            resp.sendRedirect("/users");
        } else {
            userService.addFriend(friendRequest.getToUser().getId(), friendRequest.getFromUser().getId());
            friendRequestService.deleteFriendRequest(friendRequest.getId());
            resp.sendRedirect("/users");
        }
    }
}
