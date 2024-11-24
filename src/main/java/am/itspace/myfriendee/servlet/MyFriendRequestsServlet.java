package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.FriendRequest;
import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myFriendRequests")
public class MyFriendRequestsServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<FriendRequest> friendRequestList = friendRequestService.getFriendRequestsByToId(user.getId());
        req.setAttribute("friendRequestList", friendRequestList);
        req.getRequestDispatcher("/WEB-INF/friendRequests.jsp").forward(req, resp);
    }
}
