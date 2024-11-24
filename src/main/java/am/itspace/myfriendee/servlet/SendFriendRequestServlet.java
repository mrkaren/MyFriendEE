package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendFriendRequest")
public class SendFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        friendRequestService.add(user.getId(), friendId);
        resp.sendRedirect("/users");
    }
}
