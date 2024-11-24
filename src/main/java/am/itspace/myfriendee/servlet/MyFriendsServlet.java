package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.FriendRequest;
import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.FriendRequestService;
import am.itspace.myfriendee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myFriends")
public class MyFriendsServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<User> myFriends = userService.getMyFriends(user.getId());
        req.setAttribute("myFriends", myFriends);
        req.getRequestDispatcher("/WEB-INF/myFriends.jsp").forward(req, resp);
    }
}
