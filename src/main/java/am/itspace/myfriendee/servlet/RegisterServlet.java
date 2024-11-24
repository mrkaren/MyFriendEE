package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/register")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class RegisterServlet extends HttpServlet {

    private static final String IMAGE_UPLOAD_FOLDER = "/Users/karen/Data/lessons/javase2024/ee/MyFriendEE/images/";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession httpSession = req.getSession();

        StringBuilder msgBuilder = new StringBuilder();

        if (name == null || name.trim().isEmpty()) {
            msgBuilder.append("Name is required");
            msgBuilder.append("<br>");
        }
        if (surname == null || surname.trim().isEmpty()) {
            msgBuilder.append("Surname is required");
            msgBuilder.append("<br>");
        }
        if (email == null || email.trim().isEmpty()) {
            msgBuilder.append("Email is required");
            msgBuilder.append("<br>");
        }
        if (password == null || password.trim().isEmpty() || password.length() < 6) {
            msgBuilder.append("password is required or password's length less then 6");
            msgBuilder.append("<br>");
        }

        if (!msgBuilder.isEmpty()) {
            httpSession.setAttribute("msg", msgBuilder.toString());
        } else if (userService.getUserByEmail(email) != null) {
            msgBuilder.append("Email already in use");
            msgBuilder.append("<br>");
        } else {
            Part img = req.getPart("img");
            String fileName = null;
            if (img != null && img.getSize() > 0) {
                fileName = System.nanoTime() + "_" + img.getSubmittedFileName();
                img.write(IMAGE_UPLOAD_FOLDER + fileName);
            }
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .imageName(fileName)
                    .build();
            userService.add(user);
            httpSession.setAttribute("msg", "User successfully registered");
        }
        resp.sendRedirect("/register");

    }
}
