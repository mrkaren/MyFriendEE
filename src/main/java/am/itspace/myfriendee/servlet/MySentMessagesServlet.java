package am.itspace.myfriendee.servlet;

import am.itspace.myfriendee.model.Message;
import am.itspace.myfriendee.model.User;
import am.itspace.myfriendee.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mySentMessages")
public class MySentMessagesServlet extends HttpServlet {

    private MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        List<Message> messagesByFromId = messageService.getMessagesByFromId(userId);

        req.setAttribute("messages", messagesByFromId);
        req.getRequestDispatcher("/WEB-INF/mySentMessages.jsp").forward(req, resp);
    }
}
