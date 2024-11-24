package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<User> usersList = userService.getUsersExceptCurrent(user.getId());
        req.setAttribute("users", usersList);
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
    }
}
